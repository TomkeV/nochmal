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

import Default.FileIOInterface
import util.FileIOInterface

import model.pitchComponent.PitchInterface
import model.Cross
import model.diceComponent.DiceInterface
import util.Event
import util.UndoManager
// kann ich auskommentieren ohne Fehler:
import controllerComponent.ControllerInterface
import util.Observable
import model.Filling


var diceResult = ""
var rounds = 0
var moveDone = false

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class Controller(var pitch:PitchInterface, val nums:DiceInterface, val colors:DiceInterface) extends ControllerInterface {

  val undoManager = new UndoManager[PitchInterface]
  val file_io = summon[FileIOInterface]
  var undoAllowed = false

  // Methode zum Ausführen einer beliebigen Operation
  def publish(c:Option[Cross] = None, e:Event) = 
    e match
      case Event.Applied => if(moveDone) then apply()
      case Event.Crossed => if (c.isDefined) then {
                              val cross = c.get
                              pitch = setCross(cross)
                              moveDone = true
                            }
      case Event.Diced => singleDice()
      case Event.Quit => beQuit()
      case Event.Redone => redo()
      case Event.Undone => undo()
      case Event.Saved => save()
      case Event.Loaded => load()

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
    moveDone = false
    rounds += 1

  def load() = {
    pitch = file_io.load(pitch)
  }

  def save() = {
    file_io.save(pitch)
  }

  override def toString = pitch.pitchToString()
}