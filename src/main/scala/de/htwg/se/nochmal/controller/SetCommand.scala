package de.htwg.se.nochmal
package controller

import scala.collection.mutable.ListBuffer

import util.Command
import model.PitchAsMatrix
import model.Cross

class SetCommand(line:Int, col:Int) extends Command[PitchAsMatrix] {

  override def noMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch

  override def doMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch.fillCell(line-1, col-1) 

  override def undoMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch.unfillCell(line-1, col-1)

  override def redoMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch.fillCell(line-1, col-1)
}

/* class SetCommand(list:List[Cross|String]) extends Command[PitchAsMatrix] {

  override def noMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch

  override def doMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    val crossList = list.asInstanceOf[List[Cross]]
    pitch.fillCells(crossList)

  override def undoMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch
    /* var tmpPitch = pitch
     for (i <- 0 to crossList.length-1) {
      val line = crossList(i).x
      val col = crossList(i).y
        tmpPitch.fillCell(line-1, col-1) 
    } 
    return tmpPitch */

  override def redoMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch
    /* var tmpPitch = pitch
     for (i <- 0 to crossList.length-1) {
      val line = crossList(i).x
      val col = crossList(i).y
        tmpPitch.fillCell(line-1, col-1) 
    } 
    return tmpPitch */
} */