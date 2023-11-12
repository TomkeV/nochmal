package de.htwg.se.nochmal
package util

trait Observer:
    def update(e: Event): Unit