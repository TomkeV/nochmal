val input = "X 1 2 x(1, 2) x (1 2)"

val chars = input.toLowerCase().toArray
for(i <- 0 to  chars.length-1) {
    println(chars(i))
}

val range = Range(0, chars.length).toList

val middleArray = range.map(i => 
    if (chars(i).isDigit){
        chars(i)
    } else if (chars(i).isLetter) {
        'x'
    } else {

    }
    )

var resArray = ""
for (i <- 0 to chars.length-1) {
    if (middleArray(i)!=()) {
        resArray += middleArray(i)
    }
}
resArray


// publishCross umschreiben:
import de.htwg.se.nochmal.model.PitchAsMatrix
var testPitch = new PitchAsMatrix(4, 7)

val string = "x12xx34x56"

def publishCross(input:String) = 
    for (i <- 0 to input.length()-1) {
        println(i)
        if (input(i) == 'x') {
            println(input(i).toString()+input(i+1).toString()+input(i+2).toString())
            if (input(i+1).isDigit) {
                println(input(i+1).toString())
                if (input(i+2).isDigit){
                    println(input(i+2).toString())
                    val line = input(i+1).toInt
                    val col = input(i+2).toInt
                    //testPitch = set(line, col)
                    println("erfolgreich!")
                }
            }
        }
    }

/* def publishCross(input:String) =
    var i = 0
    while (i < input.length()) {
        println(i)
        if (input(i) == 'x') {
            println(input(i).toString()+input(i+1).toString()+input(i+2).toString())
            if (input(i+1).isDigit) {
                println(input(i+1).toString())
                if (input(i+2).isDigit){
                    println(input(i+2).toString())
                    val line = input(i+1).toInt
                    val col = input(i+2).toInt
                    //testPitch.fillCell(line-1, col-1)
                    println("x" + line + col)
                }
            }
            i = i + 2
        } else {
            i = i + 1
        }
    } */

def set(line:Int, col:Int): PitchAsMatrix =
   testPitch.fillCell(line-1, col-1) 

publishCross(string)


val splitArray = string.split("""x""")
splitArray.length
for (i <- 0 to splitArray.length-1) {
    println("Eintrag" + i + ": " +  splitArray(i).toString())
}

def cross(input: String) =
    val splitArray = input.split("""x""")
    for (i <- 0 to splitArray.length-1) {
        if (splitArray(i).toString.length() > 1) {
            println("Eintrag" + i + ": " +  splitArray(i).toString())
            val line = splitArray(i)(0)
            val col = splitArray(i)(1)
            println("Zeile: " + line + " Spalte: " + col)
            
        }
    }

cross(string)