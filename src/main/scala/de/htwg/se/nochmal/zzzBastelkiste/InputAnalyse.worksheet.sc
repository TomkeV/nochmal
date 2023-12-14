import scala.util.Success
import scala.util.Failure
import scala.util.Try
val input = "x(B1)"

val chars = input.toString().toList
val inputString = chars.filter(_.isLetterOrDigit)

val x = inputString(1) match
    case 'A' | 'a' => Try(1)
    case 'B' | 'b' => Try(2)
    case 'C' | 'c' => Try(3)
    case 'D' | 'd' => Try(4)
    case 'E' | 'e' => Try(5)
    case 'F' | 'f' => Try(6)
    case 'G' | 'g' => Try(7)
    case 'H' | 'h' => Try(8)
    case 'I' | 'i' => Try(9)
    case 'J' | 'j' => Try(10)
    case 'K' | 'k' => Try(11)
    case 'L' | 'l' => Try(12)
    case 'M' | 'm' => Try(13)
    case 'N' | 'n' => Try(14)
    case 'O' | 'o' => Try(15)
    case _ => Failure(new Error("Keine gueltige Zeile!"))



//val line = inputString(1).toString()
val line = x.get
val col = inputString(2).toString().toInt

println(line.toString + ", " + col.toString)