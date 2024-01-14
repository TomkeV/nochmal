/** Nochmal_main.scala
  * Hauptklasse zum Starten des Programms.
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import aview.TUI
import aview.myGUI

import controller.controllerComponent.ControllerInterface
import Default.ControllerInterface
//import OrangePitch.ControllerInterface
//import BluePitch.ControllerInterface
//import YellowPitch.ControllerInterface


// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ MAIN
@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myController = summon[ControllerInterface]

  val myGui = myGUI(myController)
  val myTui = TUI(myController)

  myTui.run