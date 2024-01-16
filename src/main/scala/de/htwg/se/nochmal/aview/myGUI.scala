/**
  * myGUI.scala
  * Class for graphic-based user-interface of the game "Nochmal!"
  * Link to the game: https://www.schmidtspiele.de/details/produkt/noch-mal-.html
  * @author: Tomke Velten
  */

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne imports:
import controller.controllerComponent.ControllerInterface
import controller.controllerComponent.controllerBaseImpl.diceResult
import controller.controllerComponent.controllerBaseImpl.rounds

import util.*

import model.pitchComponent.baseModel.PitchWithColors
import model.pitchComponent.baseModel.blackColorsList
import model.Color as myColor
import model.Filling

// Bibliotheksimports:
import scala.swing._ 
import javax.swing.BorderFactory
import java.awt.Color as jColor

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class myGUI(controller: ControllerInterface) extends Frame with Observer {
  controller.add(this)

  // ------------------------------------------------------------- Variablen
  val num_of_rounds = controller.pitch.col_num * 2 // speichern der maximalen Rundenzahl
  val rows = controller.pitch.row_num 
  val cols = controller.pitch.col_num
  val pitchBackground = jColor(controller.pitch.myColor.background.getRGB) // speichern der Farbe des Blocks

  val spielfeld = new GUISpielfeld(controller)
/*   var number = ""
  var color = ""
  var crossesSet = 0
  var summe = 0 */

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => //redoButton.enabled = false
      case Event.Crossed => //redoButton.enabled = true
      case Event.Applied => 
                            //redoButton.enabled = false
      case Event.Loaded => this.repaint()
      case Event.Saved => 
      case Event.Undone => 
      case Event.Redone => 
    }

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
      
      contents += spielfeld.spielfeld

      // Anlegen der 6 Würfel
      val die1 = createDie("die1", 'n')
      val die2 = createDie("die2", 'n')
      val die3 = createDie("die3", 'n')
      val die4 = createDie("die4", 'c')
      val die5 = createDie("die5", 'c')
      val die6 = createDie("die6", 'c')

      // Methode zum Erzeugen der Würfel
      def createDie(n:String, typ:Char):Button = {
        new Button {
          name = n
          text = ""
          preferredSize = new Dimension(40, 20)
          border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
          enabled = false
          reactions += {
            case event.ButtonClicked(_) =>
              typ match
                case 'c' => spielfeld.color = dieChosen(this)
                case 'n' => spielfeld.number = dieChosen(this)
          }
        }
      }
      // -------------- Hilfsmethode Würfel auswählen ---------------
      def dieChosen(d:Button) : String = {
        d.name match 
          case "die1" => die2.enabled = false
                         die3.enabled = false
                         die1.text
          case "die2" => die1.enabled = false
                         die3.enabled = false
                         die2.text
          case "die3" => die1.enabled = false
                         die2.enabled = false
                         die3.text
          case "die4" => die5.enabled = false
                         die6.enabled = false
                         setColorsdieText(die4.text)
          case "die5" => die4.enabled = false
                         die6.enabled = false
                         setColorsdieText(die5.text)
          case _ => die4.enabled = false
                    die5.enabled = false
                    setColorsdieText(die6.text)
      }
      // -------------- Hilfsmethode Farbstring ----------------
      def setColorsdieText(t:String):String = {
        t match
          case "rot" => myColor.red.getRGB.toString
          case "orange" => myColor.orange.getRGB.toString
          case "gruen" => myColor.green.getRGB.toString
          case "gelb" => myColor.yellow.getRGB.toString
          case "blau" => myColor.blue.getRGB.toString
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

                val dicedArray = diceResult.split("""\R""")
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
              sumLabel.text = "Summe: " + spielfeld.summe
              gameStatePanel.repaint()
          }
        }
        contents += applyButton
        //contents += new Label()
        //contents += redoButton
        contents += new Label()
      }  

      // Rundenzähler hinzufügen
      val sumLabel = new Label("Summe: " + spielfeld.summe)
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
}


// ToDos: 
// Menüleiste
    // Option zum Beenden (Event.Quit)
    // Spielanleitung
    // Fehlerfall bei Farbjoker!!