/**
  * Controller.scala
  * Controller implementation for the normal use.
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package controller
package controllerComponent
package controllerBaseImpl

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import scala.util.{Try, Failure, Success}

import model.pitchComponent.PitchInterface
//import model.PitchAsMatrix
import model.Filling
import model.Cross
import model.diceComponent.DiceInterface
//import model.Numbers_dice
//import model.dice.diceImplementierung.Colors_dice
import util.Observable
import util.Event
import util.UndoManager
import controllerComponent.ControllerInterface


var diceResult = ""
var rounds = 0

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class Controller(var pitch:PitchInterface, val nums:DiceInterface, val colors:DiceInterface) extends ControllerInterface {

  val undoManager = new UndoManager[PitchInterface]
  var undoAllowed = false

  // Methode zum Ausführen einer beliebigen Operation
  def publish(c:Option[Cross] = None, e:Event) = 
    e match
      case Event.Applied => apply()
      case Event.Crossed => if (c.isDefined) then {
                              val cross = c.get
                              pitch = setCross(cross)
                            }
      case Event.Diced => singleDice()
      case Event.Quit => beQuit()
      case Event.Redone => redo()
      case Event.Undone => undo()
        /* if(undoAllowed)
          undo()
        else 
          println("Nicht moeglich!") */
    notifyObservers(e)
    
  def setCross(c:Cross) = 
    undoAllowed = true
    undoManager.doMove(pitch, SetCommand(c))

  def singleDice(): Unit = 
    undoAllowed = false
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    diceResult = "Deine Wuerfelergebnisse: " + eol + n + eol + c

  def beQuit(): String =
    "Danke fuers Spielen!"

/*   def undo(): PitchInterface =
    undoManager.undoMove(pitch) */

  def undo(): String =
    if (undoAllowed) then 
      undoManager.undoMove(pitch).toString()
    else 
      "Nicht moeglich!"

  def redo(): PitchInterface =
    undoManager.redoMove(pitch)

  def apply(): Unit = 
    undoAllowed = false
    rounds += 1

  override def toString = pitch.pitchToString()
}