/**
  * Colors_diceSpec.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package diceComponent
package diceImplementierung

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class Colors_diceSpec extends AnyWordSpec {
  "The dice of colors " when {
    "created " should {
      "roll 3 dice if there is no argument given " in {
        val testDice = Colors_dice()
        testDice.num_of_dices should be(3)
      }
      "be scalable in the number of dice to roll " in {
        val testDice = Colors_dice(1)
        testDice.num_of_dices should be(1)
      }
    }
    "rolled " should {
      "return 'Du brauchst mindestens einen Wuerfel' if it was initialized with a number less than 1 " in {
        val testDice = Colors_dice(0)
        val s = testDice.roll_dice()
        s should be("Du brauchst mindestens einen Wuerfel!")
      }
      "return its result as a String in the form 'farbe' " in {
        val testDice = Colors_dice(1) 
        val res = testDice.roll_dice()
        res should fullyMatch regex """(rot|orange|gelb|gruen|blau|Joker!)"""
      }
      "return its result in the form 'farbe\n farbe \n farbe\n'" in {
        val testDice = Colors_dice()
        val s = testDice.roll_dice()
        s should startWith regex """(rot|orange|gelb|gruen|blau|Joker!)"""
        s should include regex """((rot|orange|gelb|gruen|blau|Joker!)\R)+"""
      }
    }
    "created for test purposes " should {
      "be created with 100 dice to match all colors " in {
        val testDice = Colors_dice(100)
        val s = testDice.roll_dice()
        s should include regex """((rot|orange|gelb|gruen|blau|Joker!)\R)+"""
      }
    }
  }
}