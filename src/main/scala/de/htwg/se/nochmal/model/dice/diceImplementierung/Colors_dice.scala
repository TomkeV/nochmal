package de.htwg.se.nochmal
package model
package diceComponent
package diceImplementierung

case class Colors_dice(dice_num:Int = 3) extends DiceInterface:
  val num_of_dices = dice_num
  
  def roll_dice(): String =
    if (num_of_dices < 1) {
      return "Du brauchst mindestens einen Wuerfel!"
    } else {
      val randomizer = new scala.util.Random
      val n = (1 to num_of_dices).toList
      val diced_colors = n.map(i => randomizer.nextInt(6))

      val eol = sys.props("line.separator")
      diced_colors.map(x => 
        x match 
          case 0 => "rot"
          case 1 => "orange"
          case 2 => "gelb" 
          case 3 => "gruen"
          case 4 => "blau"
          case 5 => "Joker!"
          ).mkString(sep = eol)
    }
