package de.htwg.se.nochmal
package controller

import util.Observable
import util.Event

import model.Cross
import model.PitchAsMatrix

trait ControllerInterface extends Observable:
  var pitch:PitchAsMatrix
  def publish(c:Option[Cross] = None, e: Event) : Unit
  def undo(): PitchAsMatrix
  def redo(): PitchAsMatrix
  def beQuit(): String