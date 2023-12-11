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




class myGUI(controller: Controller) extends Frame with Observer {
  controller.add(this)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => println("dice")
      case Event.Crossed => println("cross")
      case Event.Undone => println("undo")
      case Event.Redone => println("redo")
    }

  var rounds = 0
  val num_of_rounds = controller.pitch.col_num * 2
  val rows = controller.pitch.row_num
  val cols = controller.pitch.col_num

  val myFrame = {
    preferredSize = new Dimension(800, 450)

    title = "Nochmal"

    contents = new BoxPanel(Orientation.Vertical) {
      border = BorderFactory.createMatteBorder(20, 20, 20, 20, jColor.darkGray)
      
      contents += createTitle(cols)

      contents += createPitch(rows, cols)

      contents += createPoints(cols)

      // es fehlen:
      // Würfel -> Synchron mit TUI?!

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
    val pitch = new GridPanel(rowNum, colNum) {
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, jColor.BLACK)

      val r = myColor.red
      val y = myColor.yellow
      val or = myColor.orange
      val gr = myColor.green
      val b = myColor.blue
      
      val firstRowColors = List(gr, gr, gr, y, y, y, y)
      val secondRowColors = List(or, gr, y, gr, y, y, or)
      val thirdRowColors = List(b, gr, r, gr, gr, gr, gr)
      val fourthRowColors = List(b, r, r, gr, or, or, b)

      contents += createRow(colNum, 1, firstRowColors)
      contents += createRow(colNum, 2, secondRowColors)
      contents += createRow(colNum, 3, thirdRowColors)
      contents += createRow(colNum, 4, fourthRowColors)
    

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


