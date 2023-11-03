case class meinFeld(zeilen:Int, spalten:Int, feldgroesse:Int):
    val eol = sys.props("line.separator")

    def title() = 
        (" " + (" " * ((feldgroesse-1)/2)) + "A" + " ") * spalten + eol

    def columns() = 
        ("+" + "-" * feldgroesse) * spalten + "+" + eol

    def lines() = 
        ("|" + " " * feldgroesse) * spalten + "|" + eol

    def pointsFirst() =
        (" " + (" " * ((feldgroesse-1)/2)) + "5" + " ") * spalten + eol

    def pointsNext() =
        (" " + (" " * ((feldgroesse-1)/2)) + "3" + " ") * spalten + eol

    def pitchToString() = 
        title() + (columns() + lines()) * zeilen + 
        columns() + pointsFirst() + pointsNext()


val spielfeld = meinFeld(3, 5, 2)
spielfeld.title()
spielfeld.columns()
spielfeld.lines()
spielfeld.pointsFirst()
spielfeld.pointsNext()
spielfeld.pitchToString()