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
class PitchAsMatrixSpec extends AnyWordSpec {
  "A Matrix is a data type which is a vector of multiple vectors. A Matrix" when {
    "created for normal use " should {
      "be created empty with a num of rows and a num of colums " in {
        val testM = new PitchAsMatrix(1, 1)
        testM.row_num should be(1)
        testM.col_num should be(1)
        testM.getIndex(0, 0) should be(Filling.empty)
      }
      val testM = new PitchAsMatrix(2, 2)
      val myCross = Cross(1, 1)
      "be able to return one column as a Vector of Filling" in {
        val col = testM.getColumn(0) 
        col shouldBe(Vector[Filling](Filling.empty, Filling.empty))
      }
      "be able to return the content of one cell " in {
        testM.getIndex(0,0) should be(Filling.empty)
      }
      "be able to change its cells from 'empty' to 'filled' " in {
        val returnedM = testM.fillCellWithCross(myCross, testM)
        returnedM.getIndex(1, 1) should be(Filling.filled)
      }
      "be able to change its cells from 'filled' to 'empty' to undo a move " in {
        val returnedM_filled = testM.fillCellWithCross(myCross, testM)
        val returnedM_emptied = returnedM_filled.unfillCellWithCross(myCross, returnedM_filled)
        returnedM_emptied.getIndex(1, 1) should be (Filling.empty)
      }
      val eol = sys.props("line.separator")
      "have a pitch in the form " + 
      " A " +
      "+-+" +
      "| |" + 
      "+-+" + 
      " 5 " in {
      val testM = new PitchAsMatrix(1, 1, 1)
      val s = testM.pitchToString(1)
      s should be(" A " + eol + "+-+" + eol + "| |" + eol + "+-+" + eol + " 5 " + eol )
      }
      "have an overwritten toString-method which also returns a pitch in the form " +
      "  A  " +
      "+---+" +
      "|   |" + 
      "+---+" + 
      "  5  " in {
      val testM = new PitchAsMatrix(1, 1)
      testM.toString should be("  A " + eol + "+---+" + eol + "|   |" + eol + "+---+" + eol + "  5 " + eol)
    } 
    }
  }  
}