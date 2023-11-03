val alphabet = Map(1 -> "A", 2 -> "B", 3 -> "C", 4 -> "D")

println(alphabet.get(1))

def create_title(cellWidth:Int = 3, colNum:Int = 7) = 
  var my_title = " "
  for {
    i <- 1 to colNum
    if i <= 26
  } my_title ++ alphabet.get(i)
  println(my_title)

create_title()




val v = Vector(1, 2, 3)
v.map(_.toString).map(" " + _ + " ").mkString("|", "|", "|")

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
