

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



/* // ----------------------------------------------------------------------------------
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
// Builder für Spielfeld mit Titel, ohne Titel,...

trait Builder:
    def createTitle(t:Title): Builder
    def createMatrix(m:Matrix): Builder
    def createPoints(p:Points): Builder

class Title(cellWidth:Int, colNum:Int) {
    val eol = sys.props("line.separator")
    val list = Range(0, colNum).toList
    val res = list.map(x =>
                " " + 
                " " * ((cellWidth-1)/2) +
                ('A' + x).toChar +
                " ").mkString + eol
}
val testTitle = Title(3, 7).res

class Matrix(cellWidth:Int, colNum:Int, rowNum:Int, pitch:PitchAsMatrix) {
    val eol = sys.props("line.separator")
    val numOfRows = Range(0, rowNum)
    val numOfCells = Range(0, colNum)
    val res = columns(cellWidth, colNum) + numOfRows.map(x =>
      (numOfCells.map(y =>
        "|" + " " * (cellWidth/2) + pitch.getIndex(x, y).toString() 
        + " " * (cellWidth/2)).mkString) + "|" + eol + columns(cellWidth, colNum)).mkString 

    def columns(cellWidth:Int = 3, colNum:Int = 7) = 
        ("+" + "-" * cellWidth) * colNum + "+" + eol
}
val pitchForTest = new PitchAsMatrix(4, 7)
val testMatrix = Matrix(3, 7, 4, pitchForTest).res

class Points(cellWidth:Int, colNum:Int) {
    val eol = sys.props("line.separator")
    val res = if (colNum%2 == 0) { // für gerade Zahlen: 
                EvenOdd.handle(EvenEvent())(cellWidth, colNum) + eol
              } else { // für ungerade Zahlen: 
                EvenOdd.handle(OddEvent())(cellWidth, colNum) + eol
              }
} */
/* val testPoints = Points(3, 7).res

class PitchBuilder() extends Builder {
    private var title : String = _
    private var matrix : String = _
    private var points : String = _

    override def createTitle(t:Title) : PitchBuilder= 
        title = t.res
        this

    override def createMatrix(m: Matrix) : PitchBuilder = 
        matrix = m.res
        this

    override def createPoints(p: Points) : PitchBuilder = 
        points = p.res
        this

    override def toString(): String = 
        title + matrix + points
}

object Pitch {
    def builder(cellWidth: Int = 3, colNum: Int = 7): PitchBuilder = {
        new PitchBuilder()
    }
}

val test = Pitch.builder(3, 7).createTitle(Title(3, 7)).createMatrix(Matrix(3, 7, 4, pitchForTest)).createPoints(Points(3, 7))


val eol = sys.props("line.separator")
// von chat-GPT
class PitchBuilder2(val cellWidth: Int, val colNum: Int) {
    private var title: String = _
    private var columns: String = _
    private var points: String = _

    def withTitle(): PitchBuilder2 = {
        title = buildTitle()
        this
    }

    def withColumns(): PitchBuilder2 = {
        columns = buildColumns()
        this
    }

    def withPoints(): PitchBuilder2 = {
        points = buildPoints()
        this
    }

    def build(): String = {
        title + columns + points
    }

    private def buildTitle(): String = {
        val list = Range(0, colNum).toList
        list.map(x =>
                " " +
                " " * ((cellWidth - 1) / 2) +
                ('A' + x).toChar +
                " ").mkString + eol
        }

    private def buildColumns(): String = {
        ("+" + "-" * cellWidth) * colNum + "+" + eol
    }

    private def buildPoints(): String = {
        if (colNum % 2 == 0) {
            EvenOdd.handle(EvenEvent())(cellWidth, colNum) + eol
        } else {
            EvenOdd.handle(OddEvent())(cellWidth, colNum) + eol
        }
    }
} */

/* object Pitch {
def builder(cellWidth: Int = 3, colNum: Int = 7): PitchBuilder2 = {
new PitchBuilder2(cellWidth, colNum)
}
} */

/* // Verwendung des Builders
val pitch = Pitch.builder()
.withTitle()
.withColumns()
.withPoints()
.build()

println(pitch) */