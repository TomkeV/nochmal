package de.htwg.se.nochmal
package aview

import controller.Controller
import util.Observer
import util.Event
import model.Filling
import scala.io.StdIn.readLine
import model.PitchAsMatrix
import controller.Controller
//import aview.TUI
import model.Colors_dice
import model.Numbers_dice


class TUI(controller: Controller) extends Observer:
  controller.add(this) // fügt TUI in die Liste hinzu, sodass update funktioniert

  def startgame = 
    println(controller.pitch.toString)
    play()

  override def update() = println("Next one...") // absoluter Quark

  def play():Unit =
    val eol = sys.props("line.separator")
    val dicecolors = Colors_dice(3)
    val dicenumbers = Numbers_dice(3)
    print("Bitte gib ein, was du tun moechtest.\n" +
      "q: beenden\n" +
      "w: wuerfeln\n" +
      "x: Feld ankreuzen\n")
    
    val input = readLine()
    input match
      case "q" => println("Danke fuers Spielen")
      case "w" => println(controller.dice())
      case "x" => println("In welcher Zeile moechtest du ankreuzen?")
                  val row = readLine().toInt
                  println("In welcher Spalte moechtest du ankreuzen?")
                  val col = readLine().toInt
                  controller.set(row, col)
                  println(controller.toString)
  play()