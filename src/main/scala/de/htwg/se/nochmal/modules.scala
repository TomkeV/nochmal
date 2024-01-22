/**
  * modules.scala
  * Part of Dependency Injection
  * @author Tomke Velten
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.Controller

import model.diceComponent.DiceInterface
import model.diceComponent.diceImplementierung.Numbers_dice
import model.diceComponent.diceImplementierung.Colors_dice

import model.pitchComponent.baseModel.PitchAsMatrix
import model.pitchComponent.PitchInterface
import model.pitchComponent.baseModel.PitchWithColors
import model.pitchComponent.baseModel.orangeColorsList
import model.pitchComponent.baseModel.blueColorsList
import model.pitchComponent.baseModel.yellowColorsList

import model.Color

import util.ioComponent.FileIOInterface
import util.ioComponent.io.JsonIO
import util.ioComponent.io.XMLIO

// -----------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------- OBJECT DEFINITION
object Default {

  given PitchInterface: PitchInterface = new PitchAsMatrix(7, 15)

  given NumbersDiceInterface: DiceInterface = Numbers_dice(3)
  given ColorDiceInterface: DiceInterface = Colors_dice(3)

  given ControllerInterface: ControllerInterface 
    = Controller(PitchInterface, NumbersDiceInterface, ColorDiceInterface)
  
  given FileIOInterface: FileIOInterface = new JsonIO()
}

// -----------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------- OBJECT DEFINITION
object OrangePitch {
  given PitchInterface: PitchInterface = new PitchAsMatrix(7, 15, color = PitchWithColors(orangeColorsList, Color.orange))

  given NumbersDiceInterface: DiceInterface = Numbers_dice(3)
  given ColorDiceInterface: DiceInterface = Colors_dice(3)

  given ControllerInterface: ControllerInterface 
    = Controller(PitchInterface, NumbersDiceInterface, ColorDiceInterface)
  
  given FileIOInterface: FileIOInterface = new JsonIO()
}

// -----------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------- OBJECT DEFINITION
object BluePitch {
  given PitchInterface: PitchInterface = new PitchAsMatrix(7, 15, color = PitchWithColors(blueColorsList, Color.blue))

  given NumbersDiceInterface: DiceInterface = Numbers_dice(3)
  given ColorDiceInterface: DiceInterface = Colors_dice(3)

  given ControllerInterface: ControllerInterface 
    = Controller(PitchInterface, NumbersDiceInterface, ColorDiceInterface)
  
  given FileIOInterface: FileIOInterface = new XMLIO()
}

// -----------------------------------------------------------------------------------------------------
// ----------------------------------------------------------------------------------- OBJECT DEFINITION
object YellowPitch {
  given PitchInterface: PitchInterface = new PitchAsMatrix(7, 15, color = PitchWithColors(yellowColorsList, Color.yellow))

  given NumbersDiceInterface: DiceInterface = Numbers_dice(3)
  given ColorDiceInterface: DiceInterface = Colors_dice(3)

  given ControllerInterface: ControllerInterface 
    = Controller(PitchInterface, NumbersDiceInterface, ColorDiceInterface)

  given FileIOInterface: FileIOInterface = new JsonIO()
}