/**
  * DiceInterface.scala
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package diceComponent

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITION
trait DiceInterface:
    val num_of_dices:Int
    def roll_dice():String