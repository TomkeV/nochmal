/**
  * Event.scala
  * Kind of a list of things which may happen 
  * ingame.
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------- ENUM DEFINITION
enum Event:
    case Quit
    case Diced
    case Crossed
    case Undone
    case Redone
    case Applied