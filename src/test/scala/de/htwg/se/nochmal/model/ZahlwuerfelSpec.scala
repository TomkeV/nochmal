package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class ZahlwuerfelSpec extends AnyWordSpec {
  "A dice of numbers" should {
    val test_dice = Numbers_dice()
    val small_test_dice = Numbers_dice(1)
    "save its number of numbers to dice " in {
      test_dice.num_of_dices should be(3)
      small_test_dice.num_of_dices should be(1)
    }

/*     "return its results in form" + 
    "1" +
    "2" +
    "3" in {
      test_dice.roll_dice() should be(/*regex*/)
    } */
  }
}