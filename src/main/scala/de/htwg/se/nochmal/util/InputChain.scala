package de.htwg.se.nochmal
package util

import controller.Controller
import scala.util.Success
import scala.util.Failure
import de.htwg.se.nochmal.model.Cross
import scala.util.Try

trait HandlerInterface:
    def handleInput(input: String, controller:Controller): Unit

abstract class ChainHandler extends HandlerInterface:
    protected var nextHandler: ChainHandler


class QuitHandler() extends ChainHandler {
    var nextHandler = UndoHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "q") {
            controller.publish(None, Event.Quit)
        } else {
            nextHandler.handleInput(input: String, controller:Controller)
        }
    }
}


// NEU für Undo-Manager
class UndoHandler() extends ChainHandler {
    var nextHandler = RedoHandler()
    override def handleInput(input: String, controller: Controller): Unit = {
        if (input == "u") {
            controller.publish(None, Event.Undone)
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}


class RedoHandler() extends ChainHandler {
    var nextHandler = DiceHandler()
    override def handleInput(input: String, controller: Controller): Unit = {
        if (input == "r") {
            controller.publish(None, Event.Redone)
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}

class DiceHandler() extends ChainHandler {
    var nextHandler = ApplyHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "w") {
            controller.publish(None, Event.Diced)
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}

class ApplyHandler() extends ChainHandler {
    var nextHandler = RestHandler()

    override def handleInput(input: String, controller: Controller): Unit = 
        if(input == "a") {
            controller.publish(None, Event.Applied)
        } else {
            nextHandler.handleInput(input, controller)
        }
}

class RestHandler() extends ChainHandler {
    var nextHandler = null;
    override def handleInput(input: String, controller: Controller): Unit = {
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
    def handle(i: String, c: Controller) = 
        QuitHandler().handleInput(i, c)
}


// Alte Methode analyseChars:
    //val chars = input.toLowerCase().toCharArray()
    //val inputString = analyseChars(chars)
    /*     def analyseChars(array:Array[Char]):String = 
        val range = Range(0, array.length).toList
        val tmpArray = range.map(i =>
          if(array(i).isDigit) {
            array(i)
          } else if (array(i).isLetter) {
            'x'
          } else {

          }
          )
        var resString = "" // noch nicht funktional programmiert!
        for (i <- 0 to array.length-1) {
            if (tmpArray(i) != ()) {
                resString += tmpArray(i)
        }
        }
        return resString */

/* class RestHandler() extends ChainHandler {
    var nextHandler = null;
    override def handleInput(input: String, controller:Controller): Unit = {
        val chars = input.toString().toList
        val inputString = chars.filter(_.isLetterOrDigit).mkString
        chars(0) match
          case 'x' => controller.publishCross(inputString)
          case ' ' => 
            chars(1) match
              case 'x' => controller.publishCross(inputString)
          case _ => println("Ungueltige Eingabe!")
    }
} */