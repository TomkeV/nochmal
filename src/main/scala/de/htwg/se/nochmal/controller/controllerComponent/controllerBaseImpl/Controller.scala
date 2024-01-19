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
import scala.collection.mutable.ArrayBuffer

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class Controller(var pitch:PitchInterface, val nums:DiceInterface, val colors:DiceInterface) extends ControllerInterface {
   // ------------------------------------------------------------------- VARIABLEN
  val undoManager = new UndoManager[PitchInterface]
  val file_io = summon[FileIOInterface]

   // ------------------------------------------------------------------- FUNKTIONEN
  def publish(c:Option[Cross] = None, e:Event) = 
    e match
      case Event.Applied => if(moveDone) then apply()
      case Event.Crossed => if (c.isDefined) then {
                              pitch = setCross(c.get)
                              moveDone = true
                            }
      case Event.Diced => singleDice()
      case Event.Quit => beQuit()
      case Event.Redone => redo()
      case Event.Undone => if(moveDone) then pitch = undo()
      case Event.Saved => save()
      case Event.Loaded => load()
    notifyObservers(e)
    
  def setCross(c:Cross) = 
    crossArray.addOne(c)
    undoManager.doMove(pitch, SetCommand(c))

  def singleDice(): Unit = 
    moveDone = false
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
    crossArray.clear()
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