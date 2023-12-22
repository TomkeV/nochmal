package de.htwg.se.nochmal
package controller
package controllerBaseImpl

import util.Command
import model.PitchAsMatrix
import model.Cross


class SetCommand(cross:Cross) extends Command[PitchAsMatrix] {

  override def noMove(pitch: PitchAsMatrix): PitchAsMatrix = pitch

  override def doMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch.fillCellWithCross(cross, p = pitch )

  override def undoMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch.unfillCellWithCross(cross, pitch)

  override def redoMove(pitch: PitchAsMatrix): PitchAsMatrix = 
    pitch.fillCellWithCross(cross, pitch)
}