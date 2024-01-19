/**
  * InputChain.scala
  * Chain of Responsibility Pattern is used to 
  * analyse input given in TUI or GUI.
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheksimports
import scala.util.{Try, Success, Failure}

// interne imports
import controller.controllerComponent.ControllerInterface
import model.Cross

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITION
trait HandlerInterface:
    def handleInput(input: String, controller:ControllerInterface): Boolean

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
abstract class ChainHandler extends HandlerInterface:
    protected var nextHandler: ChainHandler

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class QuitHandler() extends ChainHandler {
    var nextHandler = SaveHandler()
    override def handleInput(input: String, controller:ControllerInterface): Boolean = {
        if (input == "q") {
            controller.publish(e = Event.Quit)
            true
        } else {
            nextHandler.handleInput(input, controller)
            false
        }
    }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class SaveHandler() extends ChainHandler {
    var nextHandler = LoadHandler()

    override def handleInput(input: String, controller: ControllerInterface): Boolean = {
        if (input == "s") {
            controller.publish(e = Event.Saved)
            true
        } else {
            nextHandler.handleInput(input, controller)
            false
        }
    }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class LoadHandler() extends ChainHandler {
    var nextHandler = UndoHandler()

    override def handleInput(input: String, controller: ControllerInterface): Boolean = {
        if (input == "l") {
            controller.publish(e = Event.Loaded)
            true
        } else {
            nextHandler.handleInput(input, controller)
            false
        }
    }
}


// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class UndoHandler() extends ChainHandler {
    var nextHandler = RedoHandler()
    override def handleInput(input: String, controller: ControllerInterface): Boolean = {
        if (input == "u") {
            println("InputChain: Undo erkannt")
            controller.publish(e = Event.Undone)
            true
        } else {
            nextHandler.handleInput(input, controller)
            false
        }
    }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class RedoHandler() extends ChainHandler {
    var nextHandler = DiceHandler()
    override def handleInput(input: String, controller: ControllerInterface): Boolean = {
        if (input == "r") {
            controller.publish(e = Event.Redone)
            true
        } else {
            nextHandler.handleInput(input, controller)
            false
        }
    }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class DiceHandler() extends ChainHandler {
    var nextHandler = ApplyHandler()
    override def handleInput(input: String, controller:ControllerInterface): Boolean = {
        if (input == "w") {
            controller.publish(e = Event.Diced)
            true
        } else {
            nextHandler.handleInput(input, controller)
            false
        }
    }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class ApplyHandler() extends ChainHandler {
    var nextHandler = RestHandler()

    override def handleInput(input: String, controller: ControllerInterface): Boolean = 
        if(input == "a") {
            controller.publish(e = Event.Applied)
            true
        } else {
            nextHandler.handleInput(input, controller)
            false
        }
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class RestHandler() extends ChainHandler {
    var nextHandler = null;
    override def handleInput(input: String, controller: ControllerInterface): Boolean = {
        val chars = input.toString().toList
        val inputList = chars.filter(_.isLetterOrDigit)
        chars(0) match
          case 'x' => val test = analyseInput(inputList)
            if(test.isSuccess) {
                val line = inputList(2).toString().toInt
                val col = test.get 
                val cross = new Cross(line, col)
                controller.publish(Some(cross), Event.Crossed)
            } else if (test.isFailure) {
                println(test.toString())
            }
            true
          case _ => println("Ungueltige Eingabe!")
                    false
    }

    def analyseInput(input:List[Char]): Try[Int] = {
        val x = input(1) match
            case 'A' | 'a' => Success(1)
            case 'B' | 'b' => Success(2)
            case 'C' | 'c' => Success(3)
            case 'D' | 'd' => Success(4)
            case 'E' | 'e' => Success(5)
            case 'F' | 'f' => Success(6)
            case 'G' | 'g' => Success(7)
            case 'H' | 'h' => Success(8)
            case 'I' | 'i' => Success(9)
            case 'J' | 'j' => Success(10)
            case 'K' | 'k' => Success(11)
            case 'L' | 'l' => Success(12)
            case 'M' | 'm' => Success(13)
            case 'N' | 'n' => Success(14)
            case 'O' | 'o' => Success(15)
            case _ => Failure(new Error("Keine gueltige Zeile!"))
        return x
    }
}

// -----------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------- OBJECT DEFINITION
object InputHandler {
    def handle(i: String, c: ControllerInterface): Boolean = 
        if (i.isEmpty()) then
            false
        else 
            QuitHandler().handleInput(i, c)
            true
}
