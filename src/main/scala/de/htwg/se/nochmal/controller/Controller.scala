package de.htwg.se.nochmal
package controller

import util.Observable
import model.PitchAsMatrix
import model.Filling


case class Controller(var my_pitch:PitchAsMatrix) extends Observable:
    //override def toString(): String = myPitch.toString
    // kreuze ein Feld an
    // und rufe notifyObservers auf