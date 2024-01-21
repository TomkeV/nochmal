/**
  * ButtonMap.scala
  * Klasse zum Speichern einer Matrix aus Buttons
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne imports
import Default.ControllerInterface
import controller.controllerComponent.ControllerInterface

// Bibliotheksimports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._



// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class ButtonMapSpec extends AnyWordSpec {
  val controller = summon[ControllerInterface]
  "The ButtonMap " should {
    val testMap = new ButtonMap(controller)
    "implement the update to react to different events " in {
      
    }
  }
}
