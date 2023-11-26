package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

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
    }
}