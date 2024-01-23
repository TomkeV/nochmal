/**
  * TUI.scala
  * Class for text-based user-interface of the game "Nochmal!"
  * Link to the game: https://www.schmidtspiele.de/details/produkt/noch-mal-.html
  * @author: Tomke Velten
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
import controller.{diceResult, rounds, summe}
import util.Observer
import util.Event
import util.InputHandler

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class TUI(controller: ControllerInterface) extends Observer {
  controller.add(this)

  // ------------------------------------------------------------- VARIABLEN
  var goOn = true
  val num_of_rounds = controller.pitch.col_num * 2

  // ------------------------------------------------------------- FUNKTIONEN
  override def update(e: Event) = 
      e match
        case Event.Quit => goOn = false
                            println("Danke fuers Spielen!")
        case Event.Crossed => println(controller.pitch.toString) 
        case Event.Applied => if rounds == num_of_rounds then goOn = false
                              println("Runde " + rounds + " von " + num_of_rounds)
                              println("Aktueller Punktestand: " + summe)
        case Event.Diced => println(diceResult)
        case Event.Undone => println(controller.undo())
        case Event.Redone => println(controller.redo())
        case Event.Saved => println("Aktueller Spielstand wurde gespeichert.")
        case Event.Loaded => println(controller.pitch.toString) 
    
  def run =
    println(controller.pitch.toString)
    getAndAnalyseInput()

  def getAndAnalyseInput(): Unit =
    val input = readLine 
    InputHandler.handle(input, controller)
    if goOn then getAndAnalyseInput()
}