package de.htwg.se.nochmal
package model
package pitchComponent

trait PitchWithColorsInterface:
  val colorsList: List[List[Color]]
  def getStrIndex(x:Int, y:Int): String
  def getIntIndex(x:Int, y:Int): Int 
  def getLine(x:Int): List[Color]