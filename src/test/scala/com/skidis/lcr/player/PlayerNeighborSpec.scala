package com.skidis.lcr.player

import com.skidis.lcr.Direction._
import org.scalatest.{FunSpec, MustMatchers}

class PlayerNeighborSpec extends FunSpec with MustMatchers {
  val currPlayer = LcrPlayer("Current Player", 5)
  val leftPlayer = LcrPlayer("Left Player", 3)
  val rightPlayer = LcrPlayer("Right Player", 1)
  val players = Seq(currPlayer, leftPlayer, rightPlayer)

  describe("Player Neighbor") {
    it("returns the proper neighbor to the right and left of a player") {
      PlayerNeighbor(players)(currPlayer.playerName, Left) mustBe Some(leftPlayer)
      PlayerNeighbor(players)(currPlayer.playerName, Right) mustBe Some(rightPlayer)

      PlayerNeighbor(players)(rightPlayer.playerName, Left) mustBe Some(currPlayer)
      PlayerNeighbor(players)(rightPlayer.playerName, Right) mustBe Some(leftPlayer)

      PlayerNeighbor(players)(leftPlayer.playerName, Left) mustBe Some(rightPlayer)
      PlayerNeighbor(players)(leftPlayer.playerName, Right) mustBe Some(currPlayer)
    }

    it("returns none if player is not in map") {
      val playerNotInMap = LcrPlayer("Some Other Player")
      PlayerNeighbor(players)("Some Random Name", Left) mustBe None
      PlayerNeighbor(players)("Some Random Name", Right) mustBe None
    }

    it("for grins (maybe useful later, it returns the player if the direction is center") {
      PlayerNeighbor(players)(currPlayer.playerName, Center) mustBe Some(currPlayer)
      PlayerNeighbor(players)(leftPlayer.playerName, Center) mustBe Some(leftPlayer)
      PlayerNeighbor(players)(rightPlayer.playerName, Center) mustBe Some(rightPlayer)
    }
  }
}
