

// State für PitchAsMatrix.points()
trait EvenOddEvent
    //override def toString(): String = "Hallo"

case class EvenEvent() extends EvenOddEvent

case class OddEvent() extends EvenOddEvent

object EvenOdd { // Object: entweder Singleton (nur ein Objekt der Klasse) oder Companion-Object (hält für Klasse relevante Inhalte)
    var state = evenState

    def handle(e: EvenOddEvent) = 
        e match {
            case even: EvenEvent => state = evenState
            case odd: OddEvent => state = oddState
        }
        state

    def evenState(cellWidth:Int=3, colNum:Int): String =
        val numOfPoints = Range(0, colNum).toList
        val points = numOfPoints.map(x => 
            if (x == numOfPoints(0) || x == numOfPoints(colNum-1)){
                (" " * ((cellWidth-1)/2)) + " 5 "
            } else if ((x == (colNum/2)) || (x == (colNum/2)-1)) {
                (" " * ((cellWidth-1)/2)) + " 1 "
            } else if (x == ((colNum/2)-2) || x == ((colNum/2)+1)) {
                (" " * ((cellWidth-1)/2)) + " 2 "
            } else {
                (" " * ((cellWidth-1)/2)) + " 3 "
            }).mkString
        return points
        
    def oddState(cellWidth:Int, colNum:Int): String = 
        val numOfPoints = Range(0, colNum).toList
        val points = numOfPoints.map(x => 
            if (x == numOfPoints(0) || x == numOfPoints(colNum-1)){
                (" " * ((cellWidth-1)/2)) + " 5 "
            } else if (x == (colNum/2)) {
                (" " * ((cellWidth-1)/2)) + " 1 "
            } else if (x == ((colNum/2)+1) || x == ((colNum/2)-1)) {
                (" " * ((cellWidth-1)/2)) + " 2 "
            } else {
                (" " * ((cellWidth-1)/2)) + " 3 "
            }        
            ).mkString
        return points

}

EvenOdd.handle(OddEvent())(3, 7).toString()
EvenOdd.handle(EvenEvent())(3, 8).toString()
EvenOdd.handle(OddEvent())(3, 11).toString()

EvenOdd.evenState(3, 8)


def points(cellWidth:Int = 3, colNum:Int = 7): String =
    if (colNum%2 == 0) {
    // für gerade Zahlen: 
        EvenOdd.handle(EvenEvent())(cellWidth, colNum)
    } else {
    // für ungerade Zahlen
        EvenOdd.handle(OddEvent())(cellWidth, colNum)
    } 
    //return points

points().toString()



// ----------------------------------------------------------------------------------
// Chain of Responsibility 
import de.htwg.se.nochmal.controller.Controller
import de.htwg.se.nochmal.model.Colors_dice
import de.htwg.se.nochmal.model.Numbers_dice
import de.htwg.se.nochmal.model.PitchAsMatrix

trait HandlerInterface:
    def handleInput(input: String, controller:Controller): Unit

abstract class ChainHandler extends HandlerInterface:
    protected var nextHandler: ChainHandler


class QuitHandler() extends ChainHandler {
    var nextHandler = DiceHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "q") {
            println("Controller publish Quit")
            controller.publishQuit()
        } else {
            nextHandler.handleInput(input: String, controller:Controller)
        }
    }
}

class DiceHandler() extends ChainHandler {
    var nextHandler = RestHandler()
    override def handleInput(input: String, controller:Controller): Unit = {
        if (input == "d") {
            println("Controller publish Dice")
            controller.publishDice()
        } else {
            nextHandler.handleInput(input: String, controller:Controller)
        }
    }
}

class RestHandler() extends ChainHandler {
    var nextHandler = null;
    override def handleInput(input: String, controller:Controller): Unit = {
        val chars = input.toString().toList
        println("Analysing input as String")
    }
}

object InputHandler {
    def handle(i: String, c: Controller) = 
        QuitHandler().handleInput(i, c)
}


val myPitch = new PitchAsMatrix(4, 7)
val myNumdice = Numbers_dice(3)
val myColorsdice = Colors_dice(3)
val controller = Controller(myPitch, myNumdice, myColorsdice)
val my_input = "q"
InputHandler.handle(my_input, controller)


// ----------------------------------------------------------------------------------
// Factory-Pattern statt enum Color

trait ColorFactory {
    def color(): String
    def colorHash(): String
}

class RedField() extends ColorFactory {
    override def color(): String = "r"
    override def colorHash(): String = "FFFFFF"
}

class OrangeField() extends ColorFactory {
    override def color(): String = "o"
    override def colorHash(): String = "FFFFFF"
}

class YellowField() extends ColorFactory {
    override def color(): String = "y"
    override def colorHash(): String = "FFFFF"
}

class GreenField() extends ColorFactory {
    override def color(): String = "g" 
    override def colorHash(): String = "FFFFFF"
}

class BlueField() extends ColorFactory {
    override def color(): String = "b" 
    override def colorHash(): String = "FFFFF"
}

object ColorFactory {
        
    //def apply =
        // Ersatz für Konstruktor, erzeugt neue Felder je nach Parameter
}