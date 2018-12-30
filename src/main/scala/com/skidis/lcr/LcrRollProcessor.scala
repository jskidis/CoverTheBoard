package com.skidis.lcr

import com.skidis.lcr.Direction._

object LcrRollProcessor {
  def processRoll(player: Player, playerMap: PlayerMap, roll: LcrDieSide): PlayerMap = {
    def playerLoses(): PlayerMap = {
      playerMap.updatePlayer(player.loseItem)
    }

    def playerLosesTo(direction: Direction): PlayerMap = {
      val neighbor = playerMap.neighbor(player, direction)
      val updatedMap = playerMap.updatePlayer(player.loseItem)
      (neighbor map { p => updatedMap.updatePlayer(p.gainItem) }).getOrElse(updatedMap)
    }

    roll match {
      case _@NoActionDieSide => playerMap
      case DirectionDieSide(direction) if direction == Center => playerLoses()
      case DirectionDieSide(direction) => playerLosesTo(direction)
    }
  }
}
