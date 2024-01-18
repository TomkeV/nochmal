/**
  * ButtonMap.scala
  * Klasse zum Speichern einer Matrix aus Buttons
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne imports
import controller.controllerComponent.ControllerInterface
import model.pitchComponent.PitchWithColorsInterface
import de.htwg.se.nochmal.model.Filling
import de.htwg.se.nochmal.util.{Observer, InputHandler, Event}

// Bibliotheksimports
import scala.swing.*
import java.awt.Color as jColor
import java.awt.Dimension
import javax.swing.JOptionPane


// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class ButtonMap(controller: ControllerInterface) extends Observer {
  controller.add(this)

  // ------------------------------------------------------------------- VARIABLEN 
  var number = ""
  var crossesSet = 0
  var color = ""

  val buttonMap = createMatrix

  // ------------------------------------------------------------------- FUNKTIONEN
  override def update(e: Event): Unit = {
    e match
      case Event.Applied => crossesSet = 0
                            number = ""
                            color = ""
      case Event.Crossed => 
      case Event.Diced => 
      case Event.Loaded => 
      case Event.Quit => 
      case Event.Redone => 
      case Event.Saved => 
      case Event.Undone => 
  }

  def createMatrix: Map[(Int, Int), Button] = {
    val rows = controller.pitch.row_num
    val cols = controller.pitch.col_num

    val buttons = for {
      y <- 0 to rows-1
      x <- 0 to cols-1
    } yield {
      val c = controller.pitch.myColor.colorsList(y)(x).getRGB
      val n =  "x" + ('A' + x).toChar.toString() + (y+1).toString()
      val b = new Button {
          background = jColor(c)
          name = n
          preferredSize = new Dimension(30, 30)
          reactions += {
            case event.ButtonClicked(_) =>
              if (number == "") then {
                JOptionPane.showMessageDialog(null, "Du musst eine Zahl auswählen!")
              } else if (color == "") then {
                JOptionPane.showMessageDialog(null, "Du musst eine Farbe auswählen!")
              } else if (number != "!") then {
                if (crossesSet < number.toInt) then {
                  if (c.toString == color) then
                    handleClick(this)
                  else if (color == "Joker!") then
                    handleClick(this)
                  else
                    JOptionPane.showMessageDialog(null, "Du musst deine ausgewählte Farbe ankreuzen!")
                }
              } else if (number == "!") {
                if (crossesSet < 5) then {
                  if (c.toString == color) then
                    handleClick(this)
                  else if (color == "Joker!") then
                    handleClick(this)
                  else
                    JOptionPane.showMessageDialog(null, "Du musst deine ausgewählte Farbe ankreuzen!")
                }
              }
            }
        }
      (y, x) -> b
      
    }
    buttons.toMap
  }

  def handleClick(b:Button) = {
    InputHandler.handle(b.name, controller)
    b.text = Filling.filled.toString()
    b.enabled = false
    crossesSet = crossesSet + 1
  }

  def updateButtonMap() = {
    println("Update ButtonMap aufgerufen")
    for (i <- 0 to controller.pitch.row_num-1) {
      for (x <- 0 to controller.pitch.col_num-1) {
        buttonMap((i,x)).text = controller.pitch.getIndex(i, x).toString()
        buttonMap((i,x)).enabled = false
      }
    }
    println("Update ButtonMap ausgeführt")
  }
}