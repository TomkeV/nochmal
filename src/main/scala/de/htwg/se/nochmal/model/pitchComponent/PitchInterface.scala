package de.htwg.se.nochmal
package model
package pitchComponent

trait PitchInterface:
  val row_num:Int
  val col_num:Int

  def fillCellWithCross(cross:Cross, p:PitchInterface): PitchInterface
  def unfillCellWithCross(cross:Cross, pitch:PitchInterface): PitchInterface
  def pitchToString(cellWidth:Int = 3): String
  def getIndex(row:Int, col:Int): Filling