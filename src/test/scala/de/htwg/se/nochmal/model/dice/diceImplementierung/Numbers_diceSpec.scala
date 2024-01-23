/**
  * Numbers_diceSpec.scala
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
class Numbers_diceSpec extends AnyWordSpec {
  "The dice of numbers " when {
    "created " should {
      "roll 3 dice if there is no argument " in {
        val testDice = Numbers_dice()
        testDice.num_of_dices should be(3)
      }
      "be scalable in the number of dice to roll " in {
        val testDice = Numbers_dice(1)
        testDice.num_of_dices should be(1)
      }
      "save its number of dice to roll for the argument it's called with " in {
        val testDice = Numbers_dice(1)
        testDice.num_of_dices should be(1)
      }
    }
    "rolled " should {
      "return 'Du brauchst mindestens einen Wuerfel' if the object was initialized with a num of dices less than 1 " in {
        val testDice = Numbers_dice(0) 
        val s = testDice.roll_dice()
        s should be("Du brauchst mindestens einen Wuerfel!")
      }
      "return its result in the form " in {
        val testDice = Numbers_dice()
        val s = testDice.roll_dice()
        s should fullyMatch regex """([1-5!]\R){2}[1-5!]"""
      }
    }
  }
}