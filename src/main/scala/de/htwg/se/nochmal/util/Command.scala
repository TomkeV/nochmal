package de.htwg.se.nochmal
package util


import controller.ControllerInterface
//import controller.controllerBaseImpl.Controller

trait Command[T] {
  def noMove(t: T): T //wenn nichts getan wurde unverändertes T zurückliefern
  def doMove(t: T): T
  def undoMove(t: T): T
  def redoMove(t: T): T
}


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