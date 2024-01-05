/**
  * PitchAsMatrixSpec.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package pitchComponent
package baseModel

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class PitchWithColorsSpec extends AnyWordSpec {
  "A pitch with Colors should be a list of lists holding the colors of the cells. It " should {
    val testPitch = PitchWithColors(blackColorsList)
    "be able to return one of its lists " in {
      testPitch.getLine(0) should be (List(Color.green, 
                                           Color.green, 
                                           Color.green,
                                           Color.yellow, 
                                           Color.yellow,
                                           Color.yellow,
                                           Color.yellow, 
                                           Color.green,  
                                           Color.blue, 
                                           Color.blue, 
                                           Color.blue,
                                           Color.orange, 
                                           Color.yellow,
                                           Color.yellow,
                                           Color.yellow))

    }
    "be able to return one of its int-representations of rgb" in {
      testPitch.getIntIndex(0, 0) should be(Color.green.getRGB)
    }
    "be able to return one of its cells as a string" in {
      testPitch.getStrIndex(0, 0) should be(Color.green.toString())
    }
  }
}