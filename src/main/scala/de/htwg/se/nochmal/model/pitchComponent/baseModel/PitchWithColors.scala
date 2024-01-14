/**
  * PitchWithColors.scala
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package model
package pitchComponent
package baseModel

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
case class PitchWithColors(colorsList:List[List[Color]], background:Color = Color.black) extends PitchWithColorsInterface {
  def getStrIndex(x:Int, y:Int):String = {
    colorsList(x)(y).toString()
  }

  def getIntIndex(x:Int, y:Int):Int = {
    colorsList(x)(y).getRGB
  }
  
  def getLine(x:Int):List[Color] = {
    colorsList(x)
  }

}

// -----------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------- VARIABLEN SAMMLUNG
// ------------------------ vereinfachte Namen für die Farben
private val r = Color.red
private val y = Color.yellow
private val o = Color.orange
private val g = Color.green
private val b = Color.blue

// ---------------------------------------------------- Farbliste schwarzes Spielfeld
val blackColorsList = List(List(g, g, g, y, y, y, y, g, b, b, b, o, y, y, y), 
                          List(o, g, y, g, y, y, o, o, r, b, b, o, o, g, g),
                          List(b, g, r, g, g, g, g, r, r, r, y, y, o, g, g),
                          List(b, r, r, g, o, o, b, b, g, g, y, y, o, r, b),
                          List(r, o, o, o, o, r, b, b, o, o, o, r, r, r, r),
                          List(r, b, b, r, r, r, r, y, y, o, r, b, b, b, o),
                          List(y, y, b, b, b, b, r, y, y, y, g, g, g, o, o))

// ---------------------------------------------------- Farbliste orangenes Spielfeld
val orangeColorsList = List(List(o, o, y, o, r, r, r, g, o, r, g, g, g, g, g),
                            List(b, o, o, o, g, r, g, g, y, r, r, g, b, y, y),
                            List(y, b, b, g, g, r, g, y, y, y, r, r, b, y, o),
                            List(y, y, b, y, y, y, y, b, b, y, y, r, o, o, o),
                            List(r, y, g, b, b, g, y, o, b, b, b, b, o, b, b),
                            List(g, g, g, r, b, b, b, o, g, g, o, y, y, b, b),
                            List(g, r, r, r, o, o, o, r, r, o, o, o, r, r, r))

// ---------------------------------------------------- Farbliste blaues Spielfeld
val blueColorsList = List(List(r, r, g, g, y, y, y, g, g, r, r, r, r, o, o),
                        List(o, r, r, b, b, g, y, g, g, r, g, y, r, o, o),
                        List(b, o, o, b, g, g, g, r, o, g, g, g, g, g, y),
                        List(b, b, o, o, o, g, r, r, o, o, o, o, b, b, y),
                        List(g, b, b, r, r, r, b, b, b, b, o, b, y, y, g),
                        List(g, g, y, y, r, b, b, y, y, y, b, b, o, y, b),
                        List(y, y, y, y, r, o, o, o, r, y, y, b, o, r, r))

// ---------------------------------------------------- Farbliste gelbes Spielfeld
val yellowColorsList = List(List(r, g, g, y, y, y, y, o, r, r, g, g, g, b, y),
                            List(r, g, r, r, r, y, y, o, r, r, g, g, b, b, b),
                            List(b, r, r, b, b, g, g, g, g, g, r, b, o, b, r),
                            List(o, o, o, o, b, b, b, b, g, y, y, b, r, r, r),
                            List(y, b, b, o, o, r, r, r, b, b, b, r, r, o, o),
                            List(y, y, b, g, g, o, o, y, y, o, b, y, y, y, o),
                            List(g, y, y, g, g, o, o, y, o, o, o, o, y, g, g))