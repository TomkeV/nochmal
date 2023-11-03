package de.htwg.se.nochmal
package Bastelkiste

import scala.io.StdIn.readLine

@main def nochmal: Unit =
  println("Willkommen bei Nochmal!")
  val my_pitch = start_game()
  println(my_pitch.pitchToString())


def start_game(): pitch =
  println("Hallo! Wie viele Zeilen soll dein Spielfeld haben? Standard: 4")
  val numOfRows = readLine().toInt
  println("Und wie viele Spalten? Standard: 7")
  val numOfCols = readLine().toInt
  val createdPitch = pitch(numOfRows, numOfCols, 3)
  return createdPitch