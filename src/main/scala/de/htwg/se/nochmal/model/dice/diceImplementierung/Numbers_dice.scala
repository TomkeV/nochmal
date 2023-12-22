package de.htwg.se.nochmal
package model
package diceComponent
package diceImplementierung

// Klasse für Zahlenwürfel
case class Numbers_dice(dice_num:Int = 3) extends DiceInterface:
  val num_of_dices = dice_num
  
  def roll_dice(): String =
    if (num_of_dices < 1) {
      return "Du brauchst mindestens einen Wuerfel!"
    } else {
      val randomizer = new scala.util.Random
      val n = (1 to num_of_dices).toList
      val diced_ints = n.map(i => randomizer.nextInt(6)+1)
      
      val eol = sys.props("line.separator")
      diced_ints.map((x:Int) => if(x == 6) then "!" else x.toString()).mkString(sep = eol)
    }