/**
  * Nochmal_main.scala
  * Hauptklasse zum Starten des Programms.
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheksimports
import scala.io.StdIn.readLine

// interne imports
import aview.TUI
import aview.myGUI
import Default.ControllerInterface
//import OrangePitch.ControllerInterface
//import BluePitch.ControllerInterface
//import YellowPitch.ControllerInterface
import de.htwg.se.nochmal.controller.controllerComponent.ControllerInterface

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ MAIN
@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myController = summon[ControllerInterface]

  val myGui = myGUI(myController)
  val myTui = TUI(myController)

  myTui.run


// ------------------------- alte imports
  //import model.dice.DiceInterface
  //import model.diceComponent.diceImplementierung.Colors_dice
  //import model.diceComponent.diceImplementierung.Numbers_dice
  //import model.pitchComponent.baseModel.PitchAsMatrix
  //import controller.controllerComponent.controllerBaseImpl.Controller
// ----------------------- alte Bestandteile
  //val myPitch = new PitchAsMatrix(7, 15)
  //val myNumdice = Numbers_dice(3)
  //val myColorsdice = Colors_dice(3)
  //val myController = Controller(myPitch, myNumdice, myColorsdice)