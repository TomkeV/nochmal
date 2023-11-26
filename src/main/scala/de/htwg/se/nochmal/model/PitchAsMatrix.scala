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

  // Hilfsmethoden zur Ausgabe als String
  val eol = sys.props("line.separator")

  def title(cellWidth:Int = 3, colNum:Int = 7): String =
    val list = Range(0, colNum).toList
    val res = list.map(x =>
                " " + 
                " " * ((cellWidth-1)/2) +
                ('A' + x).toChar +
                " ").mkString
    return res + eol

  def columns(cellWidth:Int = 3, colNum:Int = 7) = ("+" + "-" * cellWidth) * colNum + "+" + eol
  
  def pointsFirst(cellWidth:Int = 3, colNum:Int = 7) = (" " + (" " * ((cellWidth-1)/2)) + "5" + " ") * colNum + eol

  def pitchToString(cellWidth:Int = 3): String =
    val numOfCells = Range(0, col_num)
    val numOfRows = Range(0, row_num)

    val result = title(cellWidth, col_num) + columns(cellWidth, col_num) + numOfRows.map(x =>
      (numOfCells.map(y =>
        "|" + " " * (cellWidth/2) + getIndex(x, y).toString() + " " * (cellWidth/2)).mkString) + "|" + eol + columns(cellWidth, col_num)).mkString + pointsFirst(cellWidth, col_num)

    return result
  
  // Methode toString()
  override def toString = pitchToString()


// --- alte Methoden ---
// Methode, um neue leere Matrix mit n Zeilen und m Spalten zu erzeugen
/* def create_Matrix(rows:Int = 4, columns:Int = 7): Vector[Vector[Filling]] =
	Vector.tabulate(rows) { i =>
   	Vector.tabulate(columns) {j => Filling.empty}
  } */

// Hilfsmethoden zur Ausgabe als String
/* def lines(cellWidth:Int = 3, colNum:Int = 7) = ("|" + " " * cellWidth) * colNum + "|" + eol */
/*   def pointsNext(cellWidth:Int = 3, colNum:Int = 7) = (" " + (" " * ((cellWidth-1)/2)) + "3" + " ") * colNum + eol */

// Methode zur Ausgabe eines leeren Felds
/*   def emptyPitchToString(cellWidth:Int = 3, colNum:Int = 7, lineNum:Int = 4) = 
    title(cellWidth, colNum) + (columns(cellWidth, colNum) + lines(cellWidth, colNum)) * lineNum + 
    columns(cellWidth, colNum) + pointsFirst(cellWidth, colNum) + pointsNext(cellWidth, colNum) */

  //def title(cellWidth:Int = 3, colNum:Int = 7) = (" " + (" " * ((cellWidth-1)/2)) + "A" + " ") * colNum + eol

// altes print pitch mit for-Verkettung:
/*   def pitchToString(cellWidth:Int = 3): String =
    var res = title(cellWidth, col_num) + columns(cellWidth, col_num)
    for (i <- 1 to row_num) {
      res += "|"
        for (j <- 1 to col_num) {
          res += " " * (cellWidth/2) + getIndex(i-1, j-1).toString() + " " * (cellWidth/2) + "|"
        }
      res += eol + columns(cellWidth, col_num)
    }
    return res + pointsFirst(cellWidth, col_num) */

// altes print pitch mit einer for-Schleife:
/*     def pitchToString(cellWidth:Int = 3): String =
    var res = title(cellWidth, col_num) + columns(cellWidth, col_num)
    val numOfCells = Range(0, col_num)
    for (i <- 1 to row_num) {
      res += numOfCells.map(x =>
                          "| " + getIndex(i-1, x).toString() + " ").mkString
      res += "|" + eol + columns(cellWidth, col_num)
    }
    return res + pointsFirst(cellWidth, col_num) */