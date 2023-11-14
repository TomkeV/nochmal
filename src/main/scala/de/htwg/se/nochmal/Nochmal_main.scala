package de.htwg.se.nochmal

import model.Colors_dice
import model.Numbers_dice

import scala.io.StdIn.readLine
import model.PitchAsMatrix
import controller.Controller
import model.Colors_dice
import model.Numbers_dice
import aview.TUI

@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myPitch = new PitchAsMatrix(7, 4)
  val myNumdice = Numbers_dice(3)
  val myColorsdice = Colors_dice(3)
  val myController = Controller(myPitch, myNumdice, myColorsdice)
  val myTui = TUI(myController)

  myTui.startgame