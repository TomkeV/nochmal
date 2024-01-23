// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package controller

// interne Imports
import de.htwg.se.nochmal.model.Cross
import de.htwg.se.nochmal.model.Color
// Bibliotheksimports
import scala.collection.mutable.ArrayBuffer

// -----------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------- VARIABLEN-SAMMLUNG
var diceResult = ""
var rounds = 0
var moveDone = false
var crossArray = ArrayBuffer[Cross]()
var summe = 0