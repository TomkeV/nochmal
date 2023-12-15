package de.htwg.se.nochmal
package aview

import scala.io.StdIn.readLine

import controller.Controller
import util.Observer
import util.Event
import model.PitchAsMatrix
import util.InputHandler
import controller.diceResult


class TUI(controller: Controller) extends Observer:
  controller.add(this) // fügt TUI in die Liste hinzu, sodass update funktioniert

  var goOn = true
  var rounds = 0
  val num_of_rounds = controller.pitch.col_num * 2
 
  def run =
    println(controller.pitch.toString)
    inputGetAndAnalysis()
 
  override def update(e: Event) = 
      e match
        case Event.Quit => goOn = false
        case Event.Crossed => println(controller.pitch.toString) 
                              //rounds += 1
                              //if rounds == num_of_rounds then goOn = false
                              //println("Runde " + rounds + " von " + num_of_rounds)
        case Event.Applied => rounds += 1
                              if rounds == num_of_rounds then goOn = false
                              println("Runde " + rounds + " von " + num_of_rounds)
        case Event.Diced => controller.singleDice()
                            println(diceResult)
                            //val d = controller.dice()
                            // println(d)
        case Event.Undone => println(controller.undo())
                            rounds -= 1
                            if rounds > num_of_rounds then goOn = true
                            println("Runde " + rounds + " von " + num_of_rounds)
        case Event.Redone => println(controller.redo())
                            rounds += 1
                            if rounds == num_of_rounds then goOn = false
                            println("Runde " + rounds + " von " + num_of_rounds)

  def inputGetAndAnalysis():Unit =
    val input = readLine 
    InputHandler.handle(input, controller)
    if goOn then inputGetAndAnalysis()