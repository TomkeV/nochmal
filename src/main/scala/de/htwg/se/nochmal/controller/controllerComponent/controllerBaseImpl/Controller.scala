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

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class Controller(var pitch:PitchInterface, val nums:DiceInterface, val colors:DiceInterface) extends ControllerInterface {

  val undoManager = new UndoManager[PitchInterface]

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
    notifyObservers(e)
    
  def setCross(c:Cross) = 
    undoManager.undoAllowed = true
    undoManager.doMove(pitch, SetCommand(c))

  def singleDice(): Unit = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    diceResult = "Deine Wuerfelergebnisse: " + eol + n + eol + c

  def beQuit(): String =
    "Danke fuers Spielen!"

  def undo(): PitchInterface =
    undoManager.undoMove(pitch)

  def redo(): PitchInterface =
    undoManager.redoMove(pitch)

  def apply(): Unit = 
    undoManager.undoAllowed = false

  override def toString = pitch.pitchToString()
}