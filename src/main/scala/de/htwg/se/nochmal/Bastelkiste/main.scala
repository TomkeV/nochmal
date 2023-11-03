package de.htwg.se.nochmal
package Bastelkiste

import scala.io.StdIn.readLine

@main def nochmal: Unit =
  println("Willkommen bei Nochmal!")
  val pitch = start_game()
  println(pitch.pitchToString())


def start_game(): meinFeld =
  println("Hallo! Wie viele Zeilen soll dein Spielfeld haben? Standard: 4")
  val zeilen = readLine().toInt
  println("Und wie viele Spalten? Standard: 7")
  val spalten = readLine().toInt
  val spielfeld = meinFeld(zeilen, spalten, 3)
  return spielfeld