package de.htwg.se.nochmal.util

trait EvenOddEvent

case class EvenEvent() extends EvenOddEvent

case class OddEvent() extends EvenOddEvent


object EvenOdd { // Object: entweder Singleton (nur ein Objekt der Klasse) oder Companion-Object (hält für Klasse relevante Inhalte)
    var state = evenState

    def handle(e: EvenOddEvent) = 
        e match {
            case even: EvenEvent => state = evenState
            case odd: OddEvent => state = oddState
        }
        state

    def evenState(cellWidth:Int=3, colNum:Int): String =
        val numOfPoints = Range(0, colNum).toList
        val points = numOfPoints.map(x => 
            if (x == numOfPoints(0) || x == numOfPoints(colNum-1)){
                (" " * ((cellWidth-1)/2)) + " 5 "
            } else if ((x == (colNum/2)) || (x == (colNum/2)-1)) {
                (" " * ((cellWidth-1)/2)) + " 1 "
            } else if (x == ((colNum/2)-2) || x == ((colNum/2)+1)) {
                (" " * ((cellWidth-1)/2)) + " 2 "
            } else {
                (" " * ((cellWidth-1)/2)) + " 3 "
            }).mkString
        return points
        
    def oddState(cellWidth:Int, colNum:Int): String = 
        val numOfPoints = Range(0, colNum).toList
        val points = numOfPoints.map(x => 
            if (x == numOfPoints(0) || x == numOfPoints(colNum-1)){
                (" " * ((cellWidth-1)/2)) + " 5 "
            } else if (x == (colNum/2)) {
                (" " * ((cellWidth-1)/2)) + " 1 "
            } else if (x == ((colNum/2)+1) || x == ((colNum/2)-1)) {
                (" " * ((cellWidth-1)/2)) + " 2 "
            } else {
                (" " * ((cellWidth-1)/2)) + " 3 "
            }        
            ).mkString
        return points
}