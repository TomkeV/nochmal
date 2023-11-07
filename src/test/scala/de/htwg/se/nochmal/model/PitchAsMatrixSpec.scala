package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PitchAsMatrixSpec extends AnyWordSpec {
  "A Matrix" should {
    "be created as a Vector of Vectors which represents rows and columns" in {
      create_Matrix()
    }
    "be scalable in its size so that create_Matrix(1, 1) is a Vector of one other Vector with one entry" in {
      create_Matrix(1, 1) should be(Vector(Vector[Filling](Filling.empty)))
      create_Matrix(1, 2) should be(Vector(Vector[Filling](Filling.empty, Filling.empty)))
      create_Matrix(2, 1) should be(Vector(Vector[Filling](Filling.empty), Vector[Filling](Filling.empty)))
    }
  }
}