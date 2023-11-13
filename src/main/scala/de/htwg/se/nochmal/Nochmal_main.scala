package de.htwg.se.nochmal

import model.Colors_dice
import model.Numbers_dice

import scala.io.StdIn.readLine
import de.htwg.se.nochmal.model.PitchAsMatrix
import model.PitchAsMatrix
import controller.Controller
//import aview.TUI
import model.Colors_dice
import model.Numbers_dice

@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myPitch = new PitchAsMatrix(7, 4)

  startgame(myPitch)

  //val controller = Controller(myPitch)

  //val tui = TUI(controller)
  //tui.run

def startgame(a_pitch:PitchAsMatrix):String =
  val eol = sys.props("line.separator")
  //val myPitch = new PitchAsMatrix(7, 4)
  val dicecolors = Colors_dice(3)
  val dicenumbers = Numbers_dice(3)
  println(a_pitch.toString)
  print("Bitte gib ein, was du tun moechtest.\n" +
    "q: beenden\n" +
    "w: wuerfeln\n" +
    "x: Feld ankreuzen\n")
  val eingabe = readLine().toString()

  eingabe match 
    case "q" => "Danke fuers Spielen!"
    case "w" => println(dicenumbers.roll_dice() + eol + dicecolors.roll_dice())
                startgame(a_pitch)
    case "x" => println("In welcher Zeile moechtest du ankreuzen?")
                val row = readLine().toInt
                println("In welcher Spalte moechtest du ankreuzen?")
                val col = readLine().toInt
                startgame(a_pitch.fillCell(row, col))
