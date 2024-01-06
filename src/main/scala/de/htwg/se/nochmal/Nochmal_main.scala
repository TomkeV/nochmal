package de.htwg.se.nochmal


import scala.io.StdIn.readLine

//import model.dice.DiceInterface
//import model.diceComponent.diceImplementierung.Colors_dice
//import model.diceComponent.diceImplementierung.Numbers_dice
//import model.pitchComponent.baseModel.PitchAsMatrix
//import controller.controllerComponent.controllerBaseImpl.Controller

import aview.TUI
import aview.myGUI

import com.google.inject.Injector
import com.google.inject.Guice
import de.htwg.se.nochmal.controller.controllerComponent.ControllerInterface

// imports für Scala-DI (Branch Dev_10-DI)
// import Default.ControllerInterface
// import Orange.ControllerInterface
// import de.htwg.se.nochmal.controller.controllerComponent.ControllerInterface




@main def nochmal: Unit =
  println("Herzlich Willkommen zu Nochmal!")

  // Inhalte für Guice:
  val injector: Injector = Guice.createInjector(new Modules())
  val myController = injector.getInstance(classOf[ControllerInterface])

  //val myController = summon[ControllerInterface]

  val myGui = myGUI(myController)

  val myTui = TUI(myController)

  myTui.run
