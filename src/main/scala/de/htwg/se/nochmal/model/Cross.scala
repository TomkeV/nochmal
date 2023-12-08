package de.htwg.se.nochmal
package model

case class Cross(x:Int, y:Int)

case class Move(crosses:List[Option[Cross]])