package de.htwg.se.nochmal
package controller

import util.Observable
import model.PitchAsMatrix
import model.Filling
import model.Numbers_dice
import model.Colors_dice
import util.Event


case class Controller(var pitch:PitchAsMatrix, val nums:Numbers_dice, val colors:Colors_dice) extends Observable:
    
// NEU
  def publishCross(line:Int, col:Int) =
      pitch = set(line, col)
      notifyObservers(Event.Crossed)

  def publishDice() =
    println(dice())
    notifyObservers(Event.Diced)
   
  def publishQuit() =
    println(beQuit())
    notifyObservers(Event.Quit)
    
// NEU
  def set(line:Int, col:Int): PitchAsMatrix =
    pitch.fillCell(line-1, col-1)

// Neu
  def dice(): String = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    "Deine Wuerfelergebnisse: " + eol + n + eol + c

// Neu
  def beQuit(): String =
    "Danke fuers Spielen!"

  override def toString = pitch.pitchToString()

