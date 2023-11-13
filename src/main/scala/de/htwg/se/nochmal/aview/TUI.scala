package de.htwg.se.nochmal
package aview

import controller.Controller
import util.Observer
import util.Event

class TUI(controller: Controller) extends Observer:
  controller.add(this) // fügt TUI in die Liste hinzu, sodass update funktioniert

  //def run =
    //println(myPitch.toString)

  override def update(e: Event): Unit = ??? // kommt noch

