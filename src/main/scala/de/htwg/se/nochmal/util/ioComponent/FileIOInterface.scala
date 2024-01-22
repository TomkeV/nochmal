/**
  * FileIOInterface.scala
  * @author Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util
package ioComponent

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import model.pitchComponent.PitchInterface

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITION
trait FileIOInterface {
  def load(pitch: PitchInterface): PitchInterface
  def save(pitch: PitchInterface): Boolean
}