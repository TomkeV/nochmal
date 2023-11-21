/* package de.htwg.se.nochmal
package util

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

import controller.Controller
import model.PitchAsMatrix
import model.Numbers_dice
import model.Colors_dice

class ObserverSpec extends AnyWordSpec {
  class ObserverTest extends Observer {
    val myController = Controller(new PitchAsMatrix(1, 1), new Numbers_dice, new Colors_dice)
    myController.add(this)

    override def update(): String = "Update erfolgreich" //startgame 

  }
  class ObservableTest extends Observable

  "Text " should {
    val testObserver = ObserverTest()
    "add Observer in List " in {
      
    }
  }
}
 */