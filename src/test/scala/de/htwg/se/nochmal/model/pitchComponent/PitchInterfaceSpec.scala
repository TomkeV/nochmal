/**
  * PitchInterfaceSpec.scala
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package pitchComponent

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
// interne Imports
import model.pitchComponent.baseModel.PitchAsMatrix

// Bibliotheksimports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------- INTERFACE TEST
class PitchInterfaceSpec extends AnyWordSpec {
  val testInterface = new PitchAsMatrix(1, 1, 3)
  "The trait 'PitchInterface' " should {
    "be able to fill and unfill cells of the pitch returning another PitchInterface" in {
      val res = testInterface.fillCellWithCross(Cross(1, 1), testInterface)
      res shouldBe a [PitchInterface]
      val res2 = testInterface.unfillCellWithCross(Cross(1, 1), testInterface)
      res2 shouldBe a [PitchInterface]
    }
    "have an operation to convert a pitch into a String " in {
      val res = testInterface.pitchToString()
      res shouldBe a [String]
    }
    "allow to get one column as a Vector of Fillings " in {
      val res = testInterface.getColumn(0)
      res shouldBe a [Vector[Filling]]
    }
    "allow to get the Filling of one of its cells " in {
      val res = testInterface.getIndex(0, 0)
      res shouldBe a [Filling]
    }
  }
}