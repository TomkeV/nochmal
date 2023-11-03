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
    enum my_Stone: 
        case X, O, Empty

// Struktur entwickeln mit enum:
    case class my_Matrix(rows: Vector[Vector[my_Stone]]):
        def cell(row: Int, col: Int) = rows(row)(col)
        def fill(filling: my_Stone): my_Matrix = copy(Vector.tabulate(3, 3) { (row, col) => filling })
        def replaceCell(row: Int, col: Int, cell: my_Stone) = copy(rows.updated(row, rows(row).updated(col, cell)))
        // Methode, um Matrix zu füllen und an bestimmter Stelle zu ändern

    val m = Matrix(Vector(Vector(Stone.X, Stone.O, Stone.X), Vector(), Vector()))
    m.cell(0, 1)
    //val m2 = m.fill(my_Stone.X)
    // fill erzeugt Kopien und liefert neue Datenstruktur -> kein intern veränderter Zustand, sondern neues Objekt! => Immutability
    //m2.replaceCell(1, 1, my_Stone.X)
    // replace kopiert ebenfalls zu neuer Struktur und verändert dabei bestimmte Stelle zu anderem Wert

val alphabet = Map(1 -> "A", 2 -> "B", 3 -> "C", 4 -> "D")

println(alphabet.get(1))




val v = Vector(1, 2, 3)
v.map(_.toString).map(" " + _ + " ").mkString("|", "|", "|")


//-------------------- aus Tictactoe: ----------------------------------
enum Stone(stringRepresentation: String):
  override def toString = stringRepresentation
  case X extends Stone("X")
  case O extends Stone("O")
  case Empty extends Stone(" ")

  
case class Matrix[T](rows: Vector[Vector[T]]):
  def this(size: Int, filling: T) = this(Vector.tabulate(size, size) { (row, col) => filling })
  val size: Int = rows.size
  def cell(row: Int, col: Int): T = rows(row)(col)
  def row(row: Int) = rows(row)
  def fill(filling: T): Matrix[T] = copy(Vector.tabulate(size, size) { (row, col) => filling })
  def replaceCell(row: Int, col: Int, cell: T): Matrix[T] = copy(rows.updated(row, rows(row).updated(col, cell)))

case class Field(matrix: Matrix[Stone]):
  def this(size: Int, filling: Stone) = this(new Matrix(size, filling))
  val size = matrix.size
  val eol = sys.props("line.separator")
  def bar(cellWidth: Int = 3, cellNum: Int = 3): String = (("+" + "-" * cellWidth) * cellNum) + "+" + eol
  def cells(row: Int, cellWidth: Int = 3): String =
    matrix.row(row).map(_.toString).map(" " * ((cellWidth - 1) / 2) + _ + " " * ((cellWidth - 1) / 2)).mkString("|", "|", "|") + eol
  def mesh(cellWidth: Int = 3): String =
    (0 until size).map(cells(_, cellWidth)).mkString(bar(cellWidth, size), bar(cellWidth, size), bar(cellWidth, size))
  override def toString = mesh()
  def put(stone: Stone, x: Int, y: Int) = copy(matrix.replaceCell(x, y, stone))
  def get(x: Int, y: Int): Stone = matrix.cell(x, y)

val field = new Field(3, Stone.Empty)
