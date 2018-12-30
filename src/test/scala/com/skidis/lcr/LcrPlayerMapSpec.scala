package com.skidis.lcr

import org.scalatest.{FunSpec, MustMatchers}

class LcrPlayerMapSpec extends FunSpec with MustMatchers {
  describe("Lcr Player Map") {
    val player1 = LcrPlayer("Player 1")
    val player2 = LcrPlayer("Player 2")
    val player3 = LcrPlayer("Player 3")
    val playerMap = LcrPlayerMap(Seq(player1, player2, player3))

    it("finds a player by name") {
      playerMap.playerByName(player1.playerName) mustBe Some(player1)
      playerMap.playerByName(player2.playerName) mustBe Some(player2)
      playerMap.playerByName("Player Not Found") mustBe None
    }

    it("updating a player generates a new updated map") {
      val newItemCount = 10
      val updatedPlayer2 = player2.copy(itemCount = newItemCount)
      val newPlayerMap = playerMap.updatePlayer(updatedPlayer2)

      // Updated Player 2 is reflected
      newPlayerMap.playerByName(player2.playerName) mustBe Some(updatedPlayer2)

      // Other players remain unchanged
      playerMap.playerByName(player1.playerName) mustBe Some(player1)
      playerMap.playerByName(player3.playerName) mustBe Some(player3)

    }
  }
}
