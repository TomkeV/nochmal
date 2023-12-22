package de.htwg.se.nochmal
package util

//import controller.controllerBaseImpl.Controller
import scala.util.Success
import scala.util.Failure
import de.htwg.se.nochmal.model.Cross
import scala.util.Try
import controller.ControllerInterface

trait HandlerInterface:
    def handleInput(input: String, controller:ControllerInterface): Unit

abstract class ChainHandler extends HandlerInterface:
    protected var nextHandler: ChainHandler


class QuitHandler() extends ChainHandler {
    var nextHandler = UndoHandler()
    override def handleInput(input: String, controller:ControllerInterface): Unit = {
        if (input == "q") {
            controller.publish(e = Event.Quit)
        } else {
            nextHandler.handleInput(input: String, controller:ControllerInterface)
        }
    }
}


// NEU für Undo-Manager
class UndoHandler() extends ChainHandler {
    var nextHandler = RedoHandler()
    override def handleInput(input: String, controller: ControllerInterface): Unit = {
        if (input == "u") {
            controller.publish(e = Event.Undone)
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}


class RedoHandler() extends ChainHandler {
    var nextHandler = DiceHandler()
    override def handleInput(input: String, controller: ControllerInterface): Unit = {
        if (input == "r") {
            controller.publish(e = Event.Redone)
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}

class DiceHandler() extends ChainHandler {
    var nextHandler = ApplyHandler()
    override def handleInput(input: String, controller:ControllerInterface): Unit = {
        if (input == "w") {
            controller.publish(e = Event.Diced)
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}

class ApplyHandler() extends ChainHandler {
    var nextHandler = RestHandler()

    override def handleInput(input: String, controller: ControllerInterface): Unit = 
        if(input == "a") {
            controller.publish(e = Event.Applied)
        } else {
            nextHandler.handleInput(input, controller)
        }
}

class RestHandler() extends ChainHandler {
    var nextHandler = null;
    override def handleInput(input: String, controller: ControllerInterface): Unit = {
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
          case _ => println("Ungueltige Eingabe!")
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


object InputHandler {
    def handle(i: String, c: ControllerInterface) = 
        QuitHandler().handleInput(i, c)
}
