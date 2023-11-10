package de.htwg.se.nochmal
package Bastelkiste

import model.Colors_dice
import model.Numbers_dice

import scala.io.StdIn.readLine

@main def nochmal: Unit =
  val my_pitch = start_game()
  println(my_pitch.pitchToString())
  val dice_colors = Colors_dice()
  val dice_numbers = Numbers_dice()
  println(dice_numbers.roll_dice() + dice_colors.roll_dice())


def start_game(): pitch =
  println("Hallo! Wie viele Zeilen soll dein Spielfeld haben? Standard: 4")
  val numOfRows = readLine().toInt
  println("Und wie viele Spalten? Standard: 7")
  val numOfCols = readLine().toInt
  val createdPitch = pitch(numOfRows, numOfCols, 3)
  return createdPitch

