package de.htwg.se.nochmal
package model

// --- Enum Filling ---
enum Color(s:String):
  override def toString(): String = s
  case red extends Color("r")
  case orange extends Color("o")
  case yellow extends Color("y")
  case green extends Color("g")
  case blue extends Color("b")