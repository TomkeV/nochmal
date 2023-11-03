
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