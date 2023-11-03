
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
