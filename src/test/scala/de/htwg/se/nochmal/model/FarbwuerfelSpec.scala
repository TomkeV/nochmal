package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FarbwuerfelSpec extends AnyWordSpec {
  "The dice of colors " when {
    "created " should {
      "roll 3 dice if there is no argument " in {
        val testDice = Colors_dice()
        testDice.num_of_dices should be(3)
      }
      "be scalable in the number of dices to roll " in {
        val testDice = Colors_dice(1)
        testDice.num_of_dices should be(1)
      }
      "save its number of dices to roll for the argument its called with " in {
        val testDice = Colors_dice(1)
        testDice.num_of_dices should be(1)
      }
    }
    "rolled " should {
      "return 'Du brauchst mindestens einen Wuerfel' if the object was initialized with a number of dice less than 1 " in {
        val testDice = Colors_dice(0)
        val s = testDice.roll_dice()
        s should be("Du brauchst mindestens einen Wuerfel!")
      }
      "return its result as a String in form 'farbe'" in {
        val testDice = Colors_dice(1)
        val s = testDice.roll_dice()
        s should fullyMatch regex """(rot|orange|gelb|gruen|blau|Joker!)"""
      }
      "return its result in the form " in {
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