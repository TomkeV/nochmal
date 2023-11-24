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

  var goOn = true

// NEU  
  def run =
    println(controller.pitch.toString)
    //getInput()
    inputGetAndAnalysis()

// NEU  
  override def update(e: Event) = 
      e match
        case Event.Quit => goOn = false
        case Event.Crossed => println(controller.pitch.toString) 
        case Event.Diced => println("Wo moechtest du ankreuzen? x Zahl Zahl")

// NEU NEU
  def inputGetAndAnalysis():Unit =
    val input = readLine 
    input match
      case "q" => controller.publishQuit()
      case "w" => controller.publishDice()
      case _ => {
        val chars = input.toCharArray()
          chars(0) match 
            case 'x' => 
              val line = chars(2).toString.toInt
              val col = chars(4).toString.toInt
              controller.publishCross(line, col)
      }
    if goOn then inputGetAndAnalysis()

// Alte Methoden: 
/*   def play(methodForInput: () => String):String =
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
                    println("Zeile: " + chars(2).toString.toInt)
                    //println("Spalte: " + chars(4).toString.toInt)
                    controller.set(chars(2).toString.toInt, chars(4).toString.toInt)
                    controller.toString
*/

/*   override def update() = printPitch  */

/*   def printPitch = 
    println(controller.pitch.toString)
    println(play(readLine)) */