package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

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
      "be able to return one column as a Vector of Filling" in {
        val col = testM.getColumn(0) 
        col shouldBe(Vector[Filling](Filling.empty, Filling.empty))
      }
      "be able to return the content of one cell " in {
        testM.getIndex(0,0) should be(Filling.empty)
      }
      "be able to change its cells from 'empty' to 'filled' " in {
        val returnedM = testM.fillCell(1, 1)
        returnedM.getIndex(1, 1) should be(Filling.filled)
      }
      "be able to change its cells from 'filled' to 'empty' to undo a move " in {
        val returnedM_filled = testM.fillCell(1, 1)
        val returnedM_emptied = returnedM_filled.unfillCell(1, 1)
        returnedM_emptied.getIndex(1, 1) should be (Filling.empty)
      }
    }
    "converted in Strings " should {
      val eol = sys.props("line.separator")
      "have a bar as String of Form '+---+---+---+---+---+---+---+' " in {
        val testM = new PitchAsMatrix(4, 7)
        testM.columns() should be("+---+---+---+---+---+---+---+" + eol)
      }
      "have a scalable bar" in {
        val testM = new PitchAsMatrix(1, 1)
        val testM2 = new PitchAsMatrix(1, 2)
        val testM3 = new PitchAsMatrix(2, 1)
        testM.columns(1, 1) should be("+-+" + eol)
        testM2.columns(1, 2) should be("+-+-+" + eol)
        testM3.columns(2, 1) should be("+--+" + eol)
      }
      "have a title in the form A B C D E..." in {
        val testM = new PitchAsMatrix(3, 5)
        testM.title() should be ("  A   B   C   D   E   F   G " + eol)
      }
      "have points which " should {
        "be given in the form 5 3 2 1 2 3 5 for odd numbers " in {
          val testM = new PitchAsMatrix(4, 7) 
          testM.points() should be ("  5   3   2   1   2   3   5 " + eol)
        }
        "be given in the form 5 3 2 1 1 2 3 5 for even numbers " in {
          val testM = new PitchAsMatrix(4, 8)
          testM.points(3, 8) should be ("  5   3   2   1   1   2   3   5 " + eol)
        }
      }
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


// alte Testbedingungen:
/* "have cells as String of form '|   |   |   |   |   |   |   |' " in {
        val testM = new PitchAsMatrix(4, 7)
        testM.lines() should be("|   |   |   |   |   |   |   |" + eol)
      }
      "have scalable cells" in {
        val testM = new PitchAsMatrix(1, 1)
        val testM2 = new PitchAsMatrix(1, 2)
        val testM3 = new PitchAsMatrix(2, 1)
        testM.lines(1, 1) should be("| |" + eol)
        testM2.lines(1, 2) should be("| | |" + eol)
        testM3.lines(2, 1) should be("|  |" + eol)
      }
      "have a pitch in the form " + 
      " A " +
      "+-+" +
      "| |" + 
      "+-+" + 
      " 5 " +
      " 3" in {
        val testM = new PitchAsMatrix(1, 1)
        testM.pitchToString(1, 1, 1) should be(" A " + eol + "+-+" + eol + "| |" + eol + "+-+" + eol + " 5 " + eol + " 3 " + eol)
      } */