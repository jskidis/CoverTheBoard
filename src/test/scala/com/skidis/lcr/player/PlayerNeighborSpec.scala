package com.skidis.lcr.player

import com.skidis.lcr.Direction._
import org.scalatest.{FunSpec, MustMatchers}

class PlayerNeighborSpec extends FunSpec with MustMatchers {
  val playerOne = LcrPlayer("Player 1")
  val playerTwo = LcrPlayer("Player 2")
  val playerThree = LcrPlayer("Player 3")
  val playerFour = LcrPlayer("Player 4")

  describe("Player Neighbor") {
    it("returns the proper neighbor to the right and left of a player") {
      val players = Seq(playerOne, playerTwo, playerThree, playerFour)

      PlayerNeighbor(players)(playerOne.playerName, Left) mustBe Some(playerTwo)
      PlayerNeighbor(players)(playerOne.playerName, Right) mustBe Some(playerFour)

      PlayerNeighbor(players)(playerTwo.playerName, Left) mustBe Some(playerThree)
      PlayerNeighbor(players)(playerTwo.playerName, Right) mustBe Some(playerOne)

      PlayerNeighbor(players)(playerThree.playerName, Left) mustBe Some(playerFour)
      PlayerNeighbor(players)(playerThree.playerName, Right) mustBe Some(playerTwo)

      PlayerNeighbor(players)(playerFour.playerName, Left) mustBe Some(playerOne)
      PlayerNeighbor(players)(playerFour.playerName, Right) mustBe Some(playerThree)
    }

    it("returns none if player is not in map") {
      val players = Seq(playerOne, playerTwo, playerThree)
      val playerNotInMap = LcrPlayer("Some Other Player")

      PlayerNeighbor(players)("Some Random Name", Left) mustBe None
      PlayerNeighbor(players)("Some Random Name", Right) mustBe None
    }

    it("handles only having two players") {
      val players = Seq(playerOne, playerTwo)

      PlayerNeighbor(players)(playerOne.playerName, Left) mustBe Some(playerTwo)
      PlayerNeighbor(players)(playerOne.playerName, Right) mustBe Some(playerTwo)

      PlayerNeighbor(players)(playerTwo.playerName, Left) mustBe Some(playerOne)
      PlayerNeighbor(players)(playerTwo.playerName, Right) mustBe Some(playerOne)
    }

    it("handles only having one player") {
      val players = Seq(playerOne)

      PlayerNeighbor(players)(playerOne.playerName, Left) mustBe Some(playerOne)
      PlayerNeighbor(players)(playerOne.playerName, Right) mustBe Some(playerOne)
    }

    it("for grins (maybe useful later, it returns the player if the direction is center") {
      val players = Seq(playerOne, playerTwo, playerThree)

      PlayerNeighbor(players)(playerOne.playerName, Center) mustBe Some(playerOne)
      PlayerNeighbor(players)(playerTwo.playerName, Center) mustBe Some(playerTwo)
      PlayerNeighbor(players)(playerThree.playerName, Center) mustBe Some(playerThree)
    }
  }
}
