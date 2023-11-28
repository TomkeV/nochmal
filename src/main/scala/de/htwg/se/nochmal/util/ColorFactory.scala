package de.htwg.se.nochmal
package util

trait ColorFactory {
    def color(): String
    //def colorHash(): String
}

class RedField() extends ColorFactory {
    override def color(): String = "r"
    //override def colorHash(): String = "FFFFFF"
}

class OrangeField() extends ColorFactory {
    override def color(): String = "o"
    //override def colorHash(): String = "FFFFFF"
}

class YellowField() extends ColorFactory {
    override def color(): String = "y"
    //override def colorHash(): String = "FFFFF"
}

class GreenField() extends ColorFactory {
    override def color(): String = "g" 
    //override def colorHash(): String = "FFFFFF"
}

class BlueField() extends ColorFactory {
    override def color(): String = "b" 
    //override def colorHash(): String = "FFFFF"
}