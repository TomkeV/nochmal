/**
  * PitchWithColorsInterface.scala
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package pitchComponent

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITION
trait PitchWithColorsInterface {
  val background: Color
  val colorsList: List[List[Color]]
  
  def getStrIndex(x:Int, y:Int): String
  def getIntIndex(x:Int, y:Int): Int 
  def getLine(x:Int): List[Color]
  def getColor():String
}