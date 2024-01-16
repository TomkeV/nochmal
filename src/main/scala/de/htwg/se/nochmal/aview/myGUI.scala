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
class myGUI(controller: ControllerInterface) extends MainFrame with Observer {
  controller.add(this)

  // ------------------------------------------------------------- Variablen
  val num_of_rounds = controller.pitch.col_num * 2 // speichern der maximalen Rundenzahl
  val rows = controller.pitch.row_num 
  val cols = controller.pitch.col_num
  val pitchBackground = jColor(controller.pitch.myColor.background.getRGB) // speichern der Farbe des Blocks

  val spielfeld = new GUISpielfeld(controller)

  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => //redoButton.enabled = false
      case Event.Crossed => //redoButton.enabled = true
      case Event.Applied => 
                            //redoButton.enabled = false
      case Event.Loaded => //this.repaint()
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
  val myFrame = new Frame {
    preferredSize = new Dimension(800, 600)

    title = "Nochmal"

    menuBar = new MenuBar {
      contents += new Menu("Spiel") {
        contents += new MenuItem(Action("Schliessen") {
          sys.exit(0)
        })
        contents += new MenuItem(Action("Speichern") {
          InputHandler.handle("s", controller)
        })
        contents += new MenuItem(Action("Laden") { // funktioniert nicht!!!!
          InputHandler.handle("l", controller)
        })
      }
    }

    contents = new BoxPanel(Orientation.Vertical) {
      border = BorderFactory.createMatteBorder(20, 20, 20, 20, jColor.darkGray)
      
      contents += spielfeld.spielfeld

      // Anlegen der Würfel:
      val dice = Range(0, 6).map(x =>
        if (x <= 3) then
          createDie("die"+(x+1), 'n')
        else 
          createDie("die"+(x+1), 'c')).toVector


      // Methode zum Erzeugen der Würfel
      def createDie(n:String, t:Char):Button = {
        new Button {
          val typ = t
          name = n
          text = ""
          preferredSize = new Dimension(40, 20)
          border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
          enabled = false
          reactions += {
            case event.ButtonClicked(_) =>
              println("Button " + name +" geklickt")
              typ match
                case 'c' => 
                  println("Farbwuerfel")
                  spielfeld.color = dieChosen(this)
                            println("Farbe gesetzt: " + spielfeld.color)
                case 'n' => 
                  println("Zahlwuerfel")
                  spielfeld.number = dieChosen(this)
          }
        }
      }
      // -------------- Hilfsmethode Würfel auswählen ---------------
      def dieChosen(d:Button) : String = {
        d.name match 
          case "die1" => dice(1).enabled = false
                         dice(2).enabled = false
                         println(dice(0).text)
                         dice(0).text
          case "die2" => dice(0).enabled = false
                         dice(2).enabled = false
                         println(dice(1).text)
                         dice(1).text
          case "die3" => dice(0).enabled = false
                         dice(1).enabled = false
                        println(dice(2).text)
                         dice(2).text
          case "die4" => dice(4).enabled = false
                         dice(5).enabled = false
                         setColorsdieText(dice(3).text)
          case "die5" => dice(3).enabled = false
                         dice(5).enabled = false
                         setColorsdieText(dice(4).text)
          case "die6" => dice(3).enabled = false
                         dice(4).enabled = false
                         setColorsdieText(dice(5).text)
      }

      // -------------- Hilfsmethode Farbstring ----------------
      def setColorsdieText(t:String):String = {
        println("Ausgewählt:" + t)
        t match
          case "rot" => myColor.red.getRGB.toString
          case "orange" => myColor.orange.getRGB.toString
          case "gruen" => myColor.green.getRGB.toString
          case "gelb" => myColor.yellow.getRGB.toString
          case "blau" => myColor.blue.getRGB.toString
          case _ => "Joker!"
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
                dice(0).text = dicedArray(1)
                dice(0).enabled = true
                dice(1).text = dicedArray(2)
                dice(1).enabled = true
                dice(2).text = dicedArray(3)
                dice(2).enabled = true
                dice(3).text = dicedArray(4)
                dice(3).enabled = true
                setBackground(dice(3))
                dice(4).text = dicedArray(5)
                dice(4).enabled = true
                setBackground(dice(4))
                dice(5).text = dicedArray(6)
                dice(5).enabled = true
                setBackground(dice(5))
            }
          }
        contents += new Label()
        contents += new Label() 
      } 

      // Panel zur Darstellung der 6 Würfel
      contents += new GridPanel(1, 6) {
        preferredSize = new Dimension(800, 40)
        Range(0, 6).map(x =>
          contents += dice(x))
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
    
    pack()
    centerOnScreen()
    open()
  }
}


// ToDos: 
// Menüleiste
    // Option zum Beenden (Event.Quit)
    // Spielanleitung