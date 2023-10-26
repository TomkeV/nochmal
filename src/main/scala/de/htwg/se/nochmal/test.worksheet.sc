// Worksheet für VL 3

// einfache Berechnungen:
    1 + 2

// Funktionen definieren: 
    def f(x:Int) = x + 1
    f(8)

// größere Strukturen (z.B. Klassen) definieren:
    case class Player(name:String)
    val player = Player("Tomke")
    player.name
    val playerlist = List (player)

// Datenstruktur: Vektor von Vektoren (= Array von Arrays):
    val cells = Vector(Vector("x", "o", "x"), Vector(), Vector())
    cells(0)(1)

// Datenstruktur: enum
    enum Stone: 
        case X, O, Empty

// Struktur entwickeln mit enum:
    case class Matrix(rows: Vector[Vector[Stone]]):
        def cell(row: Int, col: Int) = rows(row)(col)
        def fill(filling: Stone): Matrix = copy(Vector.tabulate(3, 3) { (row, col) => filling })
        def replaceCell(row: Int, col: Int, cell: Stone) = copy(rows.updated(row, rows(row).updated(col, cell)))
        // Methode, um Matrix zu füllen und an bestimmter Stelle zu ändern

    val m = Matrix(Vector(Vector(Stone.X, Stone.O, Stone.X), Vector(), Vector()))
    m.cell(0, 1)
    val m2 = m.fill(Stone.Empty)
    // fill erzeugt Kopien und liefert neue Datenstruktur -> kein intern veränderter Zustand, sondern neues Objekt! => Immutability
    m2.replaceCell(1, 1, Stone.X)
    // replace kopiert ebenfalls zu neuer Struktur und verändert dabei bestimmte Stelle zu anderem Wert

case class PitchMatrix[T](rows: Vector[Vector[T]]):
    def this(lineNum:Int = 4, colNum:Int = 7, filling:T = " ") = this(Vector.tabulate(lineNum, colNum) { (row, col) => filling })

val myPitch = new PitchMatrix()
