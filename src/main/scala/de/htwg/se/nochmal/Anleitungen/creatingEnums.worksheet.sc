// Enums

/* In einer einfachen enum kann man einen Typ definieren, 
 * indem man eine Auswahl an Fällen für diesen festlegt. 
 * Die Fälle werden dabei beginnend mit 0 durchnummeriert. 
 * Definition: enum Name: Fälle 
 */
enum Filling:
    case empty
    case filled

/* Etwas nützlicher sind parametrisierte Enums,
 * in denen die Fälle einen Wert zugewiesen bekommen.
 * Definition: enum Name(val name: Typ): Fälle extends Name(Wert) 
 */
enum Color(val rgb:Int):
    case Red    extends Color(0xFF1866)
    case Orange extends Color(0xF18D2B)
    case Yellow extends Color(0xFFE549)
    case Green  extends Color(0x91e068)
    case Blue   extends Color(0x1589DA)

/* Erzeugt man eine Variable, die als Wert einen Fall aus einer enum hält,
 * so kann man die Nummer dieses Wertes mit der .ordinal-Methode abfragen:
 */
val red = Color.Red
red.ordinal

val green = Color.Green
green.ordinal

/* Außerdem gibt es drei Methoden, die sich direkt auf eine enum anwenden lassen.
 * valueOf: ruft den Wert einer enum in Bezug auf einen Fall ab
 * values: liefert alle Werte einer enum als Array zurück
 * fromOrdinal: liefert Wert einer enum für eine Stelle in dieser zurück
 */
Color.valueOf("Blue")
Color.values
Color.fromOrdinal(4)

/* Als wäre das noch nicht genug, kann man sogar eigene defs festlegen
 * und mit diesen auf der enum agieren:
 */
enum Planet(mass: Double, radius: Double):
    private final val G = 6.67300E-11
    def surfaceGravity = G * mass / (radius * radius)

    case Mercury extends Planet(3.303e+23, 2.4397e6)
    case Venus   extends Planet(4.869e+24, 6.0518e6)
    case Earth   extends Planet(5.976e+24, 6.37814e6)
end Planet