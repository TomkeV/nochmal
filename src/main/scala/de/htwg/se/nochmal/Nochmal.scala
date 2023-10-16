
//Zeilen: lines / Spalten: columns / Spielfeld: pitch
@main def nochmal: Unit =
  println("Willkommen bei Nochmal!")
  println(pitch())

val eol = sys.props("line.separator")
//val bezeichnung = "  A   B   C   D   E   F   G" + eol

def title(cellWidth:Int = 3, colNum:Int = 7) = 
  (" " + (" " * ((cellWidth-1)/2)) + "A" + " ") * colNum + eol

def columns(cellWidth:Int = 3, colNum:Int = 7) = 
  ("+" + "-" * cellWidth) * colNum + "+" + eol

def lines(cellWidth:Int = 3, colNum:Int = 7) = 
  ("|" + " " * cellWidth) * colNum + "|" + eol

def pointsFirst(cellWidth:Int = 3, colNum:Int = 7) =
  (" " + (" " * ((cellWidth-1)/2)) + "5" + " ") * colNum + eol

def pointsNext(cellWidth:Int = 3, colNum:Int = 7) =
  (" " + (" " * ((cellWidth-1)/2)) + "3" + " ") * colNum + eol

def pitch(cellWidth:Int = 3, colNum:Int = 7, lineNum:Int = 4) = 
  title(cellWidth, colNum) + (columns(cellWidth, colNum) + lines(cellWidth, colNum)) * lineNum + 
  columns(cellWidth, colNum) + pointsFirst(cellWidth, colNum) + pointsNext(cellWidth, colNum)
