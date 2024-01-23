/**
  * GUISpielfeld.scala
  * Klasse zum Aufbau einer GUI
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne Imports
import controller.controllerComponent.ControllerInterface
import util.{Observer, Event, EvenOdd, EvenEvent, OddEvent}
import controller.summe

// Bibliotheksimports
import scala.swing.*
import java.awt.Color as jColor
import javax.swing.{BorderFactory, BoxLayout}

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class GUISpielfeld(controller: ControllerInterface, buttons:ButtonMap) extends Observer {
  controller.add(this)

  // ------------------------------------------------------------------- VARIABLEN 
  private val pitchBackground = jColor(controller.pitch.pitchColor.background.getRGB)
  private val titel = createTitle(controller.pitch.col_num)
  private val matrix = createMatrixFromButtonMap(controller.pitch.row_num, controller.pitch.col_num)
  private val punkte = createPoints(controller.pitch.col_num)

  val spielfeld = setUpPitch(titel, matrix, punkte)

  //var summe = 0

  // ------------------------------------------------------------------- FUNKTIONEN
  override def update(e: Event): Unit = {
    e match
      case Event.Applied => 
      case Event.Crossed => 
      case Event.Diced => 
      case Event.Loaded => 
      case Event.Quit => 
      case Event.Redone => 
      case Event.Saved => 
      case Event.Undone => 
  }

  def setUpPitch(titel:GridPanel, feld:GridPanel, punkte:GridPanel): BoxPanel = {
    new BoxPanel(Orientation.Vertical) {
      contents += titel
      contents += feld
      contents += punkte
    }
  }

  def createTitle(col:Int):GridPanel = {
    new GridPanel(1, col) {
      border = BorderFactory.createMatteBorder(10, 20, 10, 20, pitchBackground)
      for (i <- 0 to col-1) {
        contents += new Label((('A' + i).toChar).toString()) {
          background = jColor.WHITE
          foreground = jColor.BLACK
        }
      }
    }
  }

  def createPoints(col:Int):GridPanel = {
    val cellWidth = 3
    new GridPanel(1, col) {
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, pitchBackground)
      val pointStringArray = if (col%2 == 0) {
                                EvenOdd.handle(EvenEvent())(cellWidth, col).toCharArray()
                              } else {
                                EvenOdd.handle(OddEvent())(cellWidth, col).toCharArray()
                              }

      Range(0, pointStringArray.length-1).map(x => 
        if (pointStringArray(x).isDigit) {
          contents += new Button(pointStringArray(x).toString()) {
            name = pointStringArray(x).toString()
            foreground = jColor.BLACK
            background = jColor.WHITE
            border = BorderFactory.createMatteBorder(0, 10, 0, 10, pitchBackground)
            reactions += {
              case event.ButtonClicked(_) =>
                summe += name.toInt
                this.enabled = false
            }
          }
        })
    }
  }

  def createMatrixFromButtonMap(rowNum:Int, colNum:Int): GridPanel = {
    val b = buttons.buttonMap
    new GridPanel(rowNum, colNum) {
      border = BorderFactory.createMatteBorder(0, 20, 10, 20, pitchBackground)
      for (y <- 0 to rowNum-1) {
        for (x <- 0 to colNum-1) {
          contents += b((y, x))
        }
      }
    }
  }

  def updatePitch() = {
    buttons.updateButtonMap()
  }
}