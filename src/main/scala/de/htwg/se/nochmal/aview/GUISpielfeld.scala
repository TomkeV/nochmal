/**
  * GUISpielfeld.scala
  * @author: Tomke Velten
  * @version: 16.01.2024
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview



// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne Imports
import controller.controllerComponent.ControllerInterface
import util.{Observer, Event, InputHandler, EvenOdd, EvenEvent, OddEvent}
import model.Color as myColor
import model.Filling

// Bibliotheksimports
import scala.swing.*
import java.awt.Color as jColor
import javax.swing.{BorderFactory, BoxLayout, JOptionPane}
import play.api.libs.json.Json
import scala.io.Source



// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class GUISpielfeld(controller: ControllerInterface) extends Observer {
  controller.add(this)

  // ------------------------------------------------------------------- VARIABLEN 
  private val pitchBackground = jColor(controller.pitch.myColor.background.getRGB) // speichern der Farbe des Blocks
  private val titel = createTitle(controller.pitch.col_num)
  private var matrix = createPitch(controller.pitch.row_num, controller.pitch.col_num)
  private val punkte = createPoints(controller.pitch.col_num)

  val spielfeld = setUpPitch(titel, matrix, punkte)

  var number = ""
  var crossesSet = 0
  var color = ""
  var summe = 0

  // ------------------------------------------------------------------- FUNKTIONEN
  override def update(e: Event): Unit = {
    e match
      case Event.Applied => crossesSet = 0
                            number = ""
                            color = ""
      case Event.Crossed => 
      case Event.Diced => 
      case Event.Loaded => 
      case Event.Quit => 
      case Event.Redone => 
      case Event.Saved => 
      case Event.Undone => 
    
  }

  // Spielfeld zusammensetzen
  def setUpPitch(titel:GridPanel, feld:GridPanel, punkte:GridPanel): BoxPanel = {
    new BoxPanel(Orientation.Vertical) {
      contents += titel
      contents += feld
      contents += punkte
    }
  }

  // Titelzeile erzeugen
  def createTitle(col:Int):GridPanel = {
    new GridPanel(1, col) {
      border = BorderFactory.createMatteBorder(10, 20, 10, 20, pitchBackground)
      for (i <- 0 to col-1) {
        contents += new Label((('A' + i).toChar).toString()) {
          background = jColor.WHITE
          foreground = jColor.BLACK
        }
      }
    }
  }

  // Feld mit x Zeilen mit Buttons erzeugen
  def createPitch(rowNum:Int, colNum:Int): GridPanel = {
    println("createPitch aufgerufen")
    new GridPanel(rowNum, 1) {
      border = BorderFactory.createMatteBorder(0, 20, 10, 20, pitchBackground)
      for (i <- 0 to rowNum-1) {
        contents += createRow(colNum, i+1, controller.pitch.myColor.getLine(i))
      }
    }
  }

  // Automatisiertes Erzeugen von Zeilen mit bunten Feldern
  def createRow(cols:Int, num:Int, colors:List[myColor]):GridPanel = {
    new GridPanel(1, cols) {
      for (i <- 0 to cols-1) {
        val cross = "x" + ('A' + i).toChar.toString() + num.toString()
        contents += createButton(colors(i), cross)
      }
    }
  }

  // Automatisiertes Erzeugen von bunten, ankreuzbaren Feldern
  def createButton(c:myColor, n:String): Button = {
    new Button() {
      preferredSize = new Dimension(30, 30)
      background = jColor(c.getRGB)
      name = n
/*       val x = n.toCharArray()(2)
      val y = n.toCharArray()(1) */

      reactions += {
        case event.ButtonClicked(_) =>
          if (number == "") then {
            JOptionPane.showMessageDialog(null, "Du musst eine Zahl auswählen!")
          } else if (color == "") then {
            JOptionPane.showMessageDialog(null, "Du musst eine Farbe auswählen!")
          } else if (number != "!") then {
            if (crossesSet < number.toInt) then {
              if (c.getRGB.toString == color) then
                handleClick(this)
              else if (color == "Joker!") then
                handleClick(this)
              else
                JOptionPane.showMessageDialog(null, "Du musst deine ausgewählte Farbe ankreuzen!")
            }
          } else if (number == "!") {
            if (crossesSet < 5) then {
              if (c.getRGB.toString == color) then
                InputHandler.handle(name, controller)
                text = Filling.filled.toString()
                enabled = false
                crossesSet = crossesSet + 1
              else if (color == "Joker!") then
                handleClick(this)
              else
                JOptionPane.showMessageDialog(null, "Du musst deine ausgewählte Farbe ankreuzen!")
            }
          }
        }
    }
  }

  // Button ankreuzen
  def handleClick(b:Button) = {
    InputHandler.handle(b.name, controller)
    b.text = Filling.filled.toString()
    b.enabled = false
    crossesSet = crossesSet + 1
  }

  // Punktezeile erzeugen
  def createPoints(col:Int):GridPanel = {
    val cellWidth = 3
    new GridPanel(1, col) {
      border = BorderFactory.createMatteBorder(0, 20, 20, 20, pitchBackground)
      val pointStringArray = if (col%2 == 0) { // für gerade Spaltenanzahl: 
                                EvenOdd.handle(EvenEvent())(cellWidth, col).toCharArray()
                              } else { // für ungerade Spaltenanzahl:
                                EvenOdd.handle(OddEvent())(cellWidth, col).toCharArray()
                              }

      Range(0, pointStringArray.length-1).map(x => 
        if (pointStringArray(x).isDigit) {
          contents += new Button(pointStringArray(x).toString()) {
            name = pointStringArray(x).toString()
            foreground = jColor.BLACK
            background = jColor.WHITE
            border = BorderFactory.createMatteBorder(0, 10, 0, 10, pitchBackground)
            reactions += {
              case event.ButtonClicked(_) =>
                summe += name.toInt
                this.enabled = false
            }
          }
        })
    }
  }

}



/*   def loadPitchFromJson(): Vector[Vector[Filling]] = {
    val json = Json.parse(Source.fromFile("saves/save1.json").mkString)
    val pitch = (json \ "pitch").as[IndexedSeq[String]]
    pitch.map(i =>
      val chars = i.toCharArray()
      Range(0, chars.length).map(y =>
        chars(y) match
          case ' ' => Filling.empty
          case 'X' => Filling.filled
        ).toVector).toVector
  } */