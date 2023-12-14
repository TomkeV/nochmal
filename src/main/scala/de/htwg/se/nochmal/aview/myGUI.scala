// a very difficult version but it hopefully works...
package de.htwg.se.nochmal
package aview

// Bibliotheksimports:
import scala.swing._ 
import javax.swing.BorderFactory
import java.awt.Color as jColor

// eigene imports:
import controller.Controller
import controller.diceResult

import util.Observer
import util.Event
import util.InputHandler
import util.EvenOdd
import util.EvenEvent
import util.OddEvent

import model.myPitchWithColors
import model.Color as myColor
import model.Filling



class myGUI(controller: Controller) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => controller.singleDice() //dicedValues = controller.dice()
      case Event.Crossed => 
      case Event.Undone => 
      case Event.Redone =>
    }

  var rounds = 0
  val num_of_rounds = controller.pitch.col_num * 2
  val rows = controller.pitch.row_num
  val cols = controller.pitch.col_num

  //var dicedValues = ""

  val myFrame = {
    preferredSize = new Dimension(800, 600)

    title = "Nochmal"

    contents = new BoxPanel(Orientation.Vertical) {
      border = BorderFactory.createMatteBorder(20, 20, 20, 20, jColor.darkGray)
      
      contents += createTitle(cols)

      contents += createPitch(rows, cols)

      contents += createPoints(cols)

      val die1 = new Label() { text = "" }
      val die2 = new Label() { text = "" }
      val die3 = new Label() { text = "" }
      val die4 = new Label() { text = "" }
      val die5 = new Label() { text = "" }
      val die6 = new Label() { text = "" }

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

      contents += new GridPanel(1, 6) {
        preferredSize = new Dimension(800, 50)
        contents += die1
        contents += die2
        contents += die3 
        contents += die4 
        contents += die5
        contents += die6
      }

      // es fehlen:

      // Vergrößerung
      // -> umbau ankreuzen auf A B C ,... ? 

      // Bei Würfelergebnissen Farben darstellen

      // Rundenzähler -> apply/... um mehrere Kreuze zu ermöglichen?
        /* -> InputChain Eingabe a 
         * -> Kreuze einzeln auf Stack
         * -> kein Redo mehr nach Apply
         * -> Rundenzähler erst mit Apply bzw neuem Würfeln hochzählen
         */
      
      contents += new GridPanel(1, 5) {
        background = jColor.darkGray
        border = BorderFactory.createMatteBorder(10, 10, 10, 10, jColor.darkGray)
        contents += new Label()
        contents += new Button("Apply")
        contents += new Label()
        contents += new Button("Redo")
        contents += new Label()
      }  

      contents += new GridPanel(1,1) {
        background = jColor.WHITE
        contents += new Label("Runde " + rounds + " von " + num_of_rounds)
      }
      
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
        val cross = "x" + num.toString() + (i+1).toString()
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