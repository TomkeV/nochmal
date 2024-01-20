



val v = Vector("rot", "orange", "gelb", "grün", "blau", "orange")

v.head
v.take(3).drop(1).head

val vv = Vector(Vector("rot"), Vector("gelb"))
vv.head.head
vv(1).length
vv.length

v.appended("hi")

for (s <- v ) {
    if (s.startsWith("g"))
        println("hi" + s)
}

v.size
vv.size
vv.head.size

var myMatrix = Vector(Vector[String]())

// ---------------- Enum erstellen
enum Filling(s:String):
  override def toString(): String = s
  case filled extends Filling("X")
  case empty extends Filling("_")

// ---------------- Vektor mit Inhalten aus Enum
val vec = Vector[Filling](Filling.empty, Filling.empty, Filling.empty)

def myVec(places:Int = 3): Vector[Filling] = 
  val my_v = Vector.tabulate(places)(p => Filling.empty)
  return my_v

myVec()
myVec(5)

val m = Vector(Vector.fill(3)(Filling.empty), Vector.fill(3)(Filling.empty), Vector.fill(3)(Filling.empty))
println(m)

// ---------------- Ziel 1 erreicht: Matrix mit leeren Feldern erzeugt!!
def create_Matrix(Zeilen:Int, Spalten:Int): Vector[Vector[Filling]] = 
  Vector.tabulate(Zeilen) {i =>
    Vector.tabulate(Spalten) {j => Filling.empty}
  }

val matrix = create_Matrix(3, 5)

matrix.foreach(i => println(i.toString()))


case class Matrix1(zeilen:Int, spalten: Int):
  val z = zeilen
  val c = spalten
  def create_Matrix(): Vector[Vector[Filling]] = 
  Vector.tabulate(z) {i =>
    Vector.tabulate(c) {j => Filling.empty}
  }
  
val myM = Matrix1(2, 4)
myM.create_Matrix()


// Matrix als Klasse
case class PitchAsMatrix1(matrix: Vector[Vector[Filling]]):
  val rowsInMatrix = matrix.size  
  val colsInMatrix = matrix.head.size
  def this(rows:Int=4, columns:Int=7) = 
    this(
      Vector.tabulate(rows) {i =>
      Vector.tabulate(columns) {j => Filling.empty}
  }
  )
  
  val eol = sys.props("line.separator")

  def getIndex(row:Int, col:Int):Filling =
    matrix(row)(col)

  def fillCell(row:Int, col:Int):PitchAsMatrix1 = 
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))
  
  def columns(cellWidth:Int = 3, colNum:Int = 7) = 
    ("+" + "-" * cellWidth) * colNum + "+" + eol
  
  def printPitch(cellWidth:Int = 3): String =
    var res = columns(cellWidth, colsInMatrix)
    for (i <- 1 to rowsInMatrix) {
      res += "|"
        for (j <- 1 to colsInMatrix) {
          res += " " * (cellWidth/2) + getIndex(i-1, j-1).toString() + " " * (cellWidth/2) + "|"
        }
      res += eol + columns(cellWidth, colsInMatrix)
    }
    return res

val testM = new PitchAsMatrix1(2, 4)
testM.rowsInMatrix
testM.colsInMatrix
testM.getIndex(1, 1)
var ausgabeMatrix = testM.fillCell(0, 0)

ausgabeMatrix.printPitch()

val eol = sys.props("line.separator")
var res = ""
for (i <- 1 to ausgabeMatrix.rowsInMatrix) {
  res += "|"
  for (j <- 1 to ausgabeMatrix.colsInMatrix) {
    res += " " + ausgabeMatrix.getIndex(i-1, j-1).toString() + " |"
  }
  res += eol
}

println(res)

