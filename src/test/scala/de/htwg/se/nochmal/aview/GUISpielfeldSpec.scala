/* /**
  * GUISpielfeldSpec.scala
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

// -----------------------------------------------------------------------------------------------------
// ------------------------------------------------------------------------------------------------ TEST
class GUISpielfeldSpec extends AnyWordSpec {
  val testController = summon[ControllerInterface]
  val testButtonMap = ButtonMap(testController)
  val testGUIfeld = GUISpielfeld(testController, testButtonMap)
  "The GUI pitch " should {
    "save the defaul background if there is none given " in {
    }
  }
}
 */