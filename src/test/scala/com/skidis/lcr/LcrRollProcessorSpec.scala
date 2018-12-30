package com.skidis.lcr

import com.skidis.lcr.Direction._
import org.scalatest.{FunSpec, MustMatchers}

class LcrRollProcessorSpec extends FunSpec with MustMatchers {
  val (playerName, leftPlayerName, rightPlayerName) = ("Player", "Left Player", "Right Player")
  val (playerItems, leftPlayerItems, rightPlayerItems) = (1, 3, 5)
  val players = Seq(
    LcrPlayer(playerName, playerItems),
    LcrPlayer(leftPlayerName, leftPlayerItems),
    LcrPlayer(rightPlayerName, rightPlayerItems)
  )

  describe("Lcr Turn") {
    it("if no action is rolled, all player count should remain unchanged") {
      val playerMap = LcrPlayerMap(players)
      val player = playerMap.playerByName(playerName).get
      val roll = NoActionDieSide

      val newMap = LcrRollProcessor.processRoll(player, playerMap, roll)
      validateItemCounts(newMap, playerItems, leftPlayerItems, rightPlayerItems)
    }

    it("current player count is decremented when Center is rolled") {
      val playerMap = LcrPlayerMap(players)
      val player = playerMap.playerByName(playerName).get
      val roll = DirectionDieSide(Center)

      val newMap = LcrRollProcessor.processRoll(player, playerMap, roll)
      validateItemCounts(newMap, playerItems -1, leftPlayerItems, rightPlayerItems)
    }

    it("current player count is decremented and player to right count is incremented when Right is rolled") {
      val playerMap = LcrPlayerMap(players)
      val player = playerMap.playerByName(playerName).get
      val roll = DirectionDieSide(Right)

      val newMap = LcrRollProcessor.processRoll(player, playerMap, roll)
      validateItemCounts(newMap, playerItems -1, leftPlayerItems, rightPlayerItems +1)
    }
  }

  def validateItemCounts(playerMap: PlayerMap, playerCount: Int, leftPlayerCount: Int, rightPlayerCount: Int): Unit = {
    playerMap.playerByName(playerName).get.itemCount mustBe playerCount
    playerMap.playerByName(leftPlayerName).get.itemCount mustBe leftPlayerCount
    playerMap.playerByName(rightPlayerName).get.itemCount mustBe rightPlayerCount
  }
}
