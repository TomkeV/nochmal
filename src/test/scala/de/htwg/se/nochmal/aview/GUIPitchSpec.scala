/**
  * GUISpielfeldSpec.scala
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
import scala.swing.GridPanel

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class GUIPitchSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  val testButtonMap = ButtonMap(testController)
  val testGUIfeld = GUIPitch(testController, testButtonMap)
  "The GUI pitch " should {
    "create a titel line as a GridPanel " in {
      testGUIfeld.createTitle(1) shouldBe a [GridPanel]
    }
    "create a line with points as a GridPanel " in {
      testGUIfeld.createPoints(1) shouldBe a [GridPanel]
    }
    "create its pitch as a matrix of buttons in a GridPanel " in {
      testGUIfeld.createMatrixFromButtonMap(1, 1) shouldBe a [GridPanel]
    }
    "have success in updating its matrix" in {
      testGUIfeld.updatePitch() should be (true)
    }
  }
}
