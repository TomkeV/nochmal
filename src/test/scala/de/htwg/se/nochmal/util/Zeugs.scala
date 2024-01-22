package de.htwg.se.nochmal
package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import controller.controllerComponent.ControllerInterface
import BluePitch.ControllerInterface
class BluePitchSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  "A blue pitch " should {
    "have a blue background " in {
      testController.pitch.pitchColor.getColor() should be ("b")
    }
  }
  
}

import OrangePitch.ControllerInterface
class OrangePitchSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  "An orange pitch " should {
    "have an orange background " in {
      testController.pitch.pitchColor.getColor() should be ("o")
    }
  }
}

import YellowPitch.ControllerInterface
class YellowPitchSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  "An yellow pitch " should {
    "have an yellow background " in {
      testController.pitch.pitchColor.getColor() should be ("y")
    }
  }
}