package de.htwg.se.nochmal


import scala.io.StdIn.readLine

//import model.dice.DiceInterface
import model.dice.diceImplementierung.Colors_dice
import model.dice.diceImplementierung.Numbers_dice

import model.baseModel.PitchAsMatrix
import controller.controllerBaseImpl.Controller
import aview.TUI
import aview.myGUI

@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myPitch = new PitchAsMatrix(7, 15)
  val myNumdice = Numbers_dice(3)
  val myColorsdice = Colors_dice(3)
  val myController = Controller(myPitch, myNumdice, myColorsdice)

  val myGui = myGUI(myController)

  val myTui = TUI(myController)

  myTui.run