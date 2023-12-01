package de.htwg.se.nochmal
package util

import de.htwg.se.nochmal.controller.Controller

trait HandlerInterface:
    def handleInput(input: String, controller:Controller): Unit

abstract class ChainHandler extends HandlerInterface:
    protected var nextHandler: ChainHandler


class QuitHandler() extends ChainHandler {
    var nextHandler = DiceHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "q") {
            controller.publishQuit()
        } else {
            nextHandler.handleInput(input: String, controller:Controller)
        }
    }
}

class DiceHandler() extends ChainHandler {
    var nextHandler = RestHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "w") {
            controller.publishDice()
        } else {
            nextHandler.handleInput(input: String, controller:Controller)
        }
    }
}

class RestHandler() extends ChainHandler {
    var nextHandler = null;
    override def handleInput(input: String, controller:Controller): Unit = {
        //val chars = input.toString().toList
        val chars = input.toLowerCase().toCharArray()
        val inputString = analyseChars(chars)
        chars(0) match
          case 'x' => controller.publishCross(inputString)
          case ' ' => 
            chars(1) match
              case 'x' => controller.publishCross(inputString)
          case _ => println("Ungueltige Eingabe!")
    }

    def analyseChars(array:Array[Char]):String = 
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
        return resString
}

object InputHandler {
    def handle(i: String, c: Controller) = 
        QuitHandler().handleInput(i, c)
}