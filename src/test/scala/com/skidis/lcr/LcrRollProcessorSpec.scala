package com.skidis.lcr

import com.skidis.lcr.Direction._
import com.skidis.lcr.player.{LcrPlayer, Player, PlayerMap}
import org.scalatest.{FunSpec, MustMatchers}

class LcrRollProcessorSpec extends FunSpec with MustMatchers {

  val somePlayer = LcrPlayer("Some Player")

  class MockMap(val playerThatLost: Option[Player] = None,
    val directionLostTo: Option[Direction] = None, val numActions: Int = 0) extends PlayerMap {

    override def playerByName(playerName: String): Option[Player] = Option(somePlayer)

    override def playerLoses(player: Player): PlayerMap = {
      new MockMap(Some(player), None, numActions +1)
    }
    override def playerLosesTo(player: Player, direction: Direction): PlayerMap = {
      new MockMap(Some(player), Some(direction), numActions +1)
    }

    override def playerGains(player: Player): PlayerMap = this
  }

  describe("Lcr Turn") {
    it("if no action is rolled, no action should be taken on player map") {
      val playerMap = new MockMap
      val player = somePlayer
      val rolls = Seq(NoActionDieSide)

      val returnedMap = castMapToMock(LcrRollProcessor.processRolls(player, playerMap, rolls))
      returnedMap.numActions mustBe 0
      returnedMap.playerThatLost mustBe None
      returnedMap.directionLostTo mustBe None
    }

    it("when Center is rolled, player should lose (to pot)") {
      val playerMap = new MockMap
      val player = somePlayer
      val rolls = Seq(DirectionDieSide(Center))

      val returnedMap = castMapToMock(LcrRollProcessor.processRolls(player, playerMap, rolls))
      returnedMap.playerThatLost mustBe Option(somePlayer)
      returnedMap.directionLostTo mustBe None
    }

    it("when Right or Left is rolled, player should lost to player in that direction") {
      val playerMap = new MockMap
      val player = somePlayer
      val rolls = Seq(DirectionDieSide(Right))

      val returnedMap = castMapToMock(LcrRollProcessor.processRolls(player, playerMap, rolls))
      returnedMap.playerThatLost mustBe Option(somePlayer)
      returnedMap.directionLostTo mustBe Option(Right)
    }

    it("processes multiple rolls") {
      val playerMap = new MockMap
      val player = somePlayer
      val rolls = Seq(DirectionDieSide(Right), DirectionDieSide(Right), NoActionDieSide,
        DirectionDieSide(Center), DirectionDieSide(Left), NoActionDieSide)

      val returnedMap = castMapToMock(LcrRollProcessor.processRolls(player, playerMap, rolls))
      returnedMap.numActions mustBe 4 // don't include no action rolls as they do not impact map
      returnedMap.playerThatLost mustBe Option(somePlayer) // Mock retains only the last action which should have a Left
      returnedMap.directionLostTo mustBe Option(Left)
    }

    def castMapToMock(mockMap: PlayerMap): MockMap = mockMap match { case m: MockMap => m }
  }
}


