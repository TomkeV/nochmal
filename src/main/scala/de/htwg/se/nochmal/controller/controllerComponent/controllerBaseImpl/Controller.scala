package de.htwg.se.nochmal
package controller
package controllerComponent
package controllerBaseImpl

import scala.util.{Try, Failure, Success}

import model.pitchComponent.PitchInterface
//import model.PitchAsMatrix
import model.Filling
import model.Cross
import model.diceComponent.DiceInterface
//import model.Numbers_dice
//import model.dice.diceImplementierung.Colors_dice

import util.Observable
import util.Event
import util.UndoManager
import controllerComponent.ControllerInterface


var diceResult = ""

case class Controller(var pitch:PitchInterface, val nums:DiceInterface, val colors:DiceInterface) extends ControllerInterface:

  val undoManager = new UndoManager[PitchInterface]

  // Methode zum Ausführen einer beliebigen Operation
  def publish(c:Option[Cross] = None, e:Event) = 
    e match
      case Event.Applied => apply()
      case Event.Crossed => if (c.isDefined) then {
                              val cross = c.get
                              pitch = setCross(cross)
                            }
      case Event.Diced => singleDice()
      case Event.Quit => beQuit()
      case Event.Redone => redo()
      case Event.Undone => undo()
    notifyObservers(e)
    
  def setCross(c:Cross) = 
    undoManager.undoAllowed = true
    undoManager.doMove(pitch, SetCommand(c))

  def singleDice(): Unit = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    diceResult = "Deine Wuerfelergebnisse: " + eol + n + eol + c

  def beQuit(): String =
    "Danke fuers Spielen!"

  def undo(): PitchInterface =
    undoManager.undoMove(pitch)

  def redo(): PitchInterface =
    undoManager.redoMove(pitch)

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

  /*   def dice(): String = 
    val n = nums.roll_dice()
    val c = colors.roll_dice()
    val eol = sys.props("line.separator")
    "Deine Wuerfelergebnisse: " + eol + n + eol + c */

// Raus ab 15.12. durch einheitliches publish():
/*   def publishApply() = 
    apply()
    notifyObservers(Event.Applied) */
/*    def publishQuit() =
    println(beQuit())
    notifyObservers(Event.Quit)

  def publishUndo() = 
    println(undo())
    notifyObservers(Event.Undone)

  def publishRedo() = 
    println(redo())
    notifyObservers(Event.Redone) */
  
  /*   def publishDice() =
    notifyObservers(Event.Diced) */

    // Publish Cross für Spalten > 9
/*   def publishCross(input: List[Char]) = {
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
  } */