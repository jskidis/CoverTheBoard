package com.skidis.lcr

trait PlayerMap {
  def playerByName(playerName: String): Option[Player]
  def updatePlayer(player: Player): PlayerMap
}


