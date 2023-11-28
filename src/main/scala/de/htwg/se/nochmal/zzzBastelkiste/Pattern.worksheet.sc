
// State oder Strategy für PitchAsMatrix.points()?
trait EvenOddEvent(cellWidth:Int, colNum:Int)

case class EvenEvent(cellWidth:Int, colNum:Int) extends EvenOddEvent(cellWidth:Int, colNum:Int)

case class OddEvent(cellWidth:Int, colNum:Int) extends EvenOddEvent(cellWidth:Int, colNum:Int)


object EvenOdd {
    var state = evenState

    def handle(e: EvenOddEvent) = 
        e match {
            case even: EvenEvent => state = evenState
            case odd: OddEvent => state = oddState
        }
        state

    def evenState(cellWidth:Int, colNum:Int) =
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
        
    def oddState(cellWidth:Int, colNum:Int) = 
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
}

val myNum = 7
val myCells = 3
EvenOdd.handle(OddEvent(3, 7))
EvenOdd.handle(EvenEvent(3, 7))
EvenOdd.handle(OddEvent(3, 7))


// Chain of Responsibility für TUI Nutzereingaben?


// Factory-Pattern als Ersatz für enum Filling
trait FillingFactory {
    def filling(): String
}

class FilledField() extends FillingFactory {
    override def filling(): String = "x"
}

class EmptyField() extends FillingFactory {
    override def filling(): String = " "
}

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