/* package de.htwg.se.nochmal

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._


class NochmalSpec extends AnyWordSpec {
  "Nochmal" should {
    "have a bar as String of Form '+---+---+---+---+---+---+---+' " in {
      columns() should be("+---+---+---+---+---+---+---+" + eol)
    }

    "have a scalable bar" in {
      columns(1, 1) should be("+-+" + eol)
      columns(1, 2) should be("+-+-+" + eol)
      columns(2, 1) should be("+--+" + eol)
    }

    "have cells as String of form '|   |   |   |   |   |   |   |' " in {
      lines() should be("|   |   |   |   |   |   |   |" + eol)
    }
    
    "have scalable cells" in {
      lines(1, 1) should be("| |" + eol)
      lines(1, 2) should be("| | |" + eol)
      lines(2, 1) should be("|  |" + eol)
    }

    "have a pitch in the form " + 
      " A " +
      "+-+" +
      "| |" + 
      "+-+" + 
      " 5 " +
      " 3" in {
        pitch(1, 1, 1) should be(" A " + eol + "+-+" + eol + "| |" + eol + "+-+" + eol + " 5 " + eol + " 3 " + eol)
      }
  }
} */