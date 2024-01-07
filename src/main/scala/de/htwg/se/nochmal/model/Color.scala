/**
  * Color.scala
  * enum to save different colors 
  * with their string representation
  * and an rgb int-code.
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
  // rgb = r * 65536 + g * 256 + b
  case red extends Color("r", 220*65536 + 26 * 256 + 52) //("r", 255*65536+0*256+0)
  case orange extends Color("o", 255*65536 + 140 * 256 + 0)
  case yellow extends Color("y", 253*65536 + 219*256 + 0) //("y", 255*65536+255*256+0) //229*65536 + 206*256 + 0
  case green extends Color("g", 85*65536 + 203*256 + 7) //("g", 0*65536 + 255*256 + 0)
  case blue extends Color("b", 0*65536 + 171*256 + 255) //("b", 0*65536+0*256+255)
  case black extends Color("", 0)
}