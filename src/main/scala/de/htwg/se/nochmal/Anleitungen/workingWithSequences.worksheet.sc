import scala.compiletime.ops.any
// Scala Sequences - ein Bestandteil der Scala Collections

/* Sequences zeichnen sich dadurch aus, dass ihre Elemente eine Reihenfolge haben.
 * Diese Reihenfolge entsteht entweder durch eine Indiziierung oder
 * durch eine lineare Verkettung.
 * immutable Sequence = nicht veränderbare Elemente in fester Reihenfolge
 */


// List

/* Eine Liste ist eine immutable Sequence. 
 * Ihre Bestandtele werden linear verkettet, es gibt also keinen Index. 
 * Definition: val name = List(Wert1, Wert2, ...)
 * Definition: val name = Wert1 :: Wert2 :: ... :: Nil
 */
val ints = List(1, 2, 3)
val names = "Pia" :: "Tomke" :: "keksi" :: Nil

/* Obwohl nicht zwingend erforderlich, kann man den Datentyp einer Liste
 * bei ihrer Deklaration angeben.
 * Sinnvoll und wichtig ist das dann, wenn man Daten verschiedener Typen in einer Liste
 * zusammenfassen will.
 */
val places: List[String] = List("Konstanz", "Wittlich")

val things: List[String | Int] = List(1, "two", 3)
val anythings: List[Any] = List("one", 2, 3.0)

/* Um mit den immutablen Datentypen arbeiten zu können, gibt es diverse Operationen,
 * die dazu dienen, eine neue Liste basierend auf einer bestehenden zu erstellen.
 * :: dient dazu, ein Element vor einer Liste hinzuzufügen
 * ::: dient dazu, eine Liste vor einer Liste hinzuzufügen
 */
val newInts = 0 :: ints
val newAnyThings = things ::: anythings

/* Um Operationen auf die einzelnen Elemente einer Liste anzuwenden, 
 * kann man über die Liste iterieren.
 */
for name <- names do println(name)



// Vector

/* Ein Vektor ist eine immmutable Sequence.
 * Die Elemente eines Vektors werden mit einem Index versehen.
 * Definition: val name = Vector(Wert1, Wert2, ...)
 * Selbstverständlich kann ein Vektor auch Objekte einer Klasse fassen.
 */
val nums = Vector(2, 4, 6, 8, 10)

case class Person(name:String)
val people = Vector(Person("Ernie"), Person("Bert"), Person("Groover"))

/* Da Vektoren immutabel sind, braucht es spezielle Methoden, um mit ihnen zu arbeiten.
 * :+ fügt Element hinten hinzu
 * ++ fügt Vektor hinten hinzu
 * +: fügt Element vorne hinzu
 * ++: fügt Vektor vorne hinzu
 */
val newNums = nums :+ 1
val anotherNewNums = newNums ++ Vector(3, 5)
val turnAround = 0 +: nums
val anotherTurn = Vector(-3, -1) :++ turnAround

/* Auch bei Vektoren kann man mit einer for-Schleife
 * eine Operation auf jedes Element anwenden:
 */
for num <- nums do println(num)