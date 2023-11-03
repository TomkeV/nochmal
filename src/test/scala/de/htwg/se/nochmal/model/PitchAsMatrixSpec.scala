package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PitchAsMatrixSpec extends AnyWordSpec {
  "A pitch as a matrix is a tailor-made immutable data type that contains a Vector with two dimensions. " +
    "The PitchAsMatrix" when { 
    "empty"should {
      "have four Vectors with seven places" in {
        val myPitch = new PitchMatrix()
        myPitch.lines should be(4)
        myPitch.columns should be(4)
      }
      "for test purposes only be be created with a Vector of Vectors" in {
        val testMatrix = PitchMatrix(Vector(Vector(" ")))
        testMatrix.lines should be(1)
        testMatrix.columns should be(1)
      }
    }
    "filled" should {
      val myPitch = new PitchMatrix[String] (2, 2, "x")
      "give access to its cells" in {
        myPitch.cell(0, 0) should be("x")
      }
      "be filled by using the fill operation" in {
        val returnedPitch = myPitch.fill("x")
        returnedPitch.cell(0, 0) should be("x")
      }
      "be changed by using the replaceCell operation" in {
        val myPitch = new PitchMatrix[String] (2, 2, "x")
        val returnedPitch = myPitch.replaceCell(0, 0, " ")
        returnedPitch.cell(0, 0) should be(" ")
      }
    }
  }
}