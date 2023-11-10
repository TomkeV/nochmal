package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class FarbwuerfelSpec extends AnyWordSpec {
  "A colored dice" should {
    val test_dice = Colors_dice()
    val small_test_dice = Colors_dice(1)
    "save its number of colors to dice" in {
        test_dice.num_of_dices should be(3)
        small_test_dice.num_of_dices should be(1)
    }

/*     "return its results in form " +
    "color" +
    "color" +
    "color" in {
      test_dice.roll_dice() should be(/*regex*/)
    } */
  }
}