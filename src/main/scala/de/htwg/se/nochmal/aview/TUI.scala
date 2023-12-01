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
import util.InputHandler


class TUI(controller: Controller) extends Observer:
  controller.add(this) // fügt TUI in die Liste hinzu, sodass update funktioniert

  var goOn = true
 
  def run =
    println(controller.pitch.toString)
    inputGetAndAnalysis()
 
  override def update(e: Event) = 
      e match
        case Event.Quit => goOn = false
        case Event.Crossed => println(controller.pitch.toString) 
        case Event.Diced => println("Wo moechtest du ankreuzen? x Zahl Zahl")

  def inputGetAndAnalysis():Unit =
    val input = readLine 
    InputHandler.handle(input, controller)
/*     input match
      case "q" => controller.publishQuit()
      case "w" => controller.publishDice()
      case _ => {
        val chars = input.toLowerCase().toCharArray()
        val inputString = analyseChars(chars)
        chars(0) match
          case 'x' => controller.publishCross(inputString)
          case ' ' => 
            chars(1) match
              case 'x' => controller.publishCross(inputString)
          case _ => println("Ungueltige Eingabe!") */
        
        
        // TODO: controller.cross so umschreiben, dass String verarbeitet wird!
/*           chars(0) match 
            case 'x' => 
              val line = chars(2).toString.toInt
              val col = chars(4).toString.toInt
              controller.publishCross(line, col) */
      //}
    if goOn then inputGetAndAnalysis()


  // def analyseChars(array:Array[Char]):String = 
  //   val range = Range(0, array.length).toList
  //   val tmpArray = range.map(i =>
  //         if(array(i).isDigit) {
  //           array(i)
  //         } else if (array(i).isLetter) {
  //           'x'
  //         } else {

  //         }
  //         )
  //   var resString = "" // noch nicht funktional programmiert!
  //   for (i <- 0 to array.length-1) {
  //     if (tmpArray(i) != ()) {
  //       resString += tmpArray(i)
  //     }
  //   }
  //   return resString


// case _ vorher:
/*   val chars = input.toCharArray()
          chars(0) match 
            case 'x' => 
              val line = chars(2).toString.toInt
              val col = chars(4).toString.toInt
              controller.publishCross(line, col) */