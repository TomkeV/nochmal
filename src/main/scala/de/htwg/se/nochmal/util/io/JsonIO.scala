// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util
package io

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import de.htwg.se.nochmal.util.FileIOInterface
import de.htwg.se.nochmal.model.pitchComponent.PitchInterface
import scala.io.Source


// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class JsonIO extends FileIOInterface {

  override def load(pitch: PitchInterface): PitchInterface = pitch.loadFromJson()

  override def save(pitch: PitchInterface): Unit = pitch.saveToJson()
}