package de.htwg.se.nochmal
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.Numbers_dice
import model.Colors_dice
import model.PitchAsMatrix


// ACHTUNG: FUNKTIONIERT NICHT!
class PitchAsMatrixSpec extends AnyWordSpec {
  "The Controller" should {
    val testPitch = new PitchAsMatrix(4, 7)
    val testcontrol = Controller(testPitch, new Numbers_dice, new Colors_dice)
    "fill a cell when the user decides to make a cross " in {
      testcontrol.set(1, 1)
      val s = testPitch.getIndex(1, 1) 
      s.toString() should be("x")
    }
    "roll some dice when the user decides to dice " in {
      testcontrol.dice() should fullyMatch regex """([1-5!]\R){2}[1-5!]\n((rot|orange|gelb|gruen|blau|Joker!)\n){2}(rot|orange|gelb|gruen|blau|Joker!)""""
    }
  }
}