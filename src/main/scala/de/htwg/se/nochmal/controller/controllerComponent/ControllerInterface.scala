package de.htwg.se.nochmal
package controller
package controllerComponent

import util.Observable
import util.Event

import model.Cross
import model.pitchComponent.PitchInterface
//import model.baseModel.PitchAsMatrix

trait ControllerInterface extends Observable:
  var pitch:PitchInterface
  def publish(c:Option[Cross] = None, e: Event) : Unit
  def undo(): PitchInterface
  def redo(): PitchInterface
  def beQuit(): String