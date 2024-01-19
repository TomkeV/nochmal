/**
  * Color.scala
  * enum to save different colors 
  * with their string representation and an int-code for rgb-representation.
  * @author Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------- ENUM DEFINITION
enum Color(s:String, rgb:Int) {
  override def toString(): String = s
  def getRGB: Int = rgb
  case red extends Color("r", 220*65536 + 26 * 256 + 52) 
  case orange extends Color("o", 255*65536 + 140 * 256 + 0)
  case yellow extends Color("y", 253*65536 + 219*256 + 0) 
  case green extends Color("g", 85*65536 + 203*256 + 7) 
  case blue extends Color("b", 0*65536 + 171*256 + 255)
  case black extends Color("black", 0)
}