package de.htwg.se.nochmal
package model

// Klasse für Zahlenwürfel
case class Numbers_dice(dice_num:Int = 3):
  val num_of_dices = dice_num
  
  def roll_dice(): String =
    if (num_of_dices < 1) {
      return "Du brauchst mindestens einen Wuerfel!"
    } else {
      val randomizer = new scala.util.Random
      val n = (1 to num_of_dices).toList
      val diced_ints = n.map(i => randomizer.nextInt(6)+1)
      
      val eol = sys.props("line.separator")
      var dice_result = ""
      for (x <- diced_ints) {
        if (x == 6) {
          dice_result += "!" + eol
        }
        if (x < 6) {
          dice_result += x.toString() + eol
        }
      }
      return dice_result
    }