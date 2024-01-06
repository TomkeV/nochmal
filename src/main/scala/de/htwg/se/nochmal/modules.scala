package de.htwg.se.nochmal

import com.google.inject.AbstractModule
import de.htwg.se.nochmal.model.pitchComponent.PitchInterface
import de.htwg.se.nochmal.model.pitchComponent.baseModel.PitchAsMatrix
import de.htwg.se.nochmal.model.diceComponent.diceImplementierung.Numbers_dice
import de.htwg.se.nochmal.model.diceComponent.diceImplementierung.Colors_dice
import de.htwg.se.nochmal.controller.controllerComponent.ControllerInterface
import de.htwg.se.nochmal.controller.controllerComponent.controllerBaseImpl.Controller

class Modules() extends AbstractModule {
  private val myPitch = new PitchAsMatrix(7, 15)
  private val myNumsDice = Numbers_dice(3)
  private val myColorsDice = Colors_dice(3)
  
  override def configure(): Unit = {
    bind(classOf[ControllerInterface]).toInstance(new Controller(myPitch, myNumsDice, myColorsDice))
  }

}





// modules für Scala-DI (Branch Dev_10-DI)
/* import model.pitchComponent.baseModel.PitchAsMatrix
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
  
} */