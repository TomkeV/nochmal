package de.htwg.se.nochmal
package aview

import de.htwg.se.nochmal.controller.Controller
import de.htwg.se.nochmal.util.Observer
import de.htwg.se.nochmal.util.Event

class TUI(controller: Controller) extends Observer:
    controller.add(this) // fügt TUI in die Liste hinzu, sodass update funktioniert

    override def update(e: Event): Unit = ??? // kommt noch

