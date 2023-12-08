package de.htwg.se.nochmal
package controller

import scala.collection.mutable.ListBuffer

import util.Command
import model.PitchAsMatrix
import model.Cross

/* class SetCommand(line:Int, col:Int) extends Command[PitchAsMatrix] {

  override def noMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch

  override def doMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch.fillCell(line-1, col-1) 

  override def undoMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch.unfillCell(line-1, col-1)

  override def redoMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch.fillCell(line-1, col-1)
} */

class SetCommand(list:List[Option[Cross]]) extends Command[PitchAsMatrix] {

  override def noMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch

  override def doMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch.fillCellsList(list, p = pitch )

  override def undoMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch.unfillCellsList(list, pitch)

  override def redoMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch.fillCellsList(list, pitch)
}