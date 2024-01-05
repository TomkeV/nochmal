/**
  * Observer.scala
  * Observer Pattern
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package util

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITION
trait Observer:
    def update(e: Event): Unit

// -----------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------- INTERFACE DEFINITINO
trait Observable { // trait entspricht interface
    var subscribers: Vector[Observer] = Vector() // Liste der Observer (anfangs leer)
    def add(s: Observer) = subscribers = subscribers :+ s
    def remove(s: Observer) = subscribers = subscribers.filterNot(o => o == s)
    // filterNot: prueft fuer jedes Element in der Liste, ob es s ist und schmeißt es raus wenn ja 
    def notifyObservers(e: Event) = subscribers.foreach(o => o.update(e)) 
}