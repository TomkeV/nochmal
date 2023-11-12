package de.htwg.se.nochmal
package util

trait Observer: // Trait ist Interface in Scala
    def update(e: Event): Unit  // Methode update tut nichts