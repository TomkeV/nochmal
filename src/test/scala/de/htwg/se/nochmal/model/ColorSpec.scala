// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class ColorSpec extends AnyWordSpec {
  "The Color " should {
    val field_red = Color.red
    val field_orange = Color.orange
    val field_yellow = Color.yellow
    val field_green = Color.green
    val field_blue = Color.blue 
    "have an overwritten toString() method " in {
        field_red.toString() should be("r")
        field_orange.toString() should be("o")
        field_yellow.toString() should be("y")
        field_green.toString() should be("g")
        field_blue.toString() should be("b")
    }
    "have an method to get the rgb-int-code " in {
      field_red.getRGB should be(220*65536 + 26 * 256 + 52)
      field_orange.getRGB should be(255*65536 + 140 * 256 + 0)
      field_yellow.getRGB should be(253*65536 + 219*256 + 0)
      field_green.getRGB should be(85*65536 + 203*256 + 7)
      field_blue.getRGB should be(0*65536 + 171*256 + 255)
    }
  }
}