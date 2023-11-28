package de.htwg.se.nochmal
package util

import controller.Controller

trait Command {
  def doMove: Unit
  def undoMove: Unit
  def redoMove: Unit
}

class setCommand(line:Int, col:Int, controller:Controller) extends Command {
  override def doMove: Unit = 
    controller.pitch = controller.pitch.fillCell(line-1, col-1)

  override def undoMove: Unit = 
    controller.pitch = controller.pitch.unfillCell(line-1, col-1)

  override def redoMove: Unit = 
    controller.pitch = controller.pitch.fillCell(line-1, col-1)
}

class UndoManager {
  private var undoStack: List[Command] = Nil 
  private var redoStack: List[Command] = Nil

  def doMove(command: Command) = {
    undoStack = command::undoStack
    command.doMove
  }

  def undoMove = {
    undoStack match {
      case Nil => 
      case head::stack => {
        head.undoMove
        undoStack = stack
        redoStack = head::redoStack
      }
    }
  }
}