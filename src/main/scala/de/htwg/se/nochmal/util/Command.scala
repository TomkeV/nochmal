package de.htwg.se.nochmal
package util

import controller.Controller
import scala.util.Try
import scala.util.Success

trait Command[T] {
  def noMove(t: T): Try[T] //wenn nichts getan wurde unverändertes T zurückliefern
  def doMove(t: T): Try[T]
  def undoMove(t: T): Try[T]
  def redoMove(t: T): Try[T]
}


class UndoManager[T] {
  private var undoStack: List[Command[T]] = Nil 
  private var redoStack: List[Command[T]] = Nil

  def doMove(t: T, command: Command[T]) = {
    undoStack = command::undoStack // legt Objekt auf Stack, damit undo möglich
    command.doMove(t)
  }

  def undoMove(t: T): Try[T] = {
    undoStack match {
      case Nil => Success(t)
      case head::stack => 
        val result = head.undoMove(t)
        undoStack = stack
        redoStack = head::redoStack

        return result
    }
  }

  def redoMove(t: T): Try[T] = {
    redoStack match {
      case Nil => Success(t)
      case head :: stack => {
        val result = head.redoMove(t)
        redoStack = stack
        undoStack = head :: undoStack
        
        return result
      }
    }
  }
}