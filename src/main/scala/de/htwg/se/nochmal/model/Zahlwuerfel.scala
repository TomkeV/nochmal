package de.htwg.se.nochmal
package model

// Klasse für Zahlenwürfel
case class Numbers_dice(anzahl:Int = 3):
  val wuerfelzahl = anzahl
  
  def roll_dice(): String =
    if (anzahl < 1) {
      return "Du brauchst mindestens einen Wuerfel!"
    } else {
      val randomizer = new scala.util.Random
      val n = (1 to anzahl).toList
      val diced_ints = n.map(i => randomizer.nextInt(6)+1)
      
      val eol = sys.props("line.separator")
      var wuerfelergebnis = ""
      for (x <- diced_ints) {
        if (x == 6) {
          wuerfelergebnis = wuerfelergebnis + "!" + eol
        }
        if (x < 6) {
          wuerfelergebnis = wuerfelergebnis + x.toString() + eol
        }
      }
      return wuerfelergebnis
    }