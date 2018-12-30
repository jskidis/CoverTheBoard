package com.skidis.lcr

import com.skidis.lcr.Direction._
import com.skidis.lcr.player.{Player, PlayerMap}

object LcrRollProcessor {
  def processRolls(player: Player, playerMap: PlayerMap, rolls: Seq[LcrDieSide]): PlayerMap = {

    def recursiveProcessing(currPlayer: Player, accumMap: PlayerMap, remainingRolls: Seq[LcrDieSide]): PlayerMap = {
      if (remainingRolls.isEmpty) accumMap
      else {
        val newMap = remainingRolls.head match {
          case _@NoActionDieSide => accumMap
          case DirectionDieSide(direction) if direction == Center => accumMap.playerLoses(currPlayer)
          case DirectionDieSide(direction) => accumMap.playerLosesTo(currPlayer, direction)
        }

        val updatedPlayer =  newMap.playerByName(currPlayer.playerName).get
        recursiveProcessing(updatedPlayer, newMap, remainingRolls.tail)
      }
    }

    recursiveProcessing(player, playerMap, rolls)
  }
}


