package de.htwg.se.nochmal
package aview

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import de.htwg.se.nochmal.model.PitchAsMatrix
import de.htwg.se.nochmal.model.Numbers_dice
import de.htwg.se.nochmal.model.Colors_dice
import controller.Controller

class TUISpec extends AnyWordSpec {
  "The TUI" when {
    val myController = Controller(new PitchAsMatrix(1,1), new Numbers_dice, new Colors_dice)
    val testTUI = TUI(myController)
    "asked to quit game  by entering 'q'" should {
      "print 'Danke fuers Spielen!' " in {
        testTUI.play(()=> "q") should be ("Danke fuers Spielen!")
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
/*     "asked to cross cell by entering 'x'" should {
        testTUI.play(()=> "q")
    } */
  }
}