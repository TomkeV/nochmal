// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheksimports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// interne imports
import controller.controllerComponent.ControllerInterface
import BluePitch.ControllerInterface
// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class BluePitchSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  "A blue pitch " should {
    "have a blue background " in {
      testController.pitch.pitchColor.getColor() should be ("b")
    }
  }
}

// interne imports
import OrangePitch.ControllerInterface
// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class OrangePitchSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  "An orange pitch " should {
    "have an orange background " in {
      testController.pitch.pitchColor.getColor() should be ("o")
    }
  }
}

// interne imports
import YellowPitch.ControllerInterface
// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class YellowPitchSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  "An yellow pitch " should {
    "have an yellow background " in {
      testController.pitch.pitchColor.getColor() should be ("y")
    }
  }
}