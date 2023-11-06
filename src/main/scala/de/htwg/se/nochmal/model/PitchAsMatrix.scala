package de.htwg.se.nochmal
package model

// --- Enum Filling ---
enum Filling(s:String):
  override def toString(): String = s
  case filled extends Filling("X")
  case empty extends Filling("_")

// --- Methode, die neue leere Matrix mit n Zeilen und m Spalten erzeugt ---
def create_Matrix(rows:Int = 4, columns:Int = 7): Vector[Vector[Filling]] =
	Vector.tabulate(rows) { i =>
   	Vector.tabulate(columns) {j => Filling.empty}
  }

// --- Methode, die Vektor von Vektoren als solchen ausgibt ---
/* def print_Matrix(m:Matrix) =
	m.foreach(e => println(e.toString)) */