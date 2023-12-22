// a very difficult version but it hopefully works...
package de.htwg.se.nochmal
package aview

// Bibliotheksimports:
import scala.swing._ 
import javax.swing.BorderFactory
import java.awt.Color as jColor

// eigene imports:
import controller.controllerComponent.ControllerInterface
//import controller.controllerBaseImpl.Controller
import controller.controllerComponent.controllerBaseImpl.diceResult

import util.Observer
import util.Event
import util.InputHandler
import util.EvenOdd
import util.EvenEvent
import util.OddEvent

import model.myPitchWithColors
import model.Color as myColor
import model.Filling


class myGUI(controller: ControllerInterface) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => 
                          //redoButton.enabled = false
      case Event.Crossed => //redoButton.enabled = true
      case Event.Applied => rounds += 1
                            //redoButton.enabled = false
      case Event.Undone => 
      case Event.Redone => 
    }

  var rounds = 0 // speichern der aktuellen Rundenzahl
  val num_of_rounds = controller.pitch.col_num * 2 // speichern der maximalen Rundenzahl
  val rows = controller.pitch.row_num 
  val cols = controller.pitch.col_num

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
        text = "" 
        preferredSize = new Dimension(40, 20)
      }
      val die2 = new Button() { 
        text = "" 
        preferredSize = new Dimension(40, 20)
      }
      val die3 = new Button() { 
        text = ""
        preferredSize = new Dimension(40, 20)
      }
      val die4 = new Button() { 
        text = "" 
        preferredSize = new Dimension(40, 20)
/*         background = text match 
          case "rot" => jColor(myColor.red.getRGB)
          case "orange" => jColor(myColor.orange.getRGB)
          case "gelb" => jColor(myColor.yellow.getRGB)
          case "gruen" => jColor(myColor.green.getRGB)
          case "blau" => jColor(myColor.blue.getRGB)
          case "Joker!" => jColor.BLACK 
                          //foreground = jColor.WHITE
          case _ => jColor.white
        visible = true */
      }
      val die5 = new Button() { 
        text = ""
        preferredSize = new Dimension(40, 20)
      }
      val die6 = new Button() { 
        text = ""
        preferredSize = new Dimension(40, 20)
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
                die2.text = dicedArray(2)
                die3.text = dicedArray(3)
                die4.text = dicedArray(4)
                die5.text = dicedArray(5)
                die6.text = dicedArray(6)
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
        val applyButton = new Button("Apply") {
          reactions += {case event.ButtonClicked(_) =>
            InputHandler.handle("a", controller)
            roundLabel.text = "Runde " + rounds + " von " + num_of_rounds
            roundPanel.repaint()
          }
        }
        contents += applyButton
        //contents += new Label()
        //contents += redoButton
        contents += new Label()
      }  

      // Rundenzähler hinzufügen
      val roundLabel = new Label("Runde " + rounds + " von " + num_of_rounds)
      val roundPanel = new GridPanel(1,1) {
        background = jColor.WHITE
        contents += roundLabel
      }
      contents += roundPanel
      
    }

  }

  pack()
  centerOnScreen()
  open()

// Allerlei wichtige Methoden

  // Titelzeile erzeugen
  def createTitle(col:Int):GridPanel = {
    val myTitle = new GridPanel(1, col) {
      background = jColor.BLACK
      border = BorderFactory.createMatteBorder(0, 20, 0, 20, jColor.BLACK)
      for (i <- 0 to col-1) {
        contents += new Label((('A' + i).toChar).toString()) {
          foreground = jColor.LIGHT_GRAY
        }
      }
    }
    return myTitle
  }

  // Feld mit x Zeilen mit Buttons erzeugen
  def createPitch(rowNum:Int, colNum:Int): GridPanel = {
    val pitch = new GridPanel(rowNum, 1) {
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, jColor.BLACK)

      for (i <- 0 to rowNum-1) {
        contents += createRow(colNum, i+1, myPitchWithColors.getLine(i))
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
        preferredSize = new Dimension(s, s)
        background = jColor(c.getRGB)
        val myName = n
        reactions += {
          case event.ButtonClicked(_) =>
            InputHandler.handle(myName, controller)
            text = Filling.filled.toString()
            enabled = false
            //redoButton.enabled = true
        }
      }
    return myButton
  }

  // Punktezeile erzeugen
  def createPoints(col:Int):GridPanel = {
    val cellWidth = 3
    val myPoints = new GridPanel(1, col) {
      background = jColor.BLACK
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, jColor.BLACK)
      val pointStringArray = if (col%2 == 0) { // für gerade Spaltenanzahl: 
                                EvenOdd.handle(EvenEvent())(cellWidth, col).toCharArray()
                              } else { // für ungerade Spaltenanzahl:
                                EvenOdd.handle(OddEvent())(cellWidth, col).toCharArray()
                              }
      for (i <- 0 to pointStringArray.length-1) {
        if (pointStringArray(i).isDigit) {
          contents += new Label(pointStringArray(i).toString()) {
            foreground = jColor.LIGHT_GRAY
          }
        }
      }
    }
    return myPoints
  }
  
}


// ToDos: 
// Würfel als Buttons 
  	// Auswahl + Kontrolle dieser
    // Farben darstellen
// Menüleiste
    // Option zum Beenden (Event.Quit)
    // Spielanleitung
