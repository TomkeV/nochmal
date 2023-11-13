package de.htwg.se.nochmal

import model.Colors_dice
import model.Numbers_dice

import scala.io.StdIn.readLine
import de.htwg.se.nochmal.model.PitchAsMatrix
import model.PitchAsMatrix
import controller.Controller
//import aview.TUI
import model.PitchAsMatrix

@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myPitch = new PitchAsMatrix(1, 1)
  println(myPitch.toString)

  val controller = Controller(myPitch)

  //val tui = TUI(controller)
  //tui.run

/*   val my_pitch = start_game()
  println(my_pitch.pitchToString())
  val dice_colors = Colors_dice()
  val dice_numbers = Numbers_dice()
  println(dice_numbers.roll_dice() + dice_colors.roll_dice()) */


/* def start_game(): pitch =
  println("Hallo! Wie viele Zeilen soll dein Spielfeld haben? Standard: 4")
  val numOfRows = readLine().toInt
  println("Und wie viele Spalten? Standard: 7")
  val numOfCols = readLine().toInt
  val createdPitch = pitch(numOfRows, numOfCols, 3)
  return createdPitch */

