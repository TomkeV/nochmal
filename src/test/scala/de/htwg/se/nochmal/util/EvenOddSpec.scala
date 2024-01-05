/**
  * EvenOddSpec.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------ TITLE TEST
class EvenOddSpec extends AnyWordSpec {
  "The EvenOdd object should create points in different ways depending on the number of columns. " when {
    "there is an even number of columns it " should {
      "create points in the form '5 3 2 1 1 2 3 5' " in {
        val testPoints = Points(3, 8)
        testPoints.res should be ("  5   3   2   1   1   2   3   5 " + eol)
      }
    }
    "there is an odd number of columns it " should {
      "create points in the form '5 3 2 1 2 3 5' " in {
        val testPoints = Points(3, 7)
        testPoints.res should be ("  5   3   2   1   2   3   5 " + eol)
      }
    }
  }
}