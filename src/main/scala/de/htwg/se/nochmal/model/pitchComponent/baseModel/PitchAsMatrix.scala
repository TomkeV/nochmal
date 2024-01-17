/**
  * PitchAsMatrix.scala
  * @author: Tomke Velten
  * @version: 14.01.2024
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package pitchComponent
package baseModel

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne Imports
import util.*
import pitchComponent.PitchInterface
import controller.controllerComponent.controllerBaseImpl.rounds

// Bibliotheksimports
import play.api.libs.json.*
import java.io.{PrintWriter, File}
import scala.io.Source
import scala.xml.XML

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class PitchAsMatrix(matrix: Vector[Vector[Filling]]) extends PitchInterface {
	/* Auxiliary-Constructor:
   * Eigener Konstruktor zum Initialisieren eines leeren Spielfelds. 
   */
  def this(rows:Int=4, columns:Int=7, width_of_cells:Int = 3, color:PitchWithColors = PitchWithColors(blackColorsList)) = {
    this(Vector.tabulate(rows) {i =>
      Vector.tabulate(columns) {j => Filling.empty}})
    myColor = color
  }
  
  // --------------------------------------------------------------------- Variablen
  var myColor: PitchWithColorsInterface = PitchWithColors(blackColorsList) // Farbe des Spielfelds
  val row_num = matrix.size 			// Anzahl Zeilen der Matrix
  val col_num = matrix.head.size	// Anzahl Spalten der Matrix


  // --------------------------------------------------------------------- Funktionen
  // Methode für Zugriff auf Spalte:
  override def getColumn(row:Int): Vector[Filling] =
    matrix(row)
	
	// Methode für Zugriff auf Index:
  override def getIndex(row:Int, col:Int):Filling =
    matrix(row)(col)

  // Methode zum Ankreuzen eines Felds
  override def fillCellWithCross(c:Cross, p:PitchInterface):PitchAsMatrix = 
    val row = c.x-1
    val col = c.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))
  
  // Methode zum Leeren eines Felds
  override def unfillCellWithCross(cross:Cross, pitch:PitchInterface):PitchAsMatrix = 
    val row = cross.x-1
    val col = cross.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.empty)))

  // Methode zum Zusammensetzen eines Felds mit PitchBuilder
  override def pitchToString(cellWidth:Int = 3): String =
    val result = Pitch.builder(cellWidth, col_num)
      .createTitle(Title(cellWidth, col_num))
      .createMatrixWithColors(MatrixWithColors(cellWidth, col_num, row_num, this))
      //.createMatrix(Matrix(cellWidth, col_num, row_num, this))
      .createPoints(Points(cellWidth, col_num))
    return result.toString()

  // Methode toString()
  override def toString = pitchToString()


  // -------------------------------------------------------------- Funktionen für File-IO
  // ------------------------------------------- File-IO mit JSON
  // Methode zum Speichern als JSON-File
  override def saveToJson(): Unit = {
    val pw = new PrintWriter(new File("saves/save1.json")) 
    pw.write(
      Json.obj(
        "pitch" -> matrixToString(matrix),
        "rounds" -> rounds,
        "color" -> myColor.getColor()
      ).toString
    )
    pw.close()
  }

  // Hilfsmethode zum Speichern der Matrix
  def matrixToString(m:Vector[Vector[Filling]]):IndexedSeq[String] = {
    Range(0, m.length).map(x => vectorToString(m(x)))
  }

  // Hilfsmethode um Matrix umzuwandeln
  def vectorToString(v:Vector[Filling]):String = {
    var res = ""
    v.map(y => res += y.toString())
    return res
  }

  // Methode zum Laden eines JSON-Files
  override def loadFromJson(): PitchInterface = {
    val load = Source.fromFile("saves/save1.json").mkString
    val json = Json.parse(load)
    myColor = (json \ "color").as[String] match
      case "o" => PitchWithColors(orangeColorsList, Color.orange)
      case "b" => PitchWithColors(blueColorsList, Color.blue)
      case "y" => PitchWithColors(yellowColorsList, Color.yellow)
      case _ => PitchWithColors(blackColorsList, Color.black)
    
    val p = (json \ "pitch").as[IndexedSeq[String]]
    rounds = (json \ "rounds").as[Int]

    val m = stringsToMatrix(p)

    return PitchAsMatrix(m)
  }

  // Hilfsmethode zum Einlesen der Matrix
  def stringsToMatrix(seq:IndexedSeq[String]):Vector[Vector[Filling]] = {
    seq.map(x => stringToVector(x)).toVector
  }

  // Hilfsmethode um aus Strings Matrix zu machen
  def stringToVector(s:String):Vector[Filling] = {
    val chars = s.toCharArray()
    Range(0, chars.length).map(x => chars(x) match
      case ' ' => Filling.empty
      case 'X' => Filling.filled
    ).toVector
  }

  // ------------------------------------------- File-IO mit XML
  override def loadFromXML(): PitchInterface = {
    val xml = XML.loadFile("saves/xml_save.xml").head
    myColor = (xml \ "color").text match
      case "o" => PitchWithColors(orangeColorsList, Color.orange)
      case "b" => PitchWithColors(blueColorsList, Color.blue)
      case "y" => PitchWithColors(yellowColorsList, Color.yellow)
      case _ => PitchWithColors(blackColorsList, Color.black) 
    val p = (xml \ "pitch").head.text
    println("Feld: " + p)
    rounds = (xml \ "rounds").text.toInt 
    //val q = matrixFromXML(p)

    return PitchAsMatrix(matrix)
  }
  // Hilfsmethode Matrix aus Strings:
  def matrixFromXML(v:Vector[String]): Vector[Vector[Filling]] = {
    v.map(x => 
      stringToVector(x)).toVector
  }

  override def saveToXML(): Unit = {
    val pw = new PrintWriter(new File("saves/xml_save.xml"))
    pw.write(
      "<state>" +
        matrixToXML() +
        "<rounds>" + rounds + "</rounds>" +
        "<color>" + myColor.getColor() + "</color>" +
      "</state>"
    )
    pw.close()
  }

  // Hilfsmethode Matrix speichern: 
  def matrixToXML():String = {
  "<pitch>" + 
    Range(1, row_num+1).map(i => "<zeile" + i + ">" + matrix(i) + "</zeile" + i + ">").mkString(sep = "+")
  "</pitch>"
  }
}