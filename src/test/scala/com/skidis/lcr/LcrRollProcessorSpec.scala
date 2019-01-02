package com.skidis.lcr

import com.skidis.lcr.Direction._
import com.skidis.lcr.player.{LcrPlayer, Player, PlayerMap}
import org.scalatest.{FunSpec, MustMatchers}

class LcrRollProcessorSpec extends FunSpec with MustMatchers {

  val somePlayer = LcrPlayer("Some Player")

  describe("Lcr Turn") {
    it("if no action is rolled, no outcomes should be returned") {
      val rolls = Seq(NoActionDieSide)
      val outcomes = LcrRollProcessor.processRolls(somePlayer, rolls)
      outcomes mustBe empty
    }

    it("when Center is rolled, player should lose (to pot)") {
      val rolls = Seq(DirectionDieSide(Center))
      val outcomes = LcrRollProcessor.processRolls(somePlayer, rolls)
      outcomes must have size 1
      outcomes.head mustBe PlayerLosesToPotOutcome(somePlayer)
    }

    it("when Right or Left is rolled, player should lost to player in that direction") {
      val rolls = Seq(DirectionDieSide(Right))
      val outcomes = LcrRollProcessor.processRolls(somePlayer, rolls)
      outcomes must have size 1
      outcomes.head mustBe PlayerLosesToOtherOutcome(somePlayer, Right)
    }

    it("processes multiple rolls") {
      val rolls = Seq(DirectionDieSide(Right), DirectionDieSide(Right), NoActionDieSide,
        DirectionDieSide(Center), DirectionDieSide(Left), NoActionDieSide)

      val expectedOutcomes = Seq(
        PlayerLosesToOtherOutcome(somePlayer, Right),
        PlayerLosesToOtherOutcome(somePlayer, Right),
        PlayerLosesToPotOutcome(somePlayer),
        PlayerLosesToOtherOutcome(somePlayer, Left))

      val outcomes = LcrRollProcessor.processRolls(somePlayer, rolls)
      outcomes mustBe expectedOutcomes
    }
  }
}


