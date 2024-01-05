/**
  * Command.scala
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import controller.controllerComponent.ControllerInterface
//import controller.controllerBaseImpl.Controller

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITION
trait Command[T] {
  def noMove(t: T): T //wenn nichts getan wurde unverändertes T zurückliefern
  def doMove(t: T): T
  def undoMove(t: T): T
  def redoMove(t: T): T
}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class UndoManager[T] {
  var undoAllowed = false;
  private var undoStack: List[Command[T]] = Nil 
  private var redoStack: List[Command[T]] = Nil

  def doMove(t: T, command: Command[T]): T = {
    undoStack = command::undoStack // legt Objekt auf Stack, damit undo möglich
    command.doMove(t)
  }

  def undoMove(t: T): T = {
    undoStack match {
      case Nil =>t
      case head::stack => 
        val result = head.undoMove(t)
        undoStack = stack
        redoStack = head::redoStack

        return result
    }
  }

  def redoMove(t: T): T = {
    redoStack match {
      case Nil => t
      case head :: stack => {
        val result = head.redoMove(t)
        redoStack = stack
        undoStack = head :: undoStack
        
        return result
      }
    }
  }
}