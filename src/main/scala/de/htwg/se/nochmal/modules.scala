package de.htwg.se.nochmal

import model.pitchComponent.baseModel.PitchAsMatrix
import model.pitchComponent.PitchInterface

import de.htwg.se.nochmal.controller.controllerComponent.ControllerInterface
import de.htwg.se.nochmal.controller.controllerComponent.controllerBaseImpl.Controller

import de.htwg.se.nochmal.model.diceComponent.DiceInterface
import de.htwg.se.nochmal.model.diceComponent.diceImplementierung.Numbers_dice
import de.htwg.se.nochmal.model.diceComponent.diceImplementierung.Colors_dice
import de.htwg.se.nochmal.model.pitchComponent.baseModel.PitchWithColors
import de.htwg.se.nochmal.model.pitchComponent.baseModel.orangeColorsList

object Default {

  given PitchInterface: PitchInterface = new PitchAsMatrix(7, 15)

  given NumbersDiceInterface: DiceInterface = Numbers_dice(3)
  given ColorDiceInterface: DiceInterface = Colors_dice(3)

  given ControllerInterface: ControllerInterface 
    = Controller(PitchInterface, NumbersDiceInterface, ColorDiceInterface)
  
}

object Orange {
  
  given PitchInterface: PitchInterface = new PitchAsMatrix(7, 15, color = PitchWithColors(orangeColorsList))

  given NumbersDiceInterface: DiceInterface = Numbers_dice(3)
  given ColorDiceInterface: DiceInterface = Colors_dice(3)

  given ControllerInterface: ControllerInterface 
    = Controller(PitchInterface, NumbersDiceInterface, ColorDiceInterface)
  
}