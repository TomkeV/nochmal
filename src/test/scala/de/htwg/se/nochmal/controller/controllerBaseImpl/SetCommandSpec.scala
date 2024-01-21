/**
  * SetCommandSpec.scala
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package controller
package controllerComponent
package controllerBaseImpl

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne imports
import model.pitchComponent.baseModel.PitchAsMatrix
import model.Cross
import model.Filling

// Bibliotheksimports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._



// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class SetCommandSpec extends AnyWordSpec {
  val pitch = new PitchAsMatrix(2, 2)
  val testCommand = new SetCommand(Cross(1, 1))
    "The SetCommand " should {
      "do just nothing when no move is done " in {
        val res = testCommand.noMove(pitch)
        res should equal (pitch)
      }
      "have an executable function that places a cross " in {
        val res = testCommand.doMove(pitch)
        res.getIndex(0, 0) should equal (Filling.filled)
      }
/*       "have an undo function that empties the cells which were crossed last " in {
        val res = testCommand.doMove(pitch)
        val resres = testCommand.undoMove(res)
        resres.getIndex(0, 0) should equal (Filling.empty)
      } */
/*       "have an redo function that repeats the last move " in {

      } */
    }
}