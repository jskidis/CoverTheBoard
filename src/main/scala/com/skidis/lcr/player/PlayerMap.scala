package com.skidis.lcr.player

import com.skidis.lcr.Direction.Direction

trait PlayerMap {
  def playerByName(playerName: String): Option[Player]
  def updatePlayer(player: Player): PlayerMap
  def neighbor(player: Player, direction: Direction): Option[Player]
}