// printPitch in Funktional umwandeln - schritt 1:
val feldListe = Range(0, ausgabeMatrix.colsInMatrix).toList
var ergebnis = ""
for (i <- 0 to ausgabeMatrix.rowsInMatrix-1) {
  ergebnis += feldListe.map(x => 
                            "| " + ausgabeMatrix.getIndex(i, x).toString() + " ").mkString
  ergebnis += "|" + eol
}
println(ergebnis)

// printPitch in Funktional umwandeln - schritt 2:
val felder = Range(0, ausgabeMatrix.colsInMatrix).toList
val hoehe = Range(0, ausgabeMatrix.rowsInMatrix).toList
var ziel = hoehe.map(x => (felder.map(y => "| " + ausgabeMatrix.getIndex(x, y).toString() + " ").mkString) + "|" + eol).mkString
println(ziel)



// Titel mappen:

def title(cellWidth:Int = 3, colNum:Int = 7) = (" " + (" " * ((cellWidth-1)/2)) + "A" + " ") * colNum + eol

val s = title()

val list = Range(0, 7).toList

val title = list.map(x =>
                  ('A' + x).toChar)

def create_title(cellWidth:Int = 3, colNum:Int = 7): String =
  val eol = sys.props("line.separator")
  var res = " "
  for (i <- 0 to colNum-1) {
    res += " " * ((cellWidth-1)/2)
    res += ('A' + i).toChar
    res += "  "
  }
  return res + eol

val a = create_title(1, 4)

val x = list.map(x =>
                " " + 
                " " * ((3-1)/2) +
                ('A' + x).toChar +
                " "
                )
for (i <- 0 to x.length-1) {
  print(x(i).toString())
}
print(eol)

val y = for(i <- 0 to x.length-1) {
  x(i).toString()
}

println(y)

val test = list.map(x =>
                " " + 
                " " * ((3-1)/2) +
                ('A' + x).toChar +
                " "
                ).mkString

println(test)


// Punkte mit map auf reale Punkte anpassen
val rowNum = 13
val cellWidth = 3
val anzahlPunkte = Range(0, rowNum).toList
val punkte = anzahlPunkte.map(x =>
        if (rowNum%2 == 0) {
          // für gerade Zahlen: 
          if (x == anzahlPunkte(0) || x == anzahlPunkte(rowNum-1)){
            (" " * ((cellWidth-1)/2)) + " 5 "
          } else if ((x == (rowNum/2)) || (x == (rowNum/2)-1)) {
            (" " * ((cellWidth-1)/2)) + " 1 "
          } else if (x == ((rowNum/2)-2) || x == ((rowNum/2)+1)) {
            (" " * ((cellWidth-1)/2)) + " 2 "
          } else {
            (" " * ((cellWidth-1)/2)) + " 3 "
          }
        } else {
          if (x == anzahlPunkte(0) || x == anzahlPunkte(rowNum-1)){
            (" " * ((cellWidth-1)/2)) + " 5 "
          } else if (x == (rowNum/2)) {
            (" " * ((cellWidth-1)/2)) + " 1 "
          } else if (x == ((rowNum/2)+1) || x == ((rowNum/2)-1)) {
            (" " * ((cellWidth-1)/2)) + " 2 "
          } else {
            (" " * ((cellWidth-1)/2)) + " 3 "
          }
        }        
      ).mkString


val rot = "\u001B[41m"
println(rot+"Hi")


// Matrix für Farben erstellen:

import de.htwg.se.nochmal.model.Color

val colorMatrix = Vector[Vector[Color]](Vector(Color.green, Color.orange, Color.blue, Color.blue, Color.red, Color.red, Color.yellow),
                                        Vector(Color.green, Color.green, Color.green, Color.red, Color.orange, Color.blue, Color.yellow),
                                        Vector(Color.green, Color.yellow, Color.red, Color.red, Color.orange, Color.blue, Color.blue))


/* // Matrixumbau:

import de.htwg.se.nochmal.model.PitchAsMatrix

val testing_matrix = new PitchAsMatrix(4, 7) */