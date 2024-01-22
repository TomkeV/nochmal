// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util
package ioComponent
package io

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
import model.pitchComponent.PitchInterface

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class XMLIO extends FileIOInterface {
    override def load(pitch: PitchInterface): PitchInterface = pitch.loadFromXML()

    override def save(pitch: PitchInterface): Boolean = pitch.saveToXML()
}