package de.htwg.se.nochmal.util

import de.htwg.se.nochmal.model.pitchComponent.PitchInterface

trait FileIOInterface {
  def load(pitch: PitchInterface): PitchInterface
  def save(pitch: PitchInterface): Unit
}