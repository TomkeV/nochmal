/**
  * PitchInterface.scala
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package pitchComponent

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITION
trait PitchInterface {
  val row_num:Int
  val col_num:Int
  var pitchColor:PitchWithColorsInterface

  def fillCellWithCross(cross:Cross, p:PitchInterface): PitchInterface
  def unfillCellWithCross(cross:Cross, pitch:PitchInterface): PitchInterface
  def pitchToString(cellWidth:Int = 3): String
  def getIndex(row:Int, col:Int): Filling
  def getColumn(row:Int):Vector[Filling]

  // File-IO
  def loadFromJson():PitchInterface
  def saveToJson():Unit

  def loadFromXML(): PitchInterface
  def saveToXML(): Unit
}