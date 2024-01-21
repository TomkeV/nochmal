/**
  * ControllerSpec.scala
  * @author: Tomke Velten
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package controller
package controllerComponent
package controllerBaseImpl

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne imports
import model.pitchComponent.baseModel.PitchAsMatrix
import model.diceComponent.diceImplementierung.Numbers_dice
import model.diceComponent.diceImplementierung.Colors_dice

// Bibliotheksimports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class ControllerSpec extends AnyWordSpec {
  "The Controller " should {
    "ensure that no crosses be saved after apply " in {
      val controller = new Controller(new PitchAsMatrix(2, 2), new Numbers_dice(), new Colors_dice)
      controller.apply()
      crossArray shouldBe empty
    }
  }
}