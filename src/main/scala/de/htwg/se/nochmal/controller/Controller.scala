package de.htwg.se.nochmal
package controller

import util.Observable
import model.PitchAsMatrix
import model.Filling

import model.dice.DiceInterface
//import model.Numbers_dice
//import model.dice.diceImplementierung.Colors_dice

import util.Event
import util.UndoManager
import model.Cross
import scala.util.Try
import scala.util.Failure
import scala.util.Success

var diceResult = ""

case class Controller(var pitch:PitchAsMatrix, val nums:DiceInterface, val colors:DiceInterface) extends Observable:

  val undoManager = new UndoManager[PitchAsMatrix]
    
  // Publish Cross für Spalten > 9
  def publishCross(input: List[Char]) = {
    val x = input(1) match
    case 'A' | 'a' => Success(1)
    case 'B' | 'b' => Success(2)
    case 'C' | 'c' => Success(3)
    case 'D' | 'd' => Success(4)
    case 'E' | 'e' => Success(5)
    case 'F' | 'f' => Success(6)
    case 'G' | 'g' => Success(7)
    case 'H' | 'h' => Success(8)
    case 'I' | 'i' => Success(9)
    case 'J' | 'j' => Success(10)
    case 'K' | 'k' => Success(11)
    case 'L' | 'l' => Success(12)
    case 'M' | 'm' => Success(13)
    case 'N' | 'n' => Success(14)
    case 'O' | 'o' => Success(15)
    case _ => Failure(new Error("Keine gueltige Zeile!"))

    val cross = x match 
      case Failure(exception) => None
      case Success(value) => val col = value
                             val line = input(2).toString().toInt
                             Some(new Cross(line, col))
      pitch = setCross(cross)
      notifyObservers(Event.Crossed)
    x
  }

  def publishDice() =
    notifyObservers(Event.Diced)
   
  def publishQuit() =
    println(beQuit())
    notifyObservers(Event.Quit)

  def publishUndo() = 
    println(undo())
    notifyObservers(Event.Undone)

  def publishRedo() = 
    println(redo())
    notifyObservers(Event.Redone)
    
  // Ankreuzen einzelner Felder mit Try
  def setCross(c:Option[Cross]) = 
    undoManager.undoAllowed = true
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

  def undo(): PitchAsMatrix =
    undoManager.undoMove(pitch)

  def redo(): PitchAsMatrix =
    undoManager.redoMove(pitch)


// NEU für Apply
  def publishApply() = 
    apply()
    notifyObservers(Event.Applied)

  def apply(): Unit = 
    undoManager.undoAllowed = false

  override def toString = pitch.pitchToString()

// Raus seit 14.12.: 
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

    /*   // Methode set soll mit Undo kompatibel sein
  def set(line:Int, col:Int): PitchAsMatrix =
    undoManager.doMove(pitch, SetCommand(line, col))
    //pitch.fillCell(line-1, col-1) */

  // set zum ankreuzen mehrerer Felder
/*   def setList(crosses:List[Option[Cross]]) =
    undoManager.doMove(pitch, SetCommand(crosses))  */