package de.htwg.se.nochmal
package controller

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import model.Numbers_dice
import model.Colors_dice
import model.PitchAsMatrix
import model.Filling
import util.Event
import util.Observer
import model.dice.diceImplementierung.Colors_dice
import model.dice.diceImplementierung.Numbers_dice

class ControllerSpec extends AnyWordSpec {
  "The Controller" should {
    val testPitch = new PitchAsMatrix(4, 7)
    val testControl = Controller(testPitch, new Numbers_dice, new Colors_dice)
/*     "fill a cell when the user decides to make a cross " in {
      val changedPitch = testControl.set(1, 1)
      changedPitch.getIndex(0, 0) should be (Filling.filled)
    } */
    "roll some dice when the user decides to dice " in {
      val res = testControl.dice()
      res should startWith("Deine Wuerfelergebnisse: ")
      res should include regex """([1-5!]\R){2}[1-5!]"""
      res should include regex """((rot|orange|gelb|gruen|blau|Joker!)\R)+"""
    }
    "quit the game when the user enteres 'q' " in {
      val res = testControl.beQuit()
      res should be ("Danke fuers Spielen!")
    }
    "have an option to print the recent version of the pitch " in {
      val smallTestPitch = new PitchAsMatrix(1, 1)
      val smallTestControl = Controller(smallTestPitch, new Numbers_dice(1), new Colors_dice(1))
      val eol = sys.props("line.separator")
      val res = smallTestControl.toString
      res should be ("  A " + eol + "+---+" + eol + "|   |" + eol + "+---+" + eol + "  5 " + eol)
    }
/*     "notify its observers on change " in {
      class TestObserver(controller: Controller) extends Observer:
        controller.add(this)
        var bing = false
        def update(e: Event) = bing = true
      val testObserver = TestObserver(testControl)
      testObserver.bing should be (false)
      testControl.publishCross("x 1 1")
      testObserver.bing should be (true)
      testObserver.bing = false
      testControl.publishDice()
      testObserver.bing should be (true)
      testObserver.bing = false
      testControl.publishQuit()
      testObserver.bing should be (true)
    } */
  }
}