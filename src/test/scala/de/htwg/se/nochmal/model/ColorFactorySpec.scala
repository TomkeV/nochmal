package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import de.htwg.se.nochmal.util.BlueField
import de.htwg.se.nochmal.util.RedField
import de.htwg.se.nochmal.util.OrangeField
import de.htwg.se.nochmal.util.YellowField
import de.htwg.se.nochmal.util.GreenField

class ColorFactorySpec extends AnyWordSpec {
  "The ColorFactory" should {
    "be able to create red fields with an color representation " in {
      val testField = RedField()
      testField.color() should be ("r")
    }
    "be able to create orange fields with an color representation " in {
      val testField = OrangeField()
      testField.color() should be ("o")
    }
    "be able to create yellow fields with an color representation " in {
      val testField = YellowField()
      testField.color() should be ("y")
    }
    "be able to create green fields with an color representation " in {
      val testField = GreenField()
      testField.color() should be ("g")
    }
    "be able to create blue fields with an color representation " in {
      val blueField = BlueField()
      blueField.color() should be ("b")
    }
  }
}