package com.skidis.lcr

import com.skidis.lcr.Direction._

object LcrRollProcessor {
  def processRoll(player: Player, playerMap: PlayerMap, roll: LcrDieSide): PlayerMap = roll match {
    case _@NoActionDieSide => playerMap
    case DirectionDieSide(direction) if direction == Center => playerLoses(player, playerMap)
    case DirectionDieSide(_) => playerMap
  }

  def playerLoses(player: Player, playerMap: PlayerMap): PlayerMap = {
    playerMap.updatePlayer(player.loseItem)
  }
}
