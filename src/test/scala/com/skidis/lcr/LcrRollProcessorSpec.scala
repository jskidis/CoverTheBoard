package com.skidis.lcr

import com.skidis.lcr.Direction._
import com.skidis.lcr.player.{LcrPlayer, LcrPlayerMap, PlayerMap}
import org.scalatest.{FunSpec, MustMatchers}

class LcrRollProcessorSpec extends FunSpec with MustMatchers {
  val (currPlayerName, leftPlayerName, rightPlayerName) = ("Current Player", "Left Player", "Right Player")
  val (currPlayerItems, leftPlayerItems, rightPlayerItems) = (5, 3, 1)
  val players = Seq(
    LcrPlayer(currPlayerName, currPlayerItems),
    LcrPlayer(leftPlayerName, leftPlayerItems),
    LcrPlayer(rightPlayerName, rightPlayerItems)
  )

  describe("Lcr Turn") {
    it("if no action is rolled, all player count should remain unchanged") {
      val playerMap = LcrPlayerMap(players)
      val player = playerMap.playerByName(currPlayerName).get
      val rolls = Seq(NoActionDieSide)

      val newMap = LcrRollProcessor.processRolls(player, playerMap, rolls)
      validateItemCounts(newMap, currPlayerItems, leftPlayerItems, rightPlayerItems)
    }

    it("current player count is decremented when Center is rolled") {
      val playerMap = LcrPlayerMap(players)
      val player = playerMap.playerByName(currPlayerName).get
      val rolls = Seq(DirectionDieSide(Center))

      val newMap = LcrRollProcessor.processRolls(player, playerMap, rolls)
      validateItemCounts(newMap, currPlayerItems -1, leftPlayerItems, rightPlayerItems)
    }

    it("current player count is decremented and player to right count is incremented when Right is rolled") {
      val playerMap = LcrPlayerMap(players)
      val player = playerMap.playerByName(currPlayerName).get
      val rolls = Seq(DirectionDieSide(Right))

      val newMap = LcrRollProcessor.processRolls(player, playerMap, rolls)
      validateItemCounts(newMap, currPlayerItems -1, leftPlayerItems, rightPlayerItems +1)
    }

    it("processes multiple rolls") {
      val playerMap = LcrPlayerMap(players)
      val player = playerMap.playerByName(currPlayerName).get
      val rolls = Seq(DirectionDieSide(Right), DirectionDieSide(Right), NoActionDieSide,
        DirectionDieSide(Center), DirectionDieSide(Left), NoActionDieSide)

      val newMap = LcrRollProcessor.processRolls(player, playerMap, rolls)
      validateItemCounts(newMap, currPlayerItems -4, leftPlayerItems +1, rightPlayerItems +2)
    }
  }

  def validateItemCounts(playerMap: PlayerMap, playerCount: Int, leftPlayerCount: Int, rightPlayerCount: Int): Unit = {
    playerMap.playerByName(currPlayerName).get.itemCount mustBe playerCount
    playerMap.playerByName(leftPlayerName).get.itemCount mustBe leftPlayerCount
    playerMap.playerByName(rightPlayerName).get.itemCount mustBe rightPlayerCount
  }
}
