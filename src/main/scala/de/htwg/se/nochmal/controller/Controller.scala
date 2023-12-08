package de.htwg.se.nochmal
package controller

import util.Observable
import model.PitchAsMatrix
import model.Filling
import model.Numbers_dice
import model.Colors_dice
import util.Event
import util.UndoManager
import model.Cross
import scala.util.Try
import scala.util.Success
import scala.util.Failure


case class Controller(var pitch:PitchAsMatrix, val nums:Numbers_dice, val colors:Colors_dice) extends Observable:

  val undoManager = new UndoManager[PitchAsMatrix]
    
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
    /*     val r = Range(0, splitArray.length)
    val res = r.map(i =>
      if(splitArray(i).length() > 1) {
        val line = splitArray(i)(0).toString().toInt
        val col = splitArray(i)(1).toString().toInt
        new Cross(line, col)
      } else {
        "skip"
      }).toList.filter(_.isInstanceOf[Cross])
    pitch = set(res.filter(_.isInstanceOf[Crro])) */

  def publishDice() =
    println(dice())
    notifyObservers(Event.Diced)
   
  def publishQuit() =
    println(beQuit())
    notifyObservers(Event.Quit)

  // NEU wegen undo
  def publishUndo() = 
    println(undo())
    notifyObservers(Event.Undone)

  // NEU wegen redo
  def publishRedo() = 
    println(redo())
    notifyObservers(Event.Redone)
    
  // Methode set soll mit Undo kompatibel sein
  def set(line:Int, col:Int): Try[PitchAsMatrix]=
    if (line >= 0 && col >= 0) then
      Success(undoManager.doMove(pitch, SetCommand(line, col)))
    else 
      Failure(new Error("Feler in Controller.set"))
    //pitch.fillCell(line-1, col-1)

/*   // set zum ankreuzen mehrerer Felder
  def set(crosses:List[Cross]) =
    undoManager.doMove(pitch, SetCommand(crosses)) 
 */
  def dice(): String = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    "Deine Wuerfelergebnisse: " + eol + n + eol + c

  def beQuit(): String =
    "Danke fuers Spielen!"


  // NEU für Undo-Manager
  def undo(): Try[PitchAsMatrix] =
    undoManager.undoMove(pitch)

  def redo(): Try[PitchAsMatrix] =
    undoManager.redoMove(pitch)

  override def toString = pitch.pitchToString()

// old Stuff
/*   def publishCross(line:Int, col:Int) =
    pitch = set(line, col)
    notifyObservers(Event.Crossed) */