package de.htwg.se.nochmal
package model
package baseModel


import util.*

case class PitchAsMatrix(matrix: Vector[Vector[Filling]]) extends PitchInterface:
  
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



// ------------------------------------------------------------------------------------------
// Raus ab 15.12.:
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
 
/*   // Methode zum Leeren von Feldern (nötig für Undo)
  def unfillCell(row:Int, col:Int): PitchAsMatrix =
    copy(matrix.updated(row, matrix(row).updated(col, Filling.empty)))

  def unfillCellsList(l:List[Option[Cross]], p:PitchAsMatrix): PitchAsMatrix =
    var tmpMatrix = p
    for (cross <- l) yield cross match {
      case Some(c) => tmpMatrix = tmpMatrix.unfillCell(c.x-1, c.y-1)
      case None => }
    tmpMatrix */