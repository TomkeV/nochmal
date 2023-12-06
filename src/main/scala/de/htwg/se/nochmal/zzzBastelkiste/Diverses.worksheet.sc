import scala.collection.mutable.ListBuffer
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

setMove("x12")


val ergebnis = r.map(i =>
  if(splitArray(i).length() > 1) {
    val line = splitArray(i)(0).toString().toInt
    val col = splitArray(i)(1).toString().toInt
    new Cross(line, col)
  } else {
    "skip"
  }
  ).toList
