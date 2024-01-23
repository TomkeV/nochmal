/**
  * TUISpec.scala
  * Class for testing the text-based user-interface of the game "Nochmal!"
  * Link to the game: https://www.schmidtspiele.de/details/produkt/noch-mal-.html
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheks-Imports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// interne imports
import controller.controllerComponent.controllerBaseImpl.Controller
import model.pitchComponent.baseModel.PitchAsMatrix
import model.diceComponent.diceImplementierung.*
import util.Event
import controller.rounds


class TUISpec extends AnyWordSpec {
  val myController = Controller(new PitchAsMatrix(3, 3), new Numbers_dice, new Colors_dice)
  val testTUI = TUI(myController)
  "The TUI" should {
    "stop running when input was 'q' " in {
      testTUI.update(Event.Quit)
      testTUI.goOn should be (false)
    }
    "react to the Apply-Event with stopping the game if the maximal num of rounds is reached " in {
      rounds = myController.pitch.col_num * 2
      testTUI.update(Event.Applied)
      testTUI.goOn should be (false)
    }
  }
}