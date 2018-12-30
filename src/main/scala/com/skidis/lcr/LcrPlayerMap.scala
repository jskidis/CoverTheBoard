package com.skidis.lcr

case class LcrPlayerMap(players: Seq[Player]) extends PlayerMap {
  def playerByName(name: String): Option[Player] = players find (_.playerName == name)

  def updatePlayer(updatedPlayer: Player): PlayerMap = {
    val newPlayerList = players map { p =>
      if (p.playerName != updatedPlayer.playerName) p else updatedPlayer
    }
    copy(newPlayerList)
  }
}
