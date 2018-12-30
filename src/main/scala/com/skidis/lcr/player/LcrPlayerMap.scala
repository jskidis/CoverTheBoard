package com.skidis.lcr.player

import com.skidis.lcr.Direction.Direction

case class LcrPlayerMap(players: Seq[Player]) extends PlayerMap {
  def playerByName(name: String): Option[Player] = players find (_.playerName == name)

  def playerLoses(player: Player): PlayerMap = updatePlayer(player.loseItem)
  def playerGains(player: Player): PlayerMap = updatePlayer(player.gainItem)

  def playerLosesTo(player: Player, direction: Direction): PlayerMap = {
    val playerNeighbor = PlayerNeighbor(players)(player.playerName, direction)
    val mapAfterLoss = updatePlayer(player.loseItem)
    val mapAfterGain = playerNeighbor map (n => mapAfterLoss.playerGains(n))
    mapAfterGain.getOrElse(mapAfterLoss)
  }

  private def updatePlayer(updatedPlayer: Player): PlayerMap = {
    val newPlayerList = players map { p =>
      if (p.playerName != updatedPlayer.playerName) p
      else updatedPlayer
    }
    copy(newPlayerList)
  }
}
