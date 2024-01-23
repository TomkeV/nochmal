// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util
package ioComponent
package io

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne imports
import model.pitchComponent.baseModel.PitchAsMatrix
import model.pitchComponent.PitchInterface

// Bibliotheksimports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class XMLIOSpec extends AnyWordSpec {
  val pitch = new PitchAsMatrix(7, 2)
  val io = XMLIO()
  "The XML File-IO " should {
    "be able to load from an xml-file " in {
      io.load(pitch) shouldBe a [PitchInterface]
    }
    "be able to save the recent gamestate to an xml-file " in {
      io.save(pitch) should be (true)
    }
  }
}