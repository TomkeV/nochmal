/**
  * PitchAsMatrix.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package pitchComponent
package baseModel
import controller.controllerComponent.controllerBaseImpl.rounds

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import util.*
import pitchComponent.PitchInterface

// Bibliotheksimports
import play.api.libs.json.*
import java.io.{PrintWriter, File}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class PitchAsMatrix(matrix: Vector[Vector[Filling]]) extends PitchInterface {

  var myColor: PitchWithColorsInterface = PitchWithColors(blackColorsList)

	// auxiliary construktor
  def this(rows:Int=4, columns:Int=7, width_of_cells:Int = 3, color:PitchWithColors = PitchWithColors(blackColorsList)) = {
    this(Vector.tabulate(rows) {i =>
      Vector.tabulate(columns) {j => Filling.empty}})
    myColor = color
  }
  
  val row_num = matrix.size 			// Anzahl Zeilen der Matrix
  val col_num = matrix.head.size	// Anzahl Spalten der Matrix

  // Methode für Zugriff auf Spalte:
  def getColumn(row:Int): Vector[Filling] =
    matrix(row)
	
	// Methode für Zugriff auf Index:
  def getIndex(row:Int, col:Int):Filling =
    matrix(row)(col)

  // Methode zum Ankreuzen eines Felds
  override def fillCellWithCross(c:Cross, p:PitchInterface):PitchAsMatrix = 
    val row = c.x-1
    val col = c.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))
  
  // Methode zum Leeren eines Felds
  def unfillCellWithCross(cross:Cross, pitch:PitchInterface):PitchAsMatrix = 
    val row = cross.x-1
    val col = cross.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.empty)))

  // Methode zum Zusammensetzen eines Felds mit PitchBuilder
  def pitchToString(cellWidth:Int = 3): String =
    val result = Pitch.builder(cellWidth, col_num)
      .createTitle(Title(cellWidth, col_num))
      .createMatrixWithColors(MatrixWithColors(cellWidth, col_num, row_num, this))
      //.createMatrix(Matrix(cellWidth, col_num, row_num, this))
      .createPoints(Points(cellWidth, col_num))
    return result.toString()

  // Methode toString()
  override def toString = pitchToString()


  // ------------------------------------------------------ File-IO:
  override def saveToJson(file:String): Unit = {
    val pw = new PrintWriter(new File(file)) 
    pw.write(
      Json.obj(
        "pitch" -> matrixToString(matrix),
        "rounds" -> rounds,
        "color" -> myColor.getColor()
      ).toString
    )
    pw.close()
  }

  def matrixToString(m:Vector[Vector[Filling]]):IndexedSeq[String] = {
    Range(0, m.length).map(x => vectorToString(m(x)))
  }

  // Hilfsmethode um Matrix umzuwandeln
  def vectorToString(v:Vector[Filling]):String = {
    var res = ""
    v.map(y => res += y.toString())
    return res
  }

  override def loadFromJson(file:String): PitchInterface = {
    val json = Json.parse(file)
    println(json)
    myColor = (json \ "color").as[String] match
      case "black" => PitchWithColors(blackColorsList, Color.black)
      case "orange" => PitchWithColors(orangeColorsList, Color.orange)
      case "blue" => PitchWithColors(blueColorsList, Color.blue)
      case "yellow" => PitchWithColors(yellowColorsList, Color.yellow)
    
    val p = (json \ "pitch").as[IndexedSeq[String]]
    rounds = (json \ "rounds").as[Int]

    val m = stringsToMatrix(p)

    return PitchAsMatrix(m)
  }

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
}
