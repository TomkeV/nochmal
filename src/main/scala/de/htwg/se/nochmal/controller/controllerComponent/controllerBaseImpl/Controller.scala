/**
  * Controller.scala
  * Controller implementation for the normal use.
  * @author: Tomke Velten
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package controller
package controllerComponent
package controllerBaseImpl

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import Default.FileIOInterface
import util.{Event, UndoManager}
import util.ioComponent.FileIOInterface
import model.pitchComponent.PitchInterface
import model.diceComponent.DiceInterface
import model.Cross

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class Controller(var pitch:PitchInterface, val nums:DiceInterface, val colors:DiceInterface) extends ControllerInterface {
   // ------------------------------------------------------------------- VARIABLEN
  val undoManager = new UndoManager[PitchInterface]
  val file_io = summon[FileIOInterface]

   // ------------------------------------------------------------------- FUNKTIONEN
  def publish(c:Option[Cross] = None, e:Event) = {
    e match
      case Event.Applied => if(moveDone) then apply()
      case Event.Crossed => if (c.isDefined) then {
                              pitch = setCross(c.get)
                              moveDone = true
                            }
      case Event.Diced => rollDice()
      case Event.Quit => beQuit()
      case Event.Redone => redo()
      case Event.Undone => if(moveDone) then pitch = undo()
      case Event.Saved => save()
      case Event.Loaded => load()
    notifyObservers(e)
  }

  def beQuit(): String = {
    "Danke fuers Spielen!"
  }

  def undo(): PitchInterface =
    undoManager.undoMove(pitch)

  def redo(): PitchInterface =
    undoManager.redoMove(pitch)

  def apply(): Unit = {
    crossArray.clear()
    crossArray.empty
    moveDone = false
    rounds += 1
  }

  def load() = {
    pitch = file_io.load(pitch)
  }

  def save() = {
    file_io.save(pitch)
  }

  def setCross(c:Cross) = {
    crossArray.addOne(c)
    undoManager.doMove(pitch, SetCommand(c))
  }

  def rollDice(): Unit = {
    moveDone = false
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    diceResult = "Deine Wuerfelergebnisse: " + eol + n + eol + c
  }
}