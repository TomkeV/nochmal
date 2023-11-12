import scala.collection.View.Fill
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


case class Matrix(zeilen:Int, spalten: Int):
  val z = zeilen
  val c = spalten
  def create_Matrix(): Vector[Vector[Filling]] = 
  Vector.tabulate(z) {i =>
    Vector.tabulate(c) {j => Filling.empty}
  }
  
val myM = Matrix(2, 4)
myM.create_Matrix()


// Matrix als Klasse
case class PitchAsMatrix(matrix: Vector[Vector[Filling]]):
  val rowsInMatrix = matrix.size  
  val colsInMatrix = matrix.head.size
  def this(rows:Int=4, columns:Int=7) = 
    this(
      Vector.tabulate(rows) {i =>
      Vector.tabulate(columns) {j => Filling.empty}
  }
  )
  
  def getIndex(row:Int, col:Int):Filling =
    matrix(row)(col)

  def fillCell(row:Int, col:Int):PitchAsMatrix = 
    copy(matrix.updated(row, matrix(row).updated(col, Filling.filled)))

val testM = new PitchAsMatrix(2, 4)
testM.rowsInMatrix
testM.colsInMatrix
testM.getIndex(1, 1)
testM.fillCell(0, 0)
testM.fillCell(0, 1)
testM.fillCell(0, 2)
