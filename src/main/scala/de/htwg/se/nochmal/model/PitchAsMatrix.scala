package de.htwg.se.nochmal
package model

import util.*
import scala.util.Try
import scala.util.Success
import scala.util.Failure

case class PitchAsMatrix(matrix: Vector[Vector[Filling]]):
  
	// auxiliary construktor
  def this(rows:Int=4, columns:Int=7, width_of_cells:Int = 3) =
    this(Vector.tabulate(rows) {i =>
    Vector.tabulate(columns) {j => Filling.empty}
    })
  
  val row_num = matrix.size 			// Anzahl Zeilen der Matrix
  val col_num = matrix.head.size	// Anzahl Spalten der Matrix

  // Methode für Zugriff auf Spalte:
  def getColumn(row:Int): Vector[Filling] =
    matrix(row)
	
	// Methode für Zugriff auf Index:
  def getIndex(row:Int, col:Int):Filling =
    matrix(row)(col)

	// Methode zum Füllen von Feldern
/*   def fillCell(row:Int, col:Int): PitchAsMatrix = 
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))

  // Methode zum Füllen mehrerer Felder:
  def fillCellsList(l:List[Option[Cross]], p:PitchAsMatrix): PitchAsMatrix = 
    var tmpMatrix = p
    for (cross <- l) yield cross match {
      case Some(c) => tmpMatrix = tmpMatrix.fillCell(c.x-1, c.y-1)
      case None => }
    tmpMatrix */
  
  def fillCellWithCross(c:Cross, p:PitchAsMatrix):PitchAsMatrix = 
    val row = c.x-1
    val col = c.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))

 
/*   // Methode zum Leeren von Feldern (nötig für Undo)
  def unfillCell(row:Int, col:Int): PitchAsMatrix =
    copy(matrix.updated(row, matrix(row).updated(col, Filling.empty)))

  def unfillCellsList(l:List[Option[Cross]], p:PitchAsMatrix): PitchAsMatrix =
    var tmpMatrix = p
    for (cross <- l) yield cross match {
      case Some(c) => tmpMatrix = tmpMatrix.unfillCell(c.x-1, c.y-1)
      case None => }
    tmpMatrix */

  def unfillCellWithCross(cross:Cross, pitch:PitchAsMatrix):PitchAsMatrix = 
    val row = cross.x-1
    val col = cross.y-1
    copy(matrix.updated(row, matrix(row).updated(col, Filling.empty)))

  def pitchToString(cellWidth:Int = 3): String =
    val result = Pitch.builder(cellWidth, col_num)
      .createTitle(Title(cellWidth, col_num))
      .createMatrixWithColors(MatrixWithColors(cellWidth, col_num, row_num, this))
      //.createMatrix(Matrix(cellWidth, col_num, row_num, this))
      .createPoints(Points(cellWidth, col_num))
    return result.toString()

  
  // Methode toString()
  override def toString = pitchToString()


// Alte if-else von createPoints:
  /*           if (x == numOfPoints(0) || x == numOfPoints(colNum-1)){
            (" " * ((cellWidth-1)/2)) + " 5 "
          } else if ((x == (colNum/2)) || (x == (colNum/2)-1)) {
            (" " * ((cellWidth-1)/2)) + " 1 "
          } else if (x == ((colNum/2)-2) || x == ((colNum/2)+1)) {
            (" " * ((cellWidth-1)/2)) + " 2 "
          } else {
            (" " * ((cellWidth-1)/2)) + " 3 "
          } */
          /* if (x == numOfPoints(0) || x == numOfPoints(colNum-1)){
            (" " * ((cellWidth-1)/2)) + " 5 "
          } else if (x == (colNum/2)) {
            (" " * ((cellWidth-1)/2)) + " 1 "
          } else if (x == ((colNum/2)+1) || x == ((colNum/2)-1)) {
            (" " * ((cellWidth-1)/2)) + " 2 "
          } else {
            (" " * ((cellWidth-1)/2)) + " 3 "
          } */


// Ausgelagert in PitchBuilder:
    // Hilfsmethoden zur Ausgabe als String
/*   val eol = sys.props("line.separator")

  def title(cellWidth:Int = 3, colNum:Int = 7): String =
    val list = Range(0, colNum).toList
    val res = list.map(x =>
                " " + 
                " " * ((cellWidth-1)/2) +
                ('A' + x).toChar +
                " ").mkString
    return res + eol

  def columns(cellWidth:Int = 3, colNum:Int = 7) = ("+" + "-" * cellWidth) * colNum + "+" + eol
  
  def points(cellWidth:Int = 3, colNum:Int = 7): String =
        if (colNum%2 == 0) { // für gerade Zahlen: 
          EvenOdd.handle(EvenEvent())(cellWidth, colNum) + eol
        } else { // für ungerade Zahlen: 
          EvenOdd.handle(OddEvent())(cellWidth, colNum) + eol
        } */
        
      /* val numOfCells = Range(0, col_num)
    val numOfRows = Range(0, row_num)

    val result = title(cellWidth, col_num) + columns(cellWidth, col_num) + numOfRows.map(x =>
      (numOfCells.map(y =>
        "|" + " " * (cellWidth/2) + getIndex(x, y).toString() 
        + " " * (cellWidth/2)).mkString) + "|" + eol + columns(cellWidth, col_num)).mkString 
        + points(cellWidth, col_num)

    return result */