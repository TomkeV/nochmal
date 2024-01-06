/**
  * PitchBuilderSpec.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// interne imports
import model.pitchComponent.baseModel.PitchAsMatrix
import model.Cross

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------- VARIABLEN
val eol = sys.props("line.separator")
val testPitch = new PitchAsMatrix(1, 1, 3)

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------ TITLE TEST
class TitleSpec extends AnyWordSpec {
  "The title " when {
    "created for only one column " should {
      "be 'A' with only one space around for cellWidth = 0 " in {
        val testTitle = Title(0, 1)
        testTitle.res should be(" A " + eol)
      }
      "be 'A' with multiple spaces around for cellWidth > 0 " in {
        val testC2 = Title(2, 1)
        val testC3 = Title(3, 1)
        val testC5 = Title(5, 1) 
        testC2.res should be(" A " + eol)
        testC3.res should be("  A " + eol)
        testC5.res should be("   A " + eol)

      }
    }
    "created for multiple columns " should {
      "display the alphabet in the form '  A   B   C '" in {
        val testTitle = Title(3, 3) 
        testTitle.res should be ("  A   B   C " + eol)
      }
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------ MATRIX TEST
class MatrixSpec extends AnyWordSpec {
  "The matrix " when {
    "created without colors inside " should {
      val testM = Matrix(3, 1, 1, testPitch)
      "be able to create a grid in the form '+---+---+---+---+---+---+---+' " in {
        testM.columns() should be("+---+---+---+---+---+---+---+" + eol)
      }
      "be able to scale its grid to '+---+' " in {
        testM.columns(3, 1) should be("+---+" + eol)
      }
      "create a String representation in the form" +
        "+---+" +
        "|   |" +
        "+---+" in {
        testM.res should be("+---+" + eol + "|   |" + eol + "+---+" + eol)
      }
    }
    "created with colors inside " should {
      val testMC = MatrixWithColors(3, 1, 1, testPitch)
      "be able to create a grid in the form '+---+---+---+---+---+---+---+' " in {
        testMC.columns() should be("+---+---+---+---+---+---+---+" + eol)
      }
      "be able to scale its grid to '+---+' " in {
        testMC.columns(3, 1) should be("+---+" + eol)
      }
      "create a String representation with a color inside in the form" +
      "+---+" +
      "| g |" +
      "+---+" +
      "when the cell is not crossed " in {
          testMC.res should be ("+---+" + eol + "| g |" + eol + "+---+" + eol)
      }
      "create a String representation with a cross inside in the form" +
      "+---+" +
      "| X |" +
      "+---+" +
      "when the cell is crossed " in {
        val crossedPitch = testPitch.fillCellWithCross(Cross(1, 1), testPitch)
        val MCcrossed = MatrixWithColors(3, 1, 1, crossedPitch)
        MCcrossed.res should be("+---+" + eol + "| X |" + eol + "+---+" + eol)
      }
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------ POINTS TEST
// ruft EvenOddHandler auf 
// -> Test dort

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class PitchBuilderSpec extends AnyWordSpec {
  "The PitchBuilder " when {
    "used to only create title and points " should {
      "return a String in the form" +
      " A " +
      " 5 " in {
        val myPitch = Pitch.builder(3, 1)
                            .createTitle(Title(3, 1))
                            .createPoints(Points(3, 1))
        myPitch.toString() should be("  A " + eol + "  5 " + eol)
      }
    }
    "used to create a pitch without colors " should {
      "return a String in the form" +
        "  A  " +
        "+---+" +
        "|   |" +
        "+---+" +
        "  5  " in {
        val myPitch = Pitch.builder(3, 1)
                            .createTitle(Title(3, 1))
                            .createMatrix(Matrix(3, 1, 1, testPitch))
                            .createPoints(Points(3, 1))
        myPitch.toString() should be("  A " + eol + "+---+" + eol + "|   |" + eol + "+---+" + eol + "  5 " + eol)
      }
    }
    "used to create a pitch with colors " should {
      "return a String in the form" +
        "  A  " +
        "+---+" +
        "| g |" +
        "+---+" +
        "  5  " in {
        val myPitch = Pitch.builder(3, 1)
                            .createTitle(Title(3, 1))
                            .createMatrixWithColors(MatrixWithColors(3, 1, 1, testPitch))
                            .createPoints(Points(3, 1))
        myPitch.toString() should be("  A " + eol + "+---+" + eol + "| g |" + eol + "+---+" + eol + "  5 " + eol)
      }
    }
  }
}