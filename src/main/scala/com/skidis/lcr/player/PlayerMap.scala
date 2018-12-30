package com.skidis.lcr.player

import com.skidis.lcr.Direction.Direction

trait PlayerMap {
  def playerByName(playerName: String): Option[Player]
  def playerLoses(player: Player): PlayerMap
  def playerGains(player: Player): PlayerMap
  def playerLosesTo(player: Player, direction: Direction): PlayerMap
}
