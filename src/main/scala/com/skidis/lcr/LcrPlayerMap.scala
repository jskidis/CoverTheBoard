package com.skidis.lcr

import com.skidis.lcr.Direction.Direction
import com.skidis.lcr.Direction._

case class LcrPlayerMap(players: Seq[Player]) extends PlayerMap {
  def playerByName(name: String): Option[Player] = players find (_.playerName == name)

  def updatePlayer(updatedPlayer: Player): PlayerMap = {
    val newPlayerList = players map { p =>
      if (p.playerName != updatedPlayer.playerName) p else updatedPlayer
    }
    copy(newPlayerList)
  }

  override def neighbor(player: Player, direction: Direction): Option[Player] = {
    (playerByName(player.playerName), direction) match {
      case (Some(p), Left) => Option(players((players.indexOf(p) + 1) % players.size))
      case (Some(p), Right) => Option(players((players.indexOf(p) + players.size -1) % players.size))
      case (Some(p), Center) => Option(players(players.indexOf(p) % players.size))
      case _ => None
    }
  }
}
