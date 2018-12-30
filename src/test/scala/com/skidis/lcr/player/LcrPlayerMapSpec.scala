package com.skidis.lcr.player

import com.skidis.lcr.Direction._
import org.scalatest.{FunSpec, MustMatchers}

class LcrPlayerMapSpec extends FunSpec with MustMatchers {
  val (currPlayerName, leftPlayerName, rightPlayerName) = ("Current Player", "Left Player", "Right Player")
  val (currPlayerItems, leftPlayerItems, rightPlayerItems) = (5, 3, 1)
  val players = Seq(
    LcrPlayer(currPlayerName, currPlayerItems),
    LcrPlayer(leftPlayerName, leftPlayerItems),
    LcrPlayer(rightPlayerName, rightPlayerItems)
  )

  describe("Lcr Player Map") {
    it("finds a player by name") {
      val playerMap = LcrPlayerMap(players)

      playerMap.playerByName(currPlayerName) mustBe Some(LcrPlayer(currPlayerName, currPlayerItems))
      playerMap.playerByName(leftPlayerName) mustBe Some(LcrPlayer(leftPlayerName, leftPlayerItems))
      playerMap.playerByName("Player Not Found") mustBe None
    }

    it("playerLoses returns a map with the item count of specified player decremented") {
      val playerMap = LcrPlayerMap(players)
      val updatedMap = playerMap.playerLoses(players.head)
      validateItemCounts(updatedMap, currPlayerItems -1, leftPlayerItems, rightPlayerItems)
    }

    it("playerGains returns a map with the item count of specified player incremented") {
      val playerMap = LcrPlayerMap(players)
      val updatedMap = playerMap.playerGains(players.head)
      validateItemCounts(updatedMap, currPlayerItems +1, leftPlayerItems, rightPlayerItems)
    }

    it("playerLosesTo returns a map with the item count of specified player decremented " +
      "and the player in the specified direction incremented") {
      val playerMap = LcrPlayerMap(players)
      val updatedMap = playerMap.playerLosesTo(players.head, Right)
      validateItemCounts(updatedMap, currPlayerItems -1, leftPlayerItems, rightPlayerItems +1)
    }
  }

  def validateItemCounts(playerMap: PlayerMap, playerCount: Int, leftPlayerCount: Int, rightPlayerCount: Int): Unit = {
    playerMap.playerByName(currPlayerName).get.itemCount mustBe playerCount
    playerMap.playerByName(leftPlayerName).get.itemCount mustBe leftPlayerCount
    playerMap.playerByName(rightPlayerName).get.itemCount mustBe rightPlayerCount
  }
}
