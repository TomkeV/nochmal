package de.htwg.se.nochmal.model

case class PitchMatrix[T](rows: Vector[Vector[T]]):
  def this(lineNum:Int = 4, colNum:Int = 7, filling:T = " ") = this(Vector.tabulate(lineNum, colNum) { (row, col) => filling })
  val lines: Int = rows.size
  val columns: Int = rows.size  // noch falsch! Wie greife ich auf Anzahl Einträge im Vektor zu? 

  def cell(row: Int, col: Int): T = rows(row)(col)
  def fill(filling: T): PitchMatrix[T] = copy(Vector.tabulate(lines, columns) { (row, col) => filling })

  def replaceCell(row: Int, col: Int, cell: T): PitchMatrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))