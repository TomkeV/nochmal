package de.htwg.se.nochmal
package aview

import scala.swing._

import controller.Controller
import util.Observer
import util.Event

class GUI(controller: Controller) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case _ => 
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
      val pitch = new GridPanel(rows, cols) {
        title = "test"
      }
/*       for(i <- 0 to rows) {
        for (j <- 0 to cols) {

        }
      } */
      pitch.visible = true
      contents += pitch
    }
  }
  
  pack()
  centerOnScreen()
  open()

}