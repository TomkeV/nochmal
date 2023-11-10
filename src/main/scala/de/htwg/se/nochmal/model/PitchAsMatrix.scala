package de.htwg.se.nochmal
package model

case class PitchAsMatrix(matrix: Vector[Vector[Filling]]):
  def this(rows:Int=4, columns:Int=7) = 
    this(Vector.tabulate(rows) {i =>
    Vector.tabulate(columns) {j => Filling.empty}
  })



// --- Methode, die neue leere Matrix mit n Zeilen und m Spalten erzeugt ---
/* def create_Matrix(rows:Int = 4, columns:Int = 7): Vector[Vector[Filling]] =
	Vector.tabulate(rows) { i =>
   	Vector.tabulate(columns) {j => Filling.empty}
  } */
