package de.htwg.se.nochmal
package model

// --- Enum Filling ---
enum Color(s:String, rgb:Int):
  override def toString(): String = s
  def getRGB: Int = rgb
  // rgb = r * 65536 + g * 256 + b
  case red extends Color("r", 255*65536+0*256+0)
  case orange extends Color("o", 255*65536 + 140 * 256 + 0)
  case yellow extends Color("y", 255*65536+255*256+0)
  case green extends Color("g", 0*65536+255*256+0)
  case blue extends Color("b", 0*65536+0*256+255)

//case class PitchWithColors(matrix:List[List[Color]])

object myPitchWithColors {
  private val r = Color.red
  private val y = Color.yellow
  private val or = Color.orange
  private val gr = Color.green
  private val b = Color.blue
        
  private val firstRowColors = List(gr, gr, gr, y, y, y, y, gr, b, b, b, or, y, y, y)
  private val secondRowColors = List(or, gr, y, gr, y, y, or, or, r, b, b, or, or, gr, gr)
  private val thirdRowColors = List(b, gr, r, gr, gr, gr, gr, r, r, r, y, y, or, gr, gr)
  private val fourthRowColors = List(b, r, r, gr, or, or, b, b, gr, gr, y, y, or, r, b)
  private val fifthRowColors = List(r, or, or, or, or, r, b, b, or, or, or, r, r, r, r)
  private val sixthRowColors = List(r, b, b, r, r, r, r, y, y, or, r, b, b, b, or)
  private val seventhRowColors = List(y, y, b, b, b, b, r, y, y, y, gr, gr, gr, or, or)

  val colorsMatrix = List(firstRowColors, secondRowColors, thirdRowColors, 
                      fourthRowColors, fifthRowColors, sixthRowColors, seventhRowColors)

  def getStrIndex(x:Int, y:Int):String = {
    colorsMatrix(x)(y).toString()
  }

  def getIntIndex(x:Int, y:Int):Int = {
    colorsMatrix(x)(y).getRGB
  }
  
  def getLine(x:Int):List[Color] = {
    colorsMatrix(x)
  }
}