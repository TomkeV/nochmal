package de.htwg.se.nochmal
package controller

import util.Observable
import model.PitchAsMatrix
import model.Filling
import model.Numbers_dice
import model.Colors_dice
import util.Event
import util.UndoManager


case class Controller(var pitch:PitchAsMatrix, val nums:Numbers_dice, val colors:Colors_dice) extends Observable:

  val undoManager = new UndoManager[PitchAsMatrix]
    
  def publishCross(line:Int, col:Int) =
    pitch = set(line, col)
    notifyObservers(Event.Crossed)
  
  def publishCross(input:String) = 
    // Command erzeugen, Move ausführen
    val splitArray = input.split("""x""")
    for (i <- 0 to splitArray.length-1) {
        if (splitArray(i).toString.length() > 1) {
            val line = splitArray(i)(0).toString().toInt
            val col = splitArray(i)(1).toString().toInt
            pitch = set(line, col)
        }
    }
    notifyObservers(Event.Crossed)

  def publishDice() =
    println(dice())
    notifyObservers(Event.Diced)
   
  def publishQuit() =
    println(beQuit())
    notifyObservers(Event.Quit)
    
  // Methode set soll mit Undo kompatibel sein
  def set(line:Int, col:Int): PitchAsMatrix =
    undoManager.doMove(pitch, SetCommand(line, col))
    //pitch.fillCell(line-1, col-1)

  def dice(): String = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    "Deine Wuerfelergebnisse: " + eol + n + eol + c

  def beQuit(): String =
    "Danke fuers Spielen!"

  override def toString = pitch.pitchToString()

