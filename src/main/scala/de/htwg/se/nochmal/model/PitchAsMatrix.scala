package de.htwg.se.nochmal
package model

case class PitchAsMatrix(matrix: Vector[Vector[Filling]]):
  val row_num = matrix.size 			// Anzahl Zeilen der Matrix
  val col_num = matrix.head.size	// Anzahl Spalten der Matrix

	// auxiliary construktor
  def this(rows:Int=4, columns:Int=7) =
    this(Vector.tabulate(rows) {i =>
    Vector.tabulate(columns) {j => Filling.empty}
    })

  // Methode für Zugriff auf Spalte:
  def getColumn(row:Int): Vector[Filling] =
    matrix(row)
	
	// Methode für Zugriff auf Index:
  def getIndex(row:Int, col:Int):Filling =
    matrix(row)(col)

	// Methode zum Füllen von Feldern:
  def fillCell(row:Int, col:Int):PitchAsMatrix = 
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))

// --- alte Methode, um neue leere Matrix mit n Zeilen und m Spalten erzeugt ---
/* def create_Matrix(rows:Int = 4, columns:Int = 7): Vector[Vector[Filling]] =
	Vector.tabulate(rows) { i =>
   	Vector.tabulate(columns) {j => Filling.empty}
  } */
