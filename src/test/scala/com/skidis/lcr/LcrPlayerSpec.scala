package com.skidis.lcr

import org.scalatest.{FunSpec, MustMatchers}

class LcrPlayerSpec extends FunSpec with MustMatchers {
  describe("Lcr Player") {
    it("loseItem decrements the item count") {
      val itemCount = 2
      val player = LcrPlayer("Someee Name", itemCount)

      val newPlayer = player.loseItem
      newPlayer.itemCount mustBe itemCount -1
    }
  }
}
