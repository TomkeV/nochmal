package de.htwg.se.nochmal
package model

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

	// Methode zum Füllen von Feldern:
  def fillCell(row:Int, col:Int):PitchAsMatrix = 
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))

  // Hilfsethoden zur Ausgabe als String
  val eol = sys.props("line.separator")

  def title(cellWidth:Int = 3, colNum:Int = 7) = (" " + (" " * ((cellWidth-1)/2)) + "A" + " ") * colNum + eol

  def columns(cellWidth:Int = 3, colNum:Int = 7) = ("+" + "-" * cellWidth) * colNum + "+" + eol

  def lines(cellWidth:Int = 3, colNum:Int = 7) = ("|" + " " * cellWidth) * colNum + "|" + eol
  // über Zeile iterieren, Wert der Matrix an x|y abfragen und eintragen


  def pointsFirst(cellWidth:Int = 3, colNum:Int = 7) = (" " + (" " * ((cellWidth-1)/2)) + "5" + " ") * colNum + eol

  def pointsNext(cellWidth:Int = 3, colNum:Int = 7) = (" " + (" " * ((cellWidth-1)/2)) + "3" + " ") * colNum + eol

  def pitchToString(cellWidth:Int = 3, colNum:Int = 7, lineNum:Int = 4) = 
    title(cellWidth, colNum) + (columns(cellWidth, colNum) + lines(cellWidth, colNum)) * lineNum + 
    columns(cellWidth, colNum) + pointsFirst(cellWidth, colNum) + pointsNext(cellWidth, colNum)
  
  // Methode toString()
  override def toString = pitchToString(3, row_num, col_num)


// --- alte Methode, um neue leere Matrix mit n Zeilen und m Spalten erzeugt ---
/* def create_Matrix(rows:Int = 4, columns:Int = 7): Vector[Vector[Filling]] =
	Vector.tabulate(rows) { i =>
   	Vector.tabulate(columns) {j => Filling.empty}
  } */
