package de.htwg.se.nochmal
package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class PitchAsMatrixSpec extends AnyWordSpec {
  "A Matrix is a data type which is a vector of multiple vectors. A Matrix" when {
/*    "created for test purposes " should {
       "be a Vector of Vectors filled empty " in {
        val testM = new PitchAsMatrix(Vector[Vector[Filling.empty]])
        testM.row_num should be(1)
        testM.col_num should be(1)
        testM.getIndex(0, 0) should be(Filling.empty)
      }  */
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
    }
  }
}