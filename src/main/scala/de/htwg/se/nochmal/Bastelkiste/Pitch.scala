package de.htwg.se.nochmal
package Bastelkiste

case class pitch(rows:Int = 4, columns:Int = 7, width:Int = 3):
  val eol = sys.props("line.separator")

  def title() = 
    (" " + (" " * ((width-1)/2)) + "A" + " ") * columns + eol

  def print_cols() = 
    ("+" + "-" * width) * columns + "+" + eol

  def print_rows() = 
    ("|" + " " * width) * columns + "|" + eol

  def pointsFirst() =
    (" " + (" " * ((width-1)/2)) + "5" + " ") * columns + eol

  def pointsNext() =
    (" " + (" " * ((width-1)/2)) + "3" + " ") * columns + eol

  def pitchToString() = 
    title() + (print_cols() + print_rows()) * rows + print_cols() + pointsFirst() + pointsNext()