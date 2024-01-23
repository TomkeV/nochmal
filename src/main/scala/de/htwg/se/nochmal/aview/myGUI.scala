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
import controller.{diceResult, rounds, moveDone, summe}

import model.Color as myColor
import util.*

// Bibliotheksimports:
import scala.swing._ 
import javax.swing. {BorderFactory, JOptionPane}
import java.awt.Color as jColor


// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------ CLASS DEFINITION
class myGUI(controller: ControllerInterface) extends MainFrame with Observer {
  controller.add(this)

  // ------------------------------------------------------------- VARIABLEN
  val num_of_rounds = controller.pitch.col_num * 2
  val rows = controller.pitch.row_num 
  val cols = controller.pitch.col_num
  val pitchBackground = jColor(controller.pitch.pitchColor.background.getRGB)

  val buttonsMap = new ButtonMap(controller)
  val spielfeld = new GUISpielfeld(controller, buttonsMap)
  
  // ------------------------------------------------- MAINFRAME
  val myMainFrame = new Frame {
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
        contents += new MenuItem(Action("Laden") {
          InputHandler.handle("l", controller)
          spielfeld.updatePitch()
        })
      }
    }

    contents = new BoxPanel(Orientation.Vertical) {
      border = BorderFactory.createMatteBorder(20, 20, 20, 20, jColor.darkGray)
      
      // ----------------------------------- MAINFRAME: VARIABLEN
      val dice = {Range(0, 6).map(x =>
        if (x < 3) then
          createDice("die"+(x+1), 'n')
        else
          createDice("die"+(x+1), 'c')).toVector
      }
      val sumLabel = new Label("Summe: " + summe)
      val roundLabel = new Label("Runde " + rounds + " von " + num_of_rounds)
      val gameStatePanel = new GridPanel(1,2) {
        background = jColor.WHITE
        contents += roundLabel
        contents += sumLabel
      }

      // ------------------------------------ MAINFRAME: AUFBAU
      contents += spielfeld.spielfeld

      contents += new GridPanel(1, 5) {
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
                Range(0, dice.length).map(i =>
                  dice(i).text = dicedArray(i+1)
                  dice(i).enabled = true
                  if (i >= 3) then setColorsDieBackground(dice(i))
                )
            }
          }
        contents += new Label()
        contents += new Label() 
      } 

      contents += new GridPanel(1, 6) {
        preferredSize = new Dimension(800, 40)
        Range(0, 6).map(x =>
          contents += dice(x))
      }

      contents += new GridPanel(1, 5) {
        background = jColor.darkGray
        border = BorderFactory.createMatteBorder(10, 10, 10, 10, jColor.darkGray)
        contents += new Label()
        contents += new Button("Undo") {
          reactions += {
            case event.ButtonClicked(_) =>
              if (!moveDone) then
                JOptionPane.showMessageDialog(null, "Du kannst nur das Ankreuzen rückgängig machen!")
              else 
                    InputHandler.handle("u", controller)
                    spielfeld.updatePitch()
          }
        }
        contents += new Label()
        contents += new Button("Bestaetigen") {
                  reactions += {
                    case event.ButtonClicked(_) =>
                      if (!moveDone) then
                        JOptionPane.showMessageDialog(null, "Du musst erst etwas ankreuzen!")
                      else 
                        InputHandler.handle("a", controller)
                        roundLabel.text = "Runde " + rounds + " von " + num_of_rounds
                        sumLabel.text = "Summe: " + summe
                        gameStatePanel.repaint()
                  }
                }
        contents += new Label()
      }  

      contents += gameStatePanel
    
      // ------------------------------------ MAINFRAME: FUNKTIONEN
      def createDice(n:String, t:Char):Button = {
          new Button {
            val typ = t
            name = n
            text = ""
            preferredSize = new Dimension(40, 20)
            border = BorderFactory.createMatteBorder(0, 31, 0, 31, jColor.darkGray)
            enabled = false
            reactions += {
              case event.ButtonClicked(_) =>
                typ match
                  case 'c' => buttonsMap.color = dieChosenReaction(this)
                  case 'n' => buttonsMap.number = dieChosenReaction(this)
            }
          }
        }

        def dieChosenReaction(d:Button) : String = {
          d.name match 
            case "die1" => dice(1).enabled = false
                          dice(2).enabled = false
                          dice(0).text
            case "die2" => dice(0).enabled = false
                          dice(2).enabled = false
                          dice(1).text
            case "die3" => dice(0).enabled = false
                          dice(1).enabled = false
                          dice(2).text
            case "die4" => dice(4).enabled = false
                          dice(5).enabled = false
                          setColorsDieText(dice(3).text)
            case "die5" => dice(3).enabled = false
                          dice(5).enabled = false
                          setColorsDieText(dice(4).text)
            case "die6" => dice(3).enabled = false
                          dice(4).enabled = false
                          setColorsDieText(dice(5).text)
        }

        def setColorsDieText(t:String):String = {
          t match
            case "rot" => myColor.red.getRGB.toString
            case "orange" => myColor.orange.getRGB.toString
            case "gruen" => myColor.green.getRGB.toString
            case "gelb" => myColor.yellow.getRGB.toString
            case "blau" => myColor.blue.getRGB.toString
            case _ => "Joker!"
        }

        def setColorsDieBackground(d:Button) = {
          d.background = d.text match
            case "rot" => jColor(myColor.red.getRGB)
            case "orange" => jColor(myColor.orange.getRGB)
            case "gelb" => jColor(myColor.yellow.getRGB)
            case "gruen" => jColor(myColor.green.getRGB)
            case "blau" => jColor(myColor.blue.getRGB)
            case "Joker!" => jColor.BLACK 
            case _ => jColor.white
        }
    }
    
    pack()
    centerOnScreen()
    open()
  }

  // ------------------------------------------------------------- FUNKTIONEN
  override def update(e: Event): Unit = 
    e match {
      case Event.Quit => this.dispose()
      case Event.Diced => 
      case Event.Crossed => 
      case Event.Applied => 
      case Event.Loaded => 
      case Event.Saved => 
      case Event.Undone => 
      case Event.Redone => 
    }

}