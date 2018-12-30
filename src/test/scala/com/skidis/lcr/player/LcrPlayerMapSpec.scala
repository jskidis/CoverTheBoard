package com.skidis.lcr.player

import com.skidis.lcr.Direction._
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

    it("returns the proper neighbor to the right and left of a player") {
      playerMap.neighbor(player1, Left) mustBe Some(player2)
      playerMap.neighbor(player1, Right) mustBe Some(player3)

      playerMap.neighbor(player2, Left) mustBe Some(player3)
      playerMap.neighbor(player2, Right) mustBe Some(player1)

      playerMap.neighbor(player3, Left) mustBe Some(player1)
      playerMap.neighbor(player3, Right) mustBe Some(player2)
    }

    it("returns none if player is not in map") {
      val playerNotInMap = LcrPlayer("Some Other Player")
      playerMap.neighbor(playerNotInMap, Left) mustBe None
    }
  }
}
