/**
  * GUISpielfeld.scala
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
import util.{Observer, Event, InputHandler, EvenOdd, EvenEvent, OddEvent}
import model.Color as myColor
import model.Filling

// Bibliotheksimports
import scala.swing.*
import java.awt.Color as jColor
import javax.swing.{BorderFactory, BoxLayout, JOptionPane}
import play.api.libs.json.Json
import scala.io.Source



// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class GUISpielfeld(controller: ControllerInterface, buttons:ButtonMap) extends Observer {
  controller.add(this)

  // ------------------------------------------------------------------- VARIABLEN 
  private val pitchBackground = jColor(controller.pitch.myColor.background.getRGB) // speichern der Farbe des Blocks
  private val titel = createTitle(controller.pitch.col_num)
  private val matrix = createMatrixFromButtonMap(controller.pitch.row_num, controller.pitch.col_num)
  private val punkte = createPoints(controller.pitch.col_num)

  val spielfeld = setUpPitch(titel, matrix, punkte)

  var number = ""
  var crossesSet = 0
  var color = ""
  var summe = 0

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

  // Spielfeld aktualisieren
  def updatePitch() = {
    buttons.updateButtonMap()
  }

  // Spielfeld zusammensetzen
  def setUpPitch(titel:GridPanel, feld:GridPanel, punkte:GridPanel): BoxPanel = {
    new BoxPanel(Orientation.Vertical) {
      contents += titel
      contents += feld
      contents += punkte
    }
  }

  // Titelzeile erzeugen
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

  // Button ankreuzen
  def handleClick(b:Button) = {
    InputHandler.handle(b.name, controller)
    b.text = Filling.filled.toString()
    b.enabled = false
    crossesSet = crossesSet + 1
  }

  // Punktezeile erzeugen
  def createPoints(col:Int):GridPanel = {
    val cellWidth = 3
    new GridPanel(1, col) {
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, pitchBackground)
      val pointStringArray = if (col%2 == 0) { // für gerade Spaltenanzahl: 
                                EvenOdd.handle(EvenEvent())(cellWidth, col).toCharArray()
                              } else { // für ungerade Spaltenanzahl:
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

  // Matrix aus ButtonMap:
  def createMatrixFromButtonMap(rowNum:Int, colNum:Int): GridPanel = {
    val b = buttons.buttonMap
    println("Elemente in buttons: " + b.size)
    new GridPanel(rowNum, colNum) {
      border = BorderFactory.createMatteBorder(0, 20, 10, 20, pitchBackground)
      for (y <- 0 to rowNum-1) {
        for (x <- 0 to colNum-1) {
          println("y: " + y + " x: " + x)
          contents += b((y, x))
        }
      }
    }
  }
}