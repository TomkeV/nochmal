package de.htwg.se.nochmal
package model

case class PitchAsMatrix(matrix: Vector[Vector[Filling]]):
  val row_num = matrix.size 			// Anzahl Zeilen der Matrix
  val col_nun = matrix.head.size	// Anzahl Spalten der Matrix

	// Konstruktor
  def this(rows:Int=4, columns:Int=7) =
    this(Vector.tabulate(rows) {i =>
    Vector.tabulate(columns) {j => Filling.empty}
    })
	
	// Methode für Zugriff auf Index:

	// Methode zum Füllen von Feldern:

// --- Methode, die neue leere Matrix mit n Zeilen und m Spalten erzeugt ---
/* def create_Matrix(rows:Int = 4, columns:Int = 7): Vector[Vector[Filling]] =
	Vector.tabulate(rows) { i =>
   	Vector.tabulate(columns) {j => Filling.empty}
  } */
