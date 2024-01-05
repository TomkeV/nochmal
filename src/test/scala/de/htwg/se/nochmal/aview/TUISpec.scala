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
import de.htwg.se.nochmal.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.nochmal.model.pitchComponent.baseModel.PitchAsMatrix
import de.htwg.se.nochmal.model.diceComponent.diceImplementierung.Numbers_dice
import de.htwg.se.nochmal.model.diceComponent.diceImplementierung.Colors_dice


class TUISpec extends AnyWordSpec {
  "The TUI" when {
    val myController = Controller(new PitchAsMatrix(1,1), new Numbers_dice, new Colors_dice)
    val testTUI = TUI(myController)
    "asked to quit game  by entering 'q'" should {
      "print 'Danke fuers Spielen!' " in {
        //testTUI.play(()=> "q") should be ("Danke fuers Spielen!")
      }
    }
/*    "asked to roll dice by entering 'w'" should {
      "print its dice results in form '" +
        "Zahl " +
        "Zahl " +
        "Zahl " +
        "Farbe " +
        "Farbe " +
        "Farbe " in {
          testTUI.play(()=>"w") should fullyMatch regex """([1-5!]\R){2}[1-5!]\n((rot|orange|gelb|gruen|blau|Joker!)\n){2}(rot|orange|gelb|gruen|blau|Joker!)"""
          testTUI.play(() => "q")
      }
    } */
/*        "asked to cross cell by entering 'x'" should {
        "always cross the first cell for test purposes " in {
          val eol = sys.props("line.separator")
          testTUI.play(()=> "x 1 1") should be ("+---+" + eol + "| X |" + eol + "+---+")
          testTUI.play(() => "q")
        }
    }    */
  }
}