package de.htwg.se.nochmal


import scala.io.StdIn.readLine

import model.Colors_dice
import model.Numbers_dice
import model.PitchAsMatrix
import controller.Controller
import aview.TUI
import aview.GUI

@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myPitch = new PitchAsMatrix(4, 7)
  val myNumdice = Numbers_dice(3)
  val myColorsdice = Colors_dice(3)
  val myController = Controller(myPitch, myNumdice, myColorsdice)
  val myTui = TUI(myController)

  val gui = new GUI(controller)

  myTui.run