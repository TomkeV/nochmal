package de.htwg.se.nochmal
package util

// Factory-Pattern als Ersatz für enum Filling
trait FillingFactory {
    def filling(): String
}

class FilledField() extends FillingFactory {
    override def filling(): String = "x"
}

class EmptyField() extends FillingFactory {
    override def filling(): String = " "
}