/**
  * Filling.scala
  * enum to save different Fillings for cells in a matrix
  * @author Tomke Velten
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE 
package de.htwg.se.nochmal
package model

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------- ENUM DEFINITION
enum Filling(s:String):
  override def toString(): String = s
  case filled extends Filling("X")
  case empty extends Filling(" ")