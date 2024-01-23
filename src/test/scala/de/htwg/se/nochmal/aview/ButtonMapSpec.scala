/**
  * ButtonMap.scala
  * Klasse zum Speichern einer Matrix aus Buttons
  * @author: Tomke Velten
  */
// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- PACKAGE
package de.htwg.se.nochmal
package aview

// -----------------------------------------------------------------------------------------------------
// --------------------------------------------------------------------------------------------- IMPORTS
// interne imports
import Default.ControllerInterface
import controller.controllerComponent.ControllerInterface

// Bibliotheksimports
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.swing.Button
import de.htwg.se.nochmal.util.Event



// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class ButtonMapSpec extends AnyWordSpec {
  val controller = summon[ControllerInterface]
  "The ButtonMap " when {
    val testMap = new ButtonMap(controller) 
    "created" should {
      "be a map of buttons for a GUI saving their coordinates " in {
      val mock = testMap.createMatrix
      mock shouldBe a [Map[(Int, Int), Button]]
      }
    }
    "getting an event " should {
      "react to an apply " in {
        testMap.update(Event.Applied)
        testMap.crossesSet should be (0)
        testMap.number should be ("")
        testMap.color should be ("")
      }
      "react to an undo " in {
        testMap.update(Event.Undone)
        testMap.crossesSet should be (0)
      }
    }
    "used " should {
      "be able to handle clicks on it buttons " in {
        testMap.handleClick(testMap.buttonMap((1, 1)))
        testMap.crossesSet should be (1)
      }
    }
  }
}
