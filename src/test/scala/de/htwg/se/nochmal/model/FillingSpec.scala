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
class FillingSpec extends AnyWordSpec {
    "The Filling" should {
        val field_filled = Filling.filled
        val field_empty = Filling.empty
        "have an overwritten toString() method " in {
            field_filled.toString() should be("X")
            field_empty.toString() should be(" ")
        }
    }
}