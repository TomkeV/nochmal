/** Nochmal_main.scala
  * Hauptklasse zum Starten des Programms.
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheksimports
import scala.io.StdIn.readLine

// interne imports
import aview.TUI
import aview.myGUI
import Default.ControllerInterface
//import OrangePitch.ControllerInterface
//import BluePitch.ControllerInterface
//import YellowPitch.ControllerInterface
import de.htwg.se.nochmal.controller.controllerComponent.ControllerInterface

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ MAIN
@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  val myController = summon[ControllerInterface]

  val myGui = myGUI(myController)
  val myTui = TUI(myController)

  myTui.run