package com.skidis.lcr.player

import org.scalatest.{FunSpec, MustMatchers}

class LcrPlayerSpec extends FunSpec with MustMatchers {
  describe("Lcr Player") {
    it("loseItem decrements the item count") {
      val itemCount = 2
      val player = LcrPlayer("Some Name", itemCount)

      val newPlayer = player.loseItem
      newPlayer.itemCount mustBe itemCount -1
    }
  }

  it("gainItem increments the item count") {
    val itemCount = 2
    val player = LcrPlayer("Some Name", itemCount)

    val newPlayer = player.gainItem
    newPlayer.itemCount mustBe itemCount +1
  }
}
