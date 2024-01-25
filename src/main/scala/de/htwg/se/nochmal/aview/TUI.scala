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
        case Event.Applied => 
          if (rounds == num_of_rounds) {
            println(evalWin(summe))
          } else {
            println("Runde " + rounds + " von " + num_of_rounds)
            println("Aktueller Punktestand: " + summe)
          }               
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

  def evalWin(s:Int): String = {
    goOn = false
    if (s > 32) 
      "* * * * *\nEs gibt also doch Superhelden!"
    else if (s > 24)
      "* * * *\nDu könntest auch professioneller NOCH MAL!-Spieler sein!"
    else if (s > 16) 
      "* * *\nKlasse, das lief ja gut!"
    else if (s > 8)
      "* *\nDas war wohl nicht dein erstes Mal!"
    else if (s > 0)
      "*\nDas geht noch besser! "
    else if (s == 0)
      "Dabei sein ist alles!"
    else 
      "Fehler! Wie konnte das passieren?"
  }
}