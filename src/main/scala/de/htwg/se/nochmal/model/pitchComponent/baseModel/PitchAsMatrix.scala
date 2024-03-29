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
import controller.rounds

// Bibliotheksimports
import play.api.libs.json.*
import java.io.{PrintWriter, File}
import scala.xml.{XML, Node}
import scala.io.Source

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class PitchAsMatrix(matrix: Vector[Vector[Filling]]) extends PitchInterface {
	/* Auxiliary-Constructor:
   * Eigener Konstruktor zum Initialisieren eines leeren Spielfelds. 
   */
  def this(rows:Int=4, columns:Int=7, width_of_cells:Int = 3, color:PitchWithColors = PitchWithColors(blackColorsList)) = {
    this(Vector.tabulate(rows) {i =>
      Vector.tabulate(columns) {j => Filling.empty}})
    pitchColor = color
  }
  
  // --------------------------------------------------------------------- Variablen
  var pitchColor: PitchWithColorsInterface = PitchWithColors(blackColorsList)
  val row_num = matrix.size
  val col_num = matrix.head.size


  // --------------------------------------------------------------------- Funktionen
  override def getColumn(row:Int): Vector[Filling] =
    matrix(row)
	
  override def getIndex(row:Int, col:Int):Filling =
    matrix(row)(col)

  override def fillCellWithCross(c:Cross, p:PitchInterface):PitchAsMatrix = 
    val row = c.x-1
    val col = c.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))
  
  override def unfillCellWithCross(cross:Cross, pitch:PitchInterface):PitchAsMatrix = 
    val row = cross.x-1
    val col = cross.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.empty)))

  override def pitchToString(cellWidth:Int = 3): String =
    Pitch.builder(cellWidth, col_num)
      .createTitle(Title(cellWidth, col_num))
      .createMatrixWithColors(MatrixWithColors(cellWidth, col_num, row_num, this))
      //.createMatrix(Matrix(cellWidth, col_num, row_num, this))
      .createPoints(Points(cellWidth, col_num)).toString()

  override def toString = pitchToString()


  // -------------------------------------------------------------- Funktionen für File-IO
  // ------------------------------------------- File-IO mit JSON
  override def saveToJson(): Boolean = {
    val pw = new PrintWriter(new File("saves/json_save.json")) 
    pw.write(
      Json.obj(
        "pitch" -> matrixToString(matrix),
        "rounds" -> rounds,
        "sum" -> controller.summe
      ).toString)
    pw.close()
    true
  }

  private def matrixToString(m:Vector[Vector[Filling]]):IndexedSeq[String] = {
    Range(0, m.length).map(x =>
      var r = ""
      m(x).map(y => r += y.toString())
      r)
  }

  override def loadFromJson(): PitchInterface = {
    val json = Json.parse(Source.fromFile("saves/json_save.json").mkString)
    rounds = (json \ "rounds").as[Int]
    controller.summe = (json \ "sum").as[Int]
    val pitch = (json \ "pitch").as[IndexedSeq[String]].map(i =>
                val chars = i.toCharArray()
                Range(0, chars.length).map(y =>
                  chars(y) match
                    case ' ' => Filling.empty
                    case 'X' => Filling.filled
                ).toVector).toVector

    return PitchAsMatrix(pitch)
  }

  // ------------------------------------------- File-IO mit XML
  override def loadFromXML(): PitchInterface = {
    val xml = XML.loadFile("saves/xml_save.xml").head
    controller.summe = (xml \ "sum").text.toInt
    rounds = (xml \ "rounds").text.toInt
    val p = createMatrixFromXML(xml)

    return PitchAsMatrix(p)
  }
  
  private def createMatrixFromXML(xml:Node): Vector[Vector[Filling]] = {
    val lines = Range(1, 8).map(i =>
      val line = "line"+i
      (xml \ line).text.toCharArray())
    lines.map(x => 
      x.map(c => c match
                    case ' ' => Filling.empty
                    case 'X' => Filling.filled
        ).toVector).toVector
  }

  override def saveToXML(): Boolean = {
    val pw = new PrintWriter(new File("saves/xml_save.xml"))
    pw.write(
      "<pitch>" +
        "<line1>" + matrixLineToString(matrix, 0) + "</line1>" +
        "<line2>" + matrixLineToString(matrix, 1) + "</line2>" +
        "<line3>" + matrixLineToString(matrix, 2) + "</line3>" +
        "<line4>" + matrixLineToString(matrix, 3) + "</line4>" +
        "<line5>" + matrixLineToString(matrix, 4) + "</line5>" +
        "<line6>" + matrixLineToString(matrix, 5) + "</line6>" +
        "<line7>" + matrixLineToString(matrix, 6) + "</line7>" +
        "<rounds>" + rounds + "</rounds>" +
        "<sum>" + controller.summe + "</sum>" +
      "</pitch>"
    )
    pw.close()
    true
  }

  private def matrixLineToString(m:Vector[Vector[Filling]], i:Int):String = {
    m(i).map(x =>
      x.toString()).mkString
  }
}