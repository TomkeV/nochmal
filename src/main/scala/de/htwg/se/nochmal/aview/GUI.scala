package de.htwg.se.nochmal
package aview

import scala.swing._

import controller.Controller
import util.Observer
import util.Event
import java.awt.Color

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
        //title = "test"

        val redButton = new Button(" ") {
          background = Color(255*65536+0*256+0)
        }
        val blueButton = new Button(" ") {
          background = Color(0*65536+0*256+255)

        }
        val greenButton = new Button(" ") {
          background = Color(0*65536+255*256+0)
        }
        val orangeButton = new Button(" "){
          background = Color(255*65536 + 140 * 256 + 0)
          //(R*65536)+(G*256)+B
        }

        val yellowButton = new Button(" ") {
          background = Color(255*65536+255*256+0)
        }

        contents += redButton
        contents += blueButton
        contents += greenButton
        contents += orangeButton
        contents += yellowButton

        visible = true
      }

      contents += pitch
    }
  }
  
  pack()
  centerOnScreen()
  open()

}