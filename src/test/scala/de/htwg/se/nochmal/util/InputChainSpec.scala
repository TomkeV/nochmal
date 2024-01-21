/**
  * InputChainSpec.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheksimport
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// interne imports
import controller.controllerComponent.controllerBaseImpl.Controller
import model.pitchComponent.baseModel.PitchAsMatrix
import model.diceComponent.diceImplementierung.Numbers_dice
import model.diceComponent.diceImplementierung.Colors_dice

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------- VARIABLEN
val testController = Controller(new PitchAsMatrix(1, 1, 3), Numbers_dice(1), Colors_dice(1))

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ QUITHANDLER TEST
class QuitHandlerSpec extends AnyWordSpec {
  "The QuitHandler " should {
    val qH = QuitHandler()
    "only handle the input if its 'q' " in {
      qH.handleInput("q", testController) should be (true)
      qH.handleInput("f", testController) should be (false)
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ SAVEHANDLER TEST
class SaveHandlerSpec extends AnyWordSpec {
  "The SaveHandler " should {
    val sH = SaveHandler()
    "only handle the input if its 's' " in {
      sH.handleInput("s", testController) should be (true)
      sH.handleInput("f", testController) should be (false)
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ QUITHANDLER TEST
class LoadHandlerSpec extends AnyWordSpec {
  "The LoadHandler " should {
    val lH = LoadHandler()
    "only handle the input if its 'l' " in {
      lH.handleInput("l", testController) should be (true)
      lH.handleInput("f", testController) should be (false)
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ UNDOHANDLER TEST
class UndoHandlerSpec extends AnyWordSpec {
  "The UndoHandler " should {
    val uH = UndoHandler()
    "only handle the input if its 'u' " in {
      uH.handleInput("u", testController) should be (true)
      uH.handleInput("f", testController) should be (false)
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ REDOHANDLER TEST
class RedoHandlerSpec extends AnyWordSpec {
  "The RedoHandler " should {
    val rH = RedoHandler()
    "only handle the input if its 'r' " in {
      rH.handleInput("r", testController) should be (true)
      rH.handleInput("f", testController) should be (false)
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ DICEHANDLER TEST
class DiceHandlerSpec extends AnyWordSpec {
  "The DiceHandler " should {
    val dH = DiceHandler()
    "only handle the input if its 'w' " in {
      dH.handleInput("w", testController) should be (true)
      dH.handleInput("f", testController) should be (false)
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------- APPLYHANDLER TEST
class ApplyHandlerSpec extends AnyWordSpec {
  "The ApplyHandler " should {
    val aH = ApplyHandler()
    "only handle the input if its 'a' " in {
      aH.handleInput("a", testController) should be (true)
      aH.handleInput("f", testController) should be (false)
    }
  }
} 

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ RESTHANDLER TEST
class RestHandlerSpec extends AnyWordSpec {
  "The RestHandler " when {
    val rH = RestHandler()
    "the input starts with 'x' " should {
      "handle it" in {
      rH.handleInput("xA1", testController) should be (true)
      }
      "check if the given coordinates are a valid cross " in {
        val inputList = List('A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 
                              'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l',
                              'M', 'm', 'N', 'n', 'O', 'o')
        for element <- inputList do
          rH.analyseInput(List('x', element)).isSuccess
        rH.analyseInput(List('x', 'Z')).isFailure
      } 
    }
    "the input does not match any of the handlers" should {
      "also handle it without any action " in{
        rH.handleInput("f", testController) should be (false)
      }
    }
  }
}

// -----------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------- INPUTHANDLER TEST
class InputHandlerSpec extends AnyWordSpec {
  "The InputHandler object " should {
    val iH = InputHandler
    "check if there is a valid input and " when {
      "there is a valid input return true " in {
        iH.handle("q", testController) should be (true)
      }
      "there is not a valid input return false" in {
        iH.handle("", testController) should be (false)
      }
    }
  }
}