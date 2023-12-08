package de.htwg.se.nochmal
package aview

import scala.swing._

import controller.Controller
import util.Observer
import util.Event
import model.Color
import java.awt.Color

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

    }
    
    def createPitch(rowNum:Int, colNum:Int): GridPanel = {
      val pitch = new GridPanel(rowNum, 1) {
        for (i <- 0 to rowNum-1) {
          contents += createRow(colNum)
        }
      }
      return pitch
    }

    def createRow(col:Int):GridPanel = {
      val myPanel = new GridPanel(1, col) {
        for (i <- 0 to col-1)  {
          contents += new Button(" ")
        }
      }
      return myPanel
    }

    def createButton(fill:String, c:model.Color) = {
      val myButton = new Button(fill) {
        background = java.awt.Color(c.getRGB)
      }
    }


  }
  
  pack()
  centerOnScreen()
  open()

}