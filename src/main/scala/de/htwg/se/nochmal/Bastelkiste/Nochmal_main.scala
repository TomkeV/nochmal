package de.htwg.se.nochmal
package Bastelkiste

import scala.io.StdIn.readLine

@main def nochmal: Unit =
  val my_pitch = start_game()
  println(my_pitch.pitchToString())
  roll_dice()


def start_game(): pitch =
  println("Hallo! Wie viele Zeilen soll dein Spielfeld haben? Standard: 4")
  val numOfRows = readLine().toInt
  println("Und wie viele Spalten? Standard: 7")
  val numOfCols = readLine().toInt
  val createdPitch = pitch(numOfRows, numOfCols, 3)
  return createdPitch


def roll_dice(anzahl:Int = 6) =
  if (anzahl < 2) {
    println("Du brauchst mindestens 2 Wuerfel!")
  }
  if (anzahl >= 2) {
    val randomizer = new scala.util.Random
    val n = (1 to (anzahl/2)).toList
    val diced_ints = n.map(i => randomizer.nextInt(6)+1)
    val diced_colors = n.map(i => randomizer.nextInt(6))
    //println(diced_ints)
    //println(diced_colors)
    print_dice(diced_ints, diced_colors)
  }

def print_dice(numList:List[Int], colorList:List[Int]) = 
  println("Deine Wuerfelergebnisse:")
  for (x <- numList) {
    if (x == 6) {
      println("?")
    }
    if (x < 6) {
      println(x)
    }
  }

  for (y <- colorList) {
    y match {
      case 0 => println("rot")
      case 1 => println("orange")
      case 2 => println("gelb")
      case 3 => println("gruen")
      case 4 => println("blau")
      case 5 => println("Joker!")
    }
  }