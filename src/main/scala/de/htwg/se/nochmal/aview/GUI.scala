package de.htwg.se.nochmal
package aview

import scala.swing._

import controller.Controller
import util.Observer
import util.Event
import model.Color
import java.awt.Color
import model.Filling

class GUI(controller: Controller) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => 
      case Event.Crossed =>
      case Event.Undone =>
      case Event.Redone =>
    }

  val firstFrame = {
    title = "Nochmal!"
    contents = new BoxPanel(Orientation.Vertical) {
      val start_button = new Button("neues Spiel")
      contents += start_button
      val t = " A   B   C   D   E   F"
      val title_label = new Label(t)
      contents += title_label

      val rows = 4
      val cols = 7

      val pitch = createPitch(rows, cols)

      contents += pitch

      val wuerfelTitel = new Label("Deine Würfel: ")

      contents += wuerfelTitel

      val dice = new GridPanel(2, 3) {
        contents += new Label("1")
        contents += new Label("2")
        contents += new Label("3")
        contents += new Label("4")
        contents += new Label("5")
        contents += new Label("6")
      }
      contents += dice

      val wuerfelButton = new Button("neu wuerfeln")

      contents += wuerfelButton

    }
    
    // Feld mit x Zeilen mit Buttons erzeugen
    def createPitch(rowNum:Int, colNum:Int): GridPanel = {
      val pitch = new GridPanel(rowNum, 1) {
        for (i <- 0 to rowNum-1) {
          contents += createRow(colNum)
        }
      }
      return pitch
    }

    // Zeile mit Buttons erzeugen
    def createRow(col:Int):GridPanel = {
      val myPanel = new GridPanel(1, col) {
        for (i <- 0 to col-1)  {
          contents += createButton(Filling.empty)
        }
      }
      return myPanel
    }

    // neuen Button mit Filling MIT FARBWAHL ERGÄNZEN!
    def createButton(fill:Filling): Button = {
      val myButton = new Button(fill.toString) {
        //background = java.awt.Color(c.getRGB)
      }
      return myButton
    }
  }
  
  pack()
  centerOnScreen()
  open()
}