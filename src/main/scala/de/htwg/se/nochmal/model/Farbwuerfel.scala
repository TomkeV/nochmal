package de.htwg.se.nochmal
package model

case class Colors_dice(dice_num:Int = 3):
  val num_of_dices = dice_num
  
  def roll_dice(): String =
    if (num_of_dices < 1) {
      return "Du brauchst mindestens einen Wuerfel!"
    } else {
      val randomizer = new scala.util.Random
      val n = (1 to num_of_dices).toList
      val diced_colors = n.map(i => randomizer.nextInt(6))
      
      val eol = sys.props("line.separator")
      var dice_result = ""
      for (y <- diced_colors) {
        y match {
        case 0 => dice_result += "rot" + eol
        case 1 => dice_result += "orange" + eol
        case 2 => dice_result += "gelb" + eol
        case 3 => dice_result += "grün" + eol
        case 4 => dice_result += "blau" + eol
        case 5 => dice_result += "Joker!" + eol
        }
      }
      return dice_result
    }