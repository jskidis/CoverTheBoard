package com.skidis.lcr

import com.skidis.lcr.Direction._
import com.skidis.lcr.player.{Player, PlayerMap}

object LcrRollProcessor {
  def processRolls(player: Player, rolls: Seq[LcrDieSide]): Seq[Outcome] = {

    def recursiveProcessing(processedOutcomes: Seq[Outcome], remainingRolls: Seq[LcrDieSide]): Seq[Outcome] = {
      if (remainingRolls.isEmpty) processedOutcomes
      else {
        val updatedOutcomes = remainingRolls.head match {
          case _@NoActionDieSide => processedOutcomes
          case DirectionDieSide(direction) if direction == Center =>
            processedOutcomes :+ PlayerLosesToPotOutcome(player)
          case DirectionDieSide(direction) =>
            processedOutcomes :+ PlayerLosesToOtherOutcome(player, direction)
        }
        recursiveProcessing(updatedOutcomes, remainingRolls.tail)
      }
    }
    recursiveProcessing(processedOutcomes = Nil, remainingRolls = rolls)
  }
}


