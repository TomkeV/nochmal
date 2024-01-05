/**
  * SetCommand.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package controller
package controllerComponent
package controllerBaseImpl

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import util.Command
import model.pitchComponent.PitchInterface
//import model.baseModel.PitchAsMatrix
import model.Cross

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class SetCommand(cross:Cross) extends Command[PitchInterface] {

  override def noMove(pitch: PitchInterface): PitchInterface = pitch

  override def doMove(pitch: PitchInterface): PitchInterface = 
    pitch.fillCellWithCross(cross, pitch )

  override def undoMove(pitch: PitchInterface): PitchInterface = 
    pitch.unfillCellWithCross(cross, pitch)

  override def redoMove(pitch: PitchInterface): PitchInterface = 
    pitch.fillCellWithCross(cross, pitch)
}