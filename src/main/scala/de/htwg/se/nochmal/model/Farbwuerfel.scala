package de.htwg.se.nochmal
package model

case class Colors_dice(anzahl:Int = 3):
  val wuerfelzahl = anzahl
  
  def roll_dice(): String =
    if (anzahl < 1) {
      return "Du brauchst mindestens einen Wuerfel!"
    } else {
      val randomizer = new scala.util.Random
      val n = (1 to anzahl).toList
      val diced_colors = n.map(i => randomizer.nextInt(6))
      
      val eol = sys.props("line.separator")
      var wuerfelergebnis = ""
      for (y <- diced_colors) {
        y match {
        case 0 => wuerfelergebnis += "rot" + eol
        case 1 => wuerfelergebnis += "orange" + eol
        case 2 => wuerfelergebnis += "gelb" + eol
        case 3 => wuerfelergebnis += "grün" + eol
        case 4 => wuerfelergebnis += "blau" + eol
        case 5 => wuerfelergebnis += "Joker!" + eol
        }
      }
      return wuerfelergebnis
    }