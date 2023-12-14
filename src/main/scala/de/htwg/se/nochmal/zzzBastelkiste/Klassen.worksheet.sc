

// -------------- Anlegen einer Klasse für das Spielfeld ----------------
case class pitch(rows:Int, columns:Int, width:Int):
  val eol = sys.props("line.separator")

  def title() = 
    (" " + (" " * ((width-1)/2)) + "A" + " ") * columns + eol

  def print_cols() = 
    ("+" + "-" * width) * columns + "+" + eol

  def print_rows() = 
    ("|" + " " * width) * columns + "|" + eol

  def pointsFirst() =
    (" " + (" " * ((width-1)/2)) + "5" + " ") * columns + eol

  def pointsNext() =
    (" " + (" " * ((width-1)/2)) + "3" + " ") * columns + eol

  def pitchToString() = 
    title() + (print_cols() + print_rows()) * rows + print_cols() + pointsFirst() + pointsNext()

// ----------- Testen, ob meine Klasse funktioniert -----------------
val my_pitch = pitch(3, 5, 2)
my_pitch.title()
my_pitch.print_cols()
my_pitch.print_rows()
my_pitch.pointsFirst()
my_pitch.pointsNext()
my_pitch.pitchToString()


// ------------------------------------------------------------------------
// Anlegen eines Würfels:
val rand = new scala.util.Random
rand.nextInt(6)

val n = (1 to 3).toList
val diced_ints = n.map(i => rand.nextInt(6)+1)
val diced_colors = n.map(i => rand.nextInt(6))

def roll_dice(anzahl:Int = 6) =
  if (anzahl < 2) {
    println("Du brauchst mindestens 2 Würfel!")
  }
  if (anzahl >= 2) {
    val randomizer = new scala.util.Random
    val n = (1 to (anzahl/2)).toList
    val diced_ints = n.map(i => rand.nextInt(6)+1)
    val diced_colors = n.map(i => rand.nextInt(6))
    //println(diced_ints)
    //println(diced_colors)
    print_dice(diced_ints, diced_colors)
  }

roll_dice(6)

def print_dice(numList:List[Int], colorList:List[Int]) = 
  for (x <- numList) {
    if (x == 6) {
      println("?")
    }
    if (x < 6) {
      println(x)
    }
  }

  for (y <- colorList) {
    y match {
      case 0 => println("rot")
      case 1 => println("orange")
      case 2 => println("gelb")
      case 3 => println("grün")
      case 4 => println("blau")
      case 5 => println("Joker!")
    }
  }

print_dice(diced_ints, diced_colors)

// Klasse für Zahlenwürfel
case class numbers_dice(anzahl:Int = 3):
  val wuerfelzahl = anzahl
  
  def roll_dice(): String =
    if (anzahl < 1) {
      return "Du brauchst mindestens einen Wuerfel!"
    } else {
      val randomizer = new scala.util.Random
      val n = (1 to anzahl).toList
      val diced_ints = n.map(i => rand.nextInt(6)+1)
      
      val eol = sys.props("line.separator")
      var wuerfelergebnis = ""
      for (x <- diced_ints) {
        if (x == 6) {
          wuerfelergebnis = wuerfelergebnis + "!" + eol
        }
        if (x < 6) {
          wuerfelergebnis = wuerfelergebnis + x.toString() + eol
        }
      }
      return wuerfelergebnis
    }
  

val my_dice = numbers_dice()
my_dice.roll_dice()



// Klassen und Konstruktoren
case class Person(name:String, age:Int):
  println("Hallo Mensch!") // primary constructor

val myperson = Person("Anna", 23)



// _____________________________________________________________
// testing for TUI
case class Quit(message: String)
val res: Quit|Int = Quit("Bye")
res.getClass()



// Matrix mit Farben erzeugen
import de.htwg.se.nochmal.model.Color


object myPitchWithColors {
  private val r = Color.red
  private val y = Color.yellow
  private val or = Color.orange
  private val gr = Color.green
  private val b = Color.blue
        
  private val firstRowColors = List(gr, gr, gr, y, y, y, y, gr, b, b, b, or, y, y, y)
  private val secondRowColors = List(or, gr, y, gr, y, y, or, or, r, b, b, or, or, gr, gr)
  private val thirdRowColors = List(b, gr, r, gr, gr, gr, gr, r, r, r, y, y, or, gr, gr)
  private val fourthRowColors = List(b, r, r, gr, or, or, b, b, gr, gr, y, y, or, r, b)
  private val fifthRowColors = List(r, or, or, or, or, r, b, b, or, or, or, r, r, r, r)
  private val sixthRowColors = List(r, b, b, r, r, r, r, y, y, or, r, b, b, b, or)
  private val seventhRowColors = List(y, y, b, b, b, b, r, y, y, y, gr, gr, gr, or, or)

  val colorsMatrix = List(firstRowColors, secondRowColors, thirdRowColors, 
                      fourthRowColors, fifthRowColors, sixthRowColors, seventhRowColors)
}

myPitchWithColors.colorsMatrix



