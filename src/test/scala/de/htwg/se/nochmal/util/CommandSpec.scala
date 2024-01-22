/* /**
  * CommandSpec.scala
  * @author Tomke Velten
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
// ------------------------------------------------------------------------------------------------ TEST
class CommandSpec extends AnyWordSpec {
  val undoManager = new UndoManager[Int]
  "The UndoManager " when {
    "undoing a move " should {
      "return the input if the stack is empty " in {
        undoManager.undoMove(1) should be (1)
      }
      "undo the last move if the stack is not empty " in {
        undoManager.undoMove(1)
      }
    }
    "redoing a move " should {
      "return the input if the stack is empty " in {
        undoManager.redoMove(1) should be (1)
      }
    }
  }
} */