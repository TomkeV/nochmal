package de.htwg.se.nochmal
package aview

import scala.swing._

import controller.Controller
import util.Observer
import util.Event
import model.Color
import java.awt.Color as jColor
import model.Filling
import javax.swing.BorderFactory
import de.htwg.se.nochmal.util.InputHandler

// "globale" Variablen
val defaultBackgroundColor = 32 * 65536 + 32 * 256 + 32 
val defaultButtonColor = 192 * 65536 + 192 * 256 + 192
val defaultTextColor = 224 * 65536 + 224 * 256 + 244

class GUI(controller: Controller) extends Frame with Observer {
  controller.add(this)

  var rounds = 0
  val num_of_rounds = controller.pitch.col_num * 2
  val rows = controller.pitch.row_num
  val cols = controller.pitch.col_num

  override def update(e: Event): Unit = 
  // Methode update -> braucht noch Implementierungen!
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => 
      case Event.Crossed =>
      case Event.Undone =>
      case Event.Redone =>
    }

  val firstFrame = {
    preferredSize = new Dimension(800, 450) 
    
    title = "Nochmal!"

    contents = new BoxPanel(Orientation.Vertical) {
      border = BorderFactory.createMatteBorder(20, 20, 20, 20, jColor.darkGray)
            
      val titleLine = createTitle(cols)
      contents += titleLine
      val pitch = createPitch(rows, cols)
      contents += pitch

      val diceTitel = new GridPanel(1, 1) {
        background = jColor.darkGray
        contents += new Label("Deine Würfel: ") {
          foreground = jColor(defaultTextColor)
        }
      }
      contents += diceTitel

      val dicePanel = createDice(6)
      contents += dicePanel

      val diceButton = new GridPanel(1, 5) {
        background = jColor.darkGray
        contents += new Label("") {
          background = jColor.darkGray
        }
        contents += new Label("") {
          background = jColor.darkGray
        }
        contents += new Button("wuerfeln") {
          background = jColor(defaultButtonColor)
          border = BorderFactory.createMatteBorder(10, 0, 10, 0, jColor.darkGray)
          preferredSize = new Dimension(50, 30)
          reactions += {
            case event.ButtonClicked(_) =>
              InputHandler.handle("w", controller)
          }
        }
        contents += new Label("") {
          background = jColor.darkGray
        }
        contents += new Label("") {
          background = jColor.darkGray
        }
        visible = true
      }
      contents += diceButton

      val counter = new GridPanel(1, 1) {
        background = jColor(defaultBackgroundColor)
        border = BorderFactory.createMatteBorder(0, 50, 20, 50, jColor.darkGray)
        contents += new Label("Runde " + rounds + " von " + num_of_rounds) {
          foreground = jColor(defaultTextColor)
        }
      }
      contents += counter
    }
    
  }


    // Feld mit x Zeilen mit Buttons erzeugen
  def createPitch(rowNum:Int, colNum:Int): GridPanel = {
    val pitch = new GridPanel(rowNum, 1) {
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, jColor.BLACK)
      for (i <- 0 to rowNum-1) {
        contents += createRow(colNum)
      }
    }
    return pitch
  }

  // Titelzeile erzeugen
  def createTitle(col:Int):GridPanel = {
    val myTitle = new GridPanel(1, col) {
      background = jColor.BLACK
      border = BorderFactory.createMatteBorder(0, 20, 0, 20, jColor.BLACK)
      for (i <- 0 to col-1) {
        contents += new Label((('A' + i).toChar).toString()) {
          foreground = jColor(defaultTextColor)
        }
      }
    }
    return myTitle
  }

  // Zeile mit Buttons erzeugen
  def createRow(col:Int):GridPanel = {
    val myPanel = new GridPanel(1, col) {
      for (i <- 0 to col-1)  {
        contents += createButton(Filling.empty) 
      }
    }
    return myPanel
  }

  // neuen Button mit Filling MIT FARBWAHL ERGÄNZEN!
  def createButton(fill:Filling): Button = {
    val myButton = new Button(fill.toString) {
      preferredSize = new Dimension(30, 30)
      //background = jColor(c.getRGB)
      reactions += {
        case event.ButtonClicked(_) =>
                InputHandler.handle("x 1 1", controller)
      }
    }
    return myButton
  }

  // Würfel erzeugen
  def createDice(n:Int):GridPanel = {
    val diceGrid = new GridPanel(1, n) {
      background = jColor(defaultBackgroundColor)
      for (i <- 0 to n-1) {
        contents += new Label("w"+i.toString) {
          foreground = jColor(defaultTextColor)
        }
      }
    }
    return diceGrid
  }

  // zentrierte Label erzeugen
  def createCenteredLabel(text:String): GridPanel = {
    new GridPanel(1, 1) {
          background = jColor(defaultBackgroundColor)
          contents += new Label(text) {
            foreground = jColor(defaultTextColor)
          }
        }
}
  
  pack()
  centerOnScreen()
  open()
}



    
