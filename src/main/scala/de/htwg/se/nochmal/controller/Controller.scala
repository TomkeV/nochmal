package de.htwg.se.nochmal
package controller

import util.Observable
import model.PitchAsMatrix
import model.Filling
import model.Numbers_dice
import model.Colors_dice


case class Controller(var pitch:PitchAsMatrix, val nums:Numbers_dice, val colors:Colors_dice) extends Observable:
    
  // kreuze ein Feld an:
  def set(row:Int, col:Int) = 
    pitch = pitch.fillCell(row-1, col-1)
    notifyObservers
  
  // Lass die Würfel rollen!
  def dice(): String =
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    val res = n + eol + c
    notifyObservers
    return res

  override def toString = pitch.pitchToString()