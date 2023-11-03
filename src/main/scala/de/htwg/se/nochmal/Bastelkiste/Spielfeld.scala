package de.htwg.se.nochmal.Bastelkiste

case class meinFeld(zeilen:Int = 4, spalten:Int = 7, feldgroesse:Int = 3):
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