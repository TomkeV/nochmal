/**
  * TUI.scala
  * Class for text-based user-interface of the game "Nochmal!"
  * Link to the game: https://www.schmidtspiele.de/details/produkt/noch-mal-.html
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheks-Imports
import scala.io.StdIn.readLine

// interne Imports
import controller.controllerComponent.ControllerInterface
//import controller.controllerBaseImpl.Controller
import controller.controllerComponent.controllerBaseImpl.diceResult
import util.Observer
import util.Event
import util.InputHandler
import model.pitchComponent.baseModel.PitchAsMatrix

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class TUI(controller: ControllerInterface) extends Observer {
  controller.add(this) // ermöglicht update

  var goOn = true // speichert, ob noch Runden verfügbar sind
  var rounds = 0 // speichert aktuelle Rundenzahl
  val num_of_rounds = controller.pitch.col_num * 2 // speichert maximale Rundenzahl

  // Methode zum Starten des Spiels
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
        case Event.Diced => println(diceResult)
        case Event.Undone => println(controller.undo())
                            rounds -= 1
                            if rounds > num_of_rounds then goOn = true
                            println("Runde " + rounds + " von " + num_of_rounds)
        case Event.Redone => println(controller.redo())
                            rounds += 1
                            if rounds == num_of_rounds then goOn = false
                            println("Runde " + rounds + " von " + num_of_rounds)

  // rekursive Methode zum Einlesen der Nutzereingaben
  def inputGetAndAnalysis(): Unit =
    val input = readLine 
    InputHandler.handle(input, controller)
    if goOn then inputGetAndAnalysis()
}