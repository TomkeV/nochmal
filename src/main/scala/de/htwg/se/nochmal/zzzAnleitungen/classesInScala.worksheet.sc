// case classes

/* Case Classes sind Klassen, die sich besonders zum Arbeiten 
 * mit immutablen Daten eignen. 
 * Definition: case class Name(param:Datentyp = default, param2: Datentyp)
 * Verwendung: val name = Name(Werte für Parameter) // erzeugt Objekt
 * Zugriff auf Werte: name.param
 */
case class Person(name:String, age:Int)
val p1 = Person("Mirko", 35)
println(p1.name + " ist " + p1.age + " Jahre alt.")

/* Beim Vergleichen zweier Instanzen (Objekte) werden 
 * deren Inhalte auf Gleichheit geprüft.
 * Vergleich: objekt1 == objekt2
 */
val p2 = Person("Mirko", 35)
val p3 = Person("Anna", 27)
val p1equalsp2 = p1 == p2
val p1equalsp3 = p1 == p3

/* Case Classes unterstützen das kopieren ihrer Instanzen.
 * Mit der Methode .copy() wird eine Kopie erzeugt, bei der man wenn gewünscht
 * auch andere Argumente übergeben kann. 
 */
val p2_new = p2.copy(age = p2.age+1)
