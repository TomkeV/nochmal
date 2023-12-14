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
import scala.util.Failure
import scala.util.Success

var diceResult = ""

case class Controller(var pitch:PitchAsMatrix, val nums:Numbers_dice, val colors:Colors_dice) extends Observable:

  val undoManager = new UndoManager[PitchAsMatrix]
    
/*   def publishCross(input:String) = 
    // Command erzeugen, Move ausführen
    val splitArray = input.split("""x""")
    val range = Range(0, splitArray.length)
    var res = range.map(x =>
      if(splitArray(x).length() > 1) {
        val line = splitArray(x)(0).toString().toInt
        val col = splitArray(x)(1).toString().toInt
        Some(new Cross(line, col))
      } else {
        None
      }).toList
    pitch = setList(res)
    notifyObservers(Event.Crossed) */

      // Publish Cross mit mehr Zeilen
  def publishCross(input: List[Char]) = {
    val x = input(1) match
    case 'A' | 'a' => Try(1)
    case 'B' | 'b' => Try(2)
    case 'C' | 'c' => Try(3)
    case 'D' | 'd' => Try(4)
    case 'E' | 'e' => Try(5)
    case 'F' | 'f' => Try(6)
    case 'G' | 'g' => Try(7)
    case 'H' | 'h' => Try(8)
    case 'I' | 'i' => Try(9)
    case 'J' | 'j' => Try(10)
    case 'K' | 'k' => Try(11)
    case 'L' | 'l' => Try(12)
    case 'M' | 'm' => Try(13)
    case 'N' | 'n' => Try(14)
    case 'O' | 'o' => Try(15)
    case _ => Failure(new Error("Keine gueltige Zeile!"))

    val cross = x match 
      case Failure(exception) => None
      case Success(value) => val line = value
                             val col = input(2).toString().toInt
                             Some(new Cross(line, col))
      pitch = setCross(cross)
      notifyObservers(Event.Crossed)
  }

  def publishDice() =
    //println(dice())
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
    
/*   // Methode set soll mit Undo kompatibel sein
  def set(line:Int, col:Int): PitchAsMatrix =
    undoManager.doMove(pitch, SetCommand(line, col))
    //pitch.fillCell(line-1, col-1) */

  // set zum ankreuzen mehrerer Felder
/*   def setList(crosses:List[Option[Cross]]) =
    undoManager.doMove(pitch, SetCommand(crosses))  */

    // Ankreuzen einzelner Felder mit Try
  def setCross(c:Option[Cross]) = 
    undoManager.doMove(pitch, SetCommand(List(c)))
 
  def dice(): String = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    "Deine Wuerfelergebnisse: " + eol + n + eol + c

  def singleDice(): Unit = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    diceResult = "Deine Wuerfelergebnisse: " + eol + n + eol + c

  def beQuit(): String =
    "Danke fuers Spielen!"


  // NEU für Undo-Manager
  def undo(): PitchAsMatrix =
    undoManager.undoMove(pitch)

  def redo(): PitchAsMatrix =
    undoManager.redoMove(pitch)

  override def toString = pitch.pitchToString()

// old Stuff
/*   def publishCross(line:Int, col:Int) =
    pitch = set(line, col)
    notifyObservers(Event.Crossed) */

// ehemals publishCross
/*      for (i <- 0 to splitArray.length-1) {
        if (splitArray(i).toString.length() > 1) {
            val line = splitArray(i)(0).toString().toInt
            val col = splitArray(i)(1).toString().toInt
            pitch = set(line, col)
        }
      }  */