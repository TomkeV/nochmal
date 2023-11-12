package de.htwg.se.nochmal
package model

// --- Enum Filling ---
enum Filling(s:String):
  override def toString(): String = s
  case filled extends Filling("X")
  case empty extends Filling(" ")