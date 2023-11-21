package de.htwg.se.nochmal
package aview

import scala.io.StdIn.readLine

import controller.Controller
import util.Observer
import util.Event
import model.Filling
import model.PitchAsMatrix
import model.Colors_dice
import model.Numbers_dice


class TUI(controller: Controller) extends Observer:
  controller.add(this) // fügt TUI in die Liste hinzu, sodass update funktioniert

  def printPitch = 
    println(controller.pitch.toString)
    println(play(readLine))

  override def update() = printPitch 

  def play(methodForInput: () => String):String =
    val eol = sys.props("line.separator")
    val dicecolors = Colors_dice(3)
    val dicenumbers = Numbers_dice(3)
    print("Bitte gib ein, was du tun moechtest.\n" +
      "q: beenden\n" +
      "w: wuerfeln\n" +
      "x Zahl Zahl: Feld ankreuzen\n")
    
    val input = methodForInput()
    input match
      case "q" => "Danke fuers Spielen!"
      case "w" => controller.dice()
      case _ => val chars = input.toCharArray()
                chars(0) match 
                  case 'x' => 
                    //println("Zeile: " + chars(2).toString.toInt)
                    //println("Spalte: " + chars(4).toString.toInt)
                    controller.set(chars(2).toString.toInt, chars(4).toString.toInt)
                    controller.toString
