package de.htwg.se.nochmal
package util

import controller.Controller

trait HandlerInterface:
    def handleInput(input: String, controller:Controller): Unit

abstract class ChainHandler extends HandlerInterface:
    protected var nextHandler: ChainHandler


class QuitHandler() extends ChainHandler {
    var nextHandler = UndoHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "q") {
            controller.publishQuit()
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
            controller.publishUndo()
            //println("Undo!")
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}


// NEU für Redo-Manager
class RedoHandler() extends ChainHandler {
    var nextHandler = DiceHandler()
    override def handleInput(input: String, controller: Controller): Unit = {
        if (input == "r") {
            controller.publishRedo()
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}

class DiceHandler() extends ChainHandler {
    var nextHandler = ApplyHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "w") {
            controller.publishDice()
        } else {
            nextHandler.handleInput(input, controller)
        }
    }
}

class ApplyHandler() extends ChainHandler {
    var nextHandler = RestHandler()

    override def handleInput(input: String, controller: Controller): Unit = 
        if(input == "a") {
            controller.publishApply()
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
          case 'x' => controller.publishCross(inputList)
          case ' ' => 
            chars(1) match
              case 'x' => controller.publishCross(inputList)
          case _ => println("Ungueltige Eingabe!")
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