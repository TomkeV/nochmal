package de.htwg.se.nochmal
package util

import model.PitchAsMatrix

trait Builder:
    def createTitle(t:Title): Builder
    def createMatrix(m:Matrix): Builder
    def createPoints(p:Points): Builder

class Title(cellWidth:Int, colNum:Int) {
    val eol = sys.props("line.separator")
    val list = Range(0, colNum).toList
    val res = list.map(x =>
                " " + 
                " " * ((cellWidth-1)/2) +
                ('A' + x).toChar +
                " ").mkString + eol
}

class Matrix(cellWidth:Int, colNum:Int, rowNum:Int, pitch:PitchAsMatrix) {
    val eol = sys.props("line.separator")
    val numOfRows = Range(0, rowNum)
    val numOfCells = Range(0, colNum)
    val res = columns(cellWidth, colNum) + numOfRows.map(x =>
      (numOfCells.map(y =>
        "|" + " " * (cellWidth/2) + pitch.getIndex(x, y).toString() 
        + " " * (cellWidth/2)).mkString) + "|" + eol + columns(cellWidth, colNum)).mkString 

    def columns(cellWidth:Int = 3, colNum:Int = 7) = 
        ("+" + "-" * cellWidth) * colNum + "+" + eol
}

class Points(cellWidth:Int, colNum:Int) {
    val eol = sys.props("line.separator")
    val res = if (colNum%2 == 0) { // für gerade Zahlen: 
                EvenOdd.handle(EvenEvent())(cellWidth, colNum) + eol
              } else { // für ungerade Zahlen: 
                EvenOdd.handle(OddEvent())(cellWidth, colNum) + eol
              }
}

class PitchBuilder() extends Builder {
    private var title : String = _
    private var matrix : String = _
    private var points : String = _

    override def createTitle(t:Title) : PitchBuilder= 
        title = t.res
        this

    override def createMatrix(m: Matrix) : PitchBuilder = 
        matrix = m.res
        this

    override def createPoints(p: Points) : PitchBuilder = 
        points = p.res
        this

    override def toString(): String = 
        title + matrix + points
}

object Pitch {
    def builder(cellWidth: Int = 3, colNum: Int = 7): PitchBuilder = {
        new PitchBuilder()
    }
}