// a very difficult version but it hopefully works...
package de.htwg.se.nochmal
package aview

// Bibliotheksimports:
import scala.swing._ 
import javax.swing.BorderFactory
import java.awt.Color as jColor

// eigene imports:
import controller.Controller
import util.Observer
import de.htwg.se.nochmal.util.Event
import model.Color as myColor
import model.Filling
import util.InputHandler
import util.EvenOdd
import util.EvenEvent
import util.OddEvent
import java.awt.Color




class myGUI(controller: Controller) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => dicedValues = controller.dice()
      case Event.Crossed => 
      case Event.Undone => 
      case Event.Redone =>
    }

  var rounds = 0
  val num_of_rounds = controller.pitch.col_num * 2
  val rows = controller.pitch.row_num
  val cols = controller.pitch.col_num

  var dicedValues = ""

  val myFrame = {
    preferredSize = new Dimension(800, 450)

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

      contents += new Button("wuerfeln") {
        reactions += {
          case event.ButtonClicked(_) =>
            InputHandler.handle("w", controller)

            val dicedArray = dicedValues.split("""\R""")
            die1.text = dicedArray(1)
            die2.text = dicedArray(2)
            die3.text = dicedArray(3)
            die4.text = dicedArray(4)
            die5.text = dicedArray(5)
            die6.text = dicedArray(6)
        }
      }

      contents += new GridPanel(1, 6) {
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

      // Rundenzähler -> apply/... um mehrere Kreuze zu ermöglichen?

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

      val r = myColor.red
      val y = myColor.yellow
      val or = myColor.orange
      val gr = myColor.green
      val b = myColor.blue
      
      val firstRowColors = List(gr, gr, gr, y, y, y, y, gr, b, b, b, or, y, y, y)
      val secondRowColors = List(or, gr, y, gr, y, y, or, or, r, b, b, or, or, gr, gr)
      val thirdRowColors = List(b, gr, r, gr, gr, gr, gr, r, r, r, y, y, or, gr, gr)
      val fourthRowColors = List(b, r, r, gr, or, or, b, b, gr, gr, y, y, or, r, b)
      val fifthRowColors = List(r, or, or, or, or, r, b, b, or, or, or, r, r, r, r)
      val sixthRowColors = List(r, b, b, r, r, r, r, y, y, or, r, b, b, b, or)
      val seventhRowColors = List(y, y, b, b, b, b, r, y, y, y, gr, gr, gr, or, or)

      val ColorsList = List(firstRowColors, secondRowColors, thirdRowColors, 
                            fourthRowColors, fifthRowColors, sixthRowColors, seventhRowColors)

      for (i <- 0 to rowNum-1) {
        contents += createRow(colNum, i+1, ColorsList(i))
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