package de.htwg.se.nochmal
package aview

import scala.swing._

import controller.Controller
import util.Observer
import util.Event

class Gui(controller: Controller) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = ???

  val firstFrame = {
    title = "Nochmal!"
    menuBar = new MenuBar {
      contents += new Menu("File") {
        contents += new MenuItem(Action("Exit") {
          sys.exit(0)
        })
      }
    }
  }
  
  pack()
  centerOnScreen()
  open()

}