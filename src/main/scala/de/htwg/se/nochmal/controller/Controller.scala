package de.htwg.se.nochmal
package controller

import aview.pitch
import util.Observable

case class Controller(var my_pitch:pitch) extends Observable:
    // kreuze ein Feld an
    // und rufe notifyObservers auf