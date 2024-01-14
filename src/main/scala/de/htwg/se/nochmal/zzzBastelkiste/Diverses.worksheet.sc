import scala.collection.mutable.ListBuffer
/* import scala.collection.mutable.ListBuffer
import de.htwg.se.nochmal.controller.Controller
import de.htwg.se.nochmal.util.ChainHandler
// Umwandlung der analyseInput() Funktion aus TUI bzw. InputChain in FP:

class RestHandler() extends ChainHandler {
    var nextHandler = null;
    override def handleInput(input: String, controller:Controller): Unit = {
        //val chars = input.toString().toList
        val chars = input.toString().toCharArray()
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

val input = "x11 x 2 2 x (3, 3) x 4, 4"
val chars = input.toString().toList

val res = chars.filter(_.isLetterOrDigit).mkString


// ------------ 
import de.htwg.se.nochmal.model.Cross


val mychars = input.toString().toList
val inputString = mychars.filter(_.isLetterOrDigit).mkString
val splitArray = inputString.split("""x""")
 for (i <- 0 to splitArray.length-1) {
        if (splitArray(i).toString.length() > 1) {
            val line = splitArray(i)(0).toString().toInt
            val col = splitArray(i)(1).toString().toInt
            
        }
      }

var l = new ListBuffer[Cross]
for (i <- 0 to splitArray.length-1) {
  if (splitArray(i).length() > 1) {
    val line = splitArray(i)(0).toString().toInt
    val col = splitArray(i)(1).toString().toInt
    l.addOne(new Cross(line, col))
  }
}


import de.htwg.se.nochmal.model.Move

val r = Range(0, splitArray.length)

def setMove(s:String):Move = 
  var l:List[Cross] = List(null, null, null)
  val splitArray = s.split("""x""")
  for (i <- 0 to splitArray.length-1) {
    if (splitArray(i).toString.length() > 1) {
      val line = splitArray(i)(0).toString().toInt
      val col = splitArray(i)(1).toString().toInt
      l.updated(i, new Cross(line, col))
    }
  }
  Move(l(0), l(1), l(2), l(3), l(4))

//setMove("x12")


val ergebnis = r.map(i =>
  if(splitArray(i).length() > 1) {
    val line = splitArray(i)(0).toString().toInt
    val col = splitArray(i)(1).toString().toInt
    new Cross(line, col)
  } else {
    "skip"
  }
  ).toList */

import de.htwg.se.nochmal.model.Cross
// import de.htwg.se.nochmal.model.Move

var s = " "

var l = new ListBuffer[Option[Cross]]

val input = "x11 x 2 2 x (3, 3) x 4, 4"
val mychars = input.toString().toList
val inputString = mychars.filter(_.isLetterOrDigit).mkString
val splitArray = inputString.split("""x""")

val range = Range(0, splitArray.length)
var res = range.map(x =>
  if(splitArray(x).length() > 1) {
    val line = splitArray(x)(0).toString().toInt
    val col = splitArray(x)(1).toString().toInt
    Some(new Cross(line, col))
  } else {
    None
  }).toList

// GUI tests:
val eol = sys.props("line.separator")
val n = "1" + eol + "2" + eol + "!"
val c = "gruen" + eol + "rot" + eol + "Joker!"
val d = "Deine Wuerfelergebnisse: " + eol + n + eol + c
val dicedArray = d.split("""\R""")

for (i <- 0 to dicedArray.length-1) {
  println(dicedArray(i))
}

// 
val cols = 9
val num = 1
for (i <- 0 to cols-1) {
        val colChar = ('A' + i).toChar
        val cross = "x" + colChar + (num).toString()
        println(colChar.toString() + " : " + cross)
        //val cross = "x" + num.toString() + (i+1).toString()
        //contents += createButton(colors(i), cross)
      }


val matrix = Vector(Vector(" ", "X", " "), Vector("X", " ", " "), Vector("X", "X", "X"))
matrix.toString()

var string = ""
matrix(1).map(x => string += x.toString)
println(string)
def matrixToString(m:Vector[Vector[String]]):IndexedSeq[String] = {
  val range = Range(0, m.length)
  val res = range.map(x => vectorToString(m(x)))
  return res
}

def vectorToString(v:Vector[String]):String = {
  var res = ""
  v.map(y => res += y.toString())
  res
}

val test = matrixToString(matrix)

def stringsToMatrix(seq:IndexedSeq[String]):Vector[Vector[String]] = {
  seq.map(x => stringToVector(x)).toVector
}

def stringToVector(s:String):Vector[String] = {
  val chars = s.toCharArray()
  val range = Range(0, chars.length)

  range.map(x => chars(x) match
    case ' ' => " "
    case 'X' => "X"
  ).toVector
}

val test2 = stringsToMatrix(test)