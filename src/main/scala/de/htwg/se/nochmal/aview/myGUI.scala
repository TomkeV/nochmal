/**
  * myGUI.scala
  * Class for graphic-based user-interface of the game "Nochmal!"
  * Link to the game: https://www.schmidtspiele.de/details/produkt/noch-mal-.html
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// Bibliotheksimports:
import scala.swing._ 
import javax.swing.BorderFactory
import java.awt.Color as jColor

// interne imports:
import controller.controllerComponent.ControllerInterface
//import controller.controllerBaseImpl.Controller
import controller.controllerComponent.controllerBaseImpl.diceResult
import controller.controllerComponent.controllerBaseImpl.rounds

import util.Observer
import util.Event
import util.InputHandler
import util.EvenOdd
import util.EvenEvent
import util.OddEvent

import model.pitchComponent.baseModel.PitchWithColors
import model.pitchComponent.baseModel.blackColorsList
import model.Color as myColor
import model.Filling

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class myGUI(controller: ControllerInterface) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => //redoButton.enabled = false
      case Event.Crossed => //redoButton.enabled = true
      case Event.Applied => crossesSet = 0
                            //redoButton.enabled = false
      case Event.Undone => 
      case Event.Redone => 
    }

  val num_of_rounds = controller.pitch.col_num * 2 // speichern der maximalen Rundenzahl
  val rows = controller.pitch.row_num 
  val cols = controller.pitch.col_num
  val pitchBackground = jColor(controller.pitch.myColor.background.getRGB) // speichern der Farbe des Blocks

  var number = ""
  var color = ""
  var crossesSet = 0
  var summe = 0


/*   val redoButton = new Button("Redo") {
    enabled = false
    reactions += {
      case event.ButtonClicked(_) =>
              InputHandler.handle("r", controller)
    }
  } */

  // Anlegen des Hauptrahmens
  val myFrame = {
    preferredSize = new Dimension(800, 600)

    title = "Nochmal"

    contents = new BoxPanel(Orientation.Vertical) {
      border = BorderFactory.createMatteBorder(20, 20, 20, 20, jColor.darkGray)
      
      // Titelzeile mit Buchstaben hinzufügen
      contents += createTitle(cols)
      // Matrix des Spielfelds hinzufügen
      contents += createPitch(rows, cols)
      // Zeile mit Punkten pro Spalte hinzufügen
      contents += createPoints(cols)


      // Anlegen der 6 Würfel
      val die1 = new Button() { 
        name = "die1"
        text = "" 
        preferredSize = new Dimension(40, 20)
        border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
        enabled = false
        reactions += {
          case event.ButtonClicked(_) =>
            number = dieChosen(this)
        }
      }

      val die2 = new Button() { 
        name = "die2"
        text = "" 
        preferredSize = new Dimension(40, 20)
        border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
        enabled = false
        reactions += {
          case event.ButtonClicked(_) =>
            number = dieChosen(this)
        }
      }

      val die3 = new Button() { 
        name = "die3"
        text = ""
        preferredSize = new Dimension(40, 20)
        border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
        enabled = false
        reactions += {
          case event.ButtonClicked(_) =>
            number = dieChosen(this)
        }
      }

      val die4 = new Button() { 
        name = "die4"
        text = "" 
        preferredSize = new Dimension(40, 20)
        border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
        enabled = false
        reactions += {
          case event.ButtonClicked(_) =>
            color = dieChosen(this)
        }
      }

      val die5 = new Button() { 
        name = "die5"
        text = ""
        preferredSize = new Dimension(40, 20)
        border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
        enabled = false
        reactions += {
          case event.ButtonClicked(_) =>
            color = dieChosen(this)
        }
      }

      val die6 = new Button() { 
        name = "die6"
        text = ""
        preferredSize = new Dimension(40, 20)
        border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
        enabled = false
        reactions += {
          case event.ButtonClicked(_) =>
            color = dieChosen(this)
        }
      }

      // -------------- Hilfsmethode Würfel auswählen ---------------
      def dieChosen(d:Button) : String = {
        if d.name == "die1" then 
          die2.enabled = false
          die3.enabled = false
          die1.text
        else if d.name == "die2" then
          die1.enabled = false
          die3.enabled = false
          die2.text
        else if d.name == "die3" then
          die1.enabled = false
          die2.enabled = false
          die3.text
        else if d.name == "die4" then
          die5.enabled = false
          die6.enabled = false
          die4.text match 
            case "rot" => myColor.red.getRGB.toString
            case "orange" => myColor.orange.getRGB.toString
            case "grün" => myColor.green.getRGB.toString
            case "gelb" => myColor.yellow.getRGB.toString
            case "blau" => myColor.blue.getRGB.toString
            case _ => "Joker"
        else if d.name == "die5" then
          die4.enabled = false
          die6.enabled = false
          die5.text match 
            case "rot" => myColor.red.getRGB.toString
            case "orange" => myColor.orange.getRGB.toString
            case "grün" => myColor.green.getRGB.toString
            case "gelb" => myColor.yellow.getRGB.toString
            case "blau" => myColor.blue.getRGB.toString
            case _ => "Joker"
        else 
          die4.enabled = false
          die5.enabled = false
          die6.text match 
            case "rot" => myColor.red.getRGB.toString
            case "orange" => myColor.orange.getRGB.toString
            case "grün" => myColor.green.getRGB.toString
            case "gelb" => myColor.yellow.getRGB.toString
            case "blau" => myColor.blue.getRGB.toString
            case _ => "Joker"
      }
      // -------------- Hilfsmethode Farbgebung -----------------
      def setBackground(d:Button) = {
        val diceColor = d.text match
          case "rot" => jColor(myColor.red.getRGB)
          case "orange" => jColor(myColor.orange.getRGB)
          case "gelb" => jColor(myColor.yellow.getRGB)
          case "gruen" => jColor(myColor.green.getRGB)
          case "blau" => jColor(myColor.blue.getRGB)
          case "Joker!" => jColor.BLACK 
          case _ => jColor.white
        
        d.background = diceColor        
      }

      // Button zum Würfeln zentriert hinzufügen
      contents += new GridPanel(1,5) {
        border = BorderFactory.createMatteBorder(10, 10, 10, 10, jColor.darkGray)
        background = jColor.darkGray
        contents += new Label() 
        contents += new Label() 
        contents += new Button("wuerfeln") {
            preferredSize = new Dimension(50, 30)
            reactions += {
              case event.ButtonClicked(_) =>
                InputHandler.handle("w", controller)

                val dicedArray = diceResult.split("""\R""") //dicedValues.split("""\R""")
                die1.text = dicedArray(1)
                die1.enabled = true
                die2.text = dicedArray(2)
                die2.enabled = true
                die3.text = dicedArray(3)
                die3.enabled = true
                die4.text = dicedArray(4)
                die4.enabled = true
                setBackground(die4)
                die5.text = dicedArray(5)
                die5.enabled = true
                setBackground(die5)
                die6.text = dicedArray(6)
                die6.enabled = true
                setBackground(die6)
            }
          }
        contents += new Label()
        contents += new Label() 
      } 

      // Panel zur Darstellung der 6 Würfel
      contents += new GridPanel(1, 6) {
        preferredSize = new Dimension(800, 40)
        //border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
        contents += die1
        contents += die2
        contents += die3 
        contents += die4 
        contents += die5
        contents += die6
      }




      // Button Apply zentriert hinzufügen
      contents += new GridPanel(1, 5) {
        background = jColor.darkGray
        border = BorderFactory.createMatteBorder(10, 10, 10, 10, jColor.darkGray)
        contents += new Label()
        val applyButton = new Button("Bestaetigen") {
          //enabled = false
          preferredSize = new Dimension(50, 30)
          reactions += {
            case event.ButtonClicked(_) =>
              InputHandler.handle("a", controller)
              roundLabel.text = "Runde " + rounds + " von " + num_of_rounds
              sumLabel.text = "Summe: " + summe
              gameStatePanel.repaint()
          }
        }
        contents += applyButton
        //contents += new Label()
        //contents += redoButton
        contents += new Label()
      }  

      // Rundenzähler hinzufügen
      val sumLabel = new Label("Summe: " + summe)
      val roundLabel = new Label("Runde " + rounds + " von " + num_of_rounds)
      val gameStatePanel = new GridPanel(1,2) {
        background = jColor.WHITE
        contents += roundLabel
        contents += sumLabel
      }
      contents += gameStatePanel
      
    }

  }

  pack()
  centerOnScreen()
  open()

// Allerlei wichtige Methoden

  // Titelzeile erzeugen
  def createTitle(col:Int):GridPanel = {
    val myTitle = new GridPanel(1, col) {
      //background = jColor.WHITE
      border = BorderFactory.createMatteBorder(10, 20, 10, 20, pitchBackground) //jColor.BLACK
      for (i <- 0 to col-1) {
        contents += new Label((('A' + i).toChar).toString()) {
          background = jColor.WHITE
          foreground = jColor.BLACK
        }
      }
    }
    return myTitle
  }

  // Feld mit x Zeilen mit Buttons erzeugen
  def createPitch(rowNum:Int, colNum:Int): GridPanel = {
    val myColors = controller.pitch.myColor
    val pitch = new GridPanel(rowNum, 1) {
      border = BorderFactory.createMatteBorder(0, 20, 10, 20, pitchBackground) //jColor.BLACK

      for (i <- 0 to rowNum-1) {
        contents += createRow(colNum, i+1, myColors.getLine(i))
      }
    }
    return pitch
  }

  // Automatisiertes Erzeugen von Zeilen mit bunten Feldern
  def createRow(cols:Int, num:Int, colors:List[myColor]):GridPanel = {
    val myPanel = new GridPanel(1, cols) {
      for (i <- 0 to cols-1) {
        val colChar = ('A' + i).toChar
        val cross = "x" + colChar.toString() + num.toString()
        //val cross = "x" + num.toString() + (i+1).toString()
        contents += createButton(colors(i), cross)
      }
    }
    return myPanel
  }

  // Automatisiertes Erzeugen von bunten, ankreuzbaren Feldern
  def createButton(c:myColor, n:String): Button = {
    val s = 30 // Größe des Buttons
    val myButton = new Button() {
      val thisColor = c.getRGB
        preferredSize = new Dimension(s, s)
        background = jColor(thisColor)
        name = n
        reactions += {
          case event.ButtonClicked(_) =>
            if (number != "!") 
              && (crossesSet < number.toInt) 
              && (thisColor.toString == color) then
                InputHandler.handle(name, controller)
                text = Filling.filled.toString()
                enabled = false
                crossesSet = crossesSet + 1
                //redoButton.enabled = true
            else if (number != "!") 
              && (crossesSet < number.toInt) 
              && (color == "Joker") then
                InputHandler.handle(name, controller)
                text = Filling.filled.toString()
                enabled = false
                crossesSet = crossesSet + 1
                //redoButton.enabled = true
            else if (number == "!")
              && (crossesSet < 5)
              && (thisColor.toString == color) then
                InputHandler.handle(name, controller)
                text = Filling.filled.toString()
                enabled = false
                crossesSet = crossesSet + 1
                //redoButton.enabled = true
            else if (number == "!")
              && (crossesSet < 5)
              && (color == "Joker") then
                InputHandler.handle(name, controller)
                text = Filling.filled.toString()
                enabled = false
                crossesSet = crossesSet + 1
                //redoButton.enabled = true
        }
      }
    return myButton
  }

  // Punktezeile erzeugen
  def createPoints(col:Int):GridPanel = {
    val cellWidth = 3
    val myPoints = new GridPanel(1, col) {
      //background = jColor.BLACK
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, pitchBackground) //jColor.BLACK
      val pointStringArray = if (col%2 == 0) { // für gerade Spaltenanzahl: 
                                EvenOdd.handle(EvenEvent())(cellWidth, col).toCharArray()
                              } else { // für ungerade Spaltenanzahl:
                                EvenOdd.handle(OddEvent())(cellWidth, col).toCharArray()
                              }
      for (i <- 0 to pointStringArray.length-1) {
        if (pointStringArray(i).isDigit) {
          contents += new Button(pointStringArray(i).toString()) {
            name = pointStringArray(i).toString()
            foreground = jColor.BLACK
            background = jColor.WHITE
            border = BorderFactory.createMatteBorder(0, 10, 0, 10, pitchBackground)
            reactions += {
              case event.ButtonClicked(_) =>
                summe += name.toInt
                this.enabled = false
            }
          }
        }
      }
    }
    return myPoints
  }
}


// ToDos: 
// Menüleiste
    // Option zum Beenden (Event.Quit)
    // Spielanleitung
