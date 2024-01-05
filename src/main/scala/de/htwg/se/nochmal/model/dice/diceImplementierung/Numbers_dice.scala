/**
  * Numbers_dice.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package diceComponent
package diceImplementierung

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class Numbers_dice(num_of_dices:Int = 3) extends DiceInterface {
  def roll_dice(): String =
    if (num_of_dices < 1) then
      return "Du brauchst mindestens einen Wuerfel!"
    else 
      val randomizer = new scala.util.Random
      val n = (1 to num_of_dices).toList
      val diced_ints = n.map(i => randomizer.nextInt(6)+1)
      
      val eol = sys.props("line.separator")
      diced_ints.map((x:Int) => if(x == 6) then "!" else x.toString()).mkString(sep = eol)
}