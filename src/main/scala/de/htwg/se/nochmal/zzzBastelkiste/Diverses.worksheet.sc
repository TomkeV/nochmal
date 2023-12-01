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

val input = "x12 x 1 2 x (1, 2) x 1, 2"
val chars = input.toString().toList

val res = chars.filter(_.isLetterOrDigit).mkString