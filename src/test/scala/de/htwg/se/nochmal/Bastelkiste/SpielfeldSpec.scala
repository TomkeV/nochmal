package de.htwg.se.nochmal
package Bastelkiste

import Bastelkiste._

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

val eol = sys.props("line.separator")

class SpielfeldSpec extends AnyWordSpec {
  "Spielfeld" should {
    val testfeld = meinFeld()
    "have a bar as String of Form '+---+---+---+---+---+---+---+' when its created without Parameters" in {
      testfeld.columns() should be("+---+---+---+---+---+---+---+" + eol)
    }
    "have cells as String of form '|   |   |   |   |   |   |   |' when its created without Parameters" in {
      testfeld.lines() should be("|   |   |   |   |   |   |   |" + eol)
    }
    
    val mini_testfeld = meinFeld(1, 1, 1)
    "have a bar in String of Form '+-+' when its created with one column and minimal boxsize" in {
      mini_testfeld.columns() should be("+-+" + eol)
    }
    "have a cell in String of Form '| |' when its created with one line and minimal boxsize" in {
      mini_testfeld.lines() should be("| |" + eol)
    }
    "have a pitch in the form " + 
      " A " +
      "+-+" +
      "| |" + 
      "+-+" + 
      " 5 " +
      " 3"  +
      "when its created in minimum size "in {
      mini_testfeld.pitchToString() should be(" A " + eol + "+-+" + eol + "| |" + eol + "+-+" + eol + " 5 " + eol + " 3 " + eol)
    }
  }
}