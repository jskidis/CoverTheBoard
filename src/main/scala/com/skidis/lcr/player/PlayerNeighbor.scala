package com.skidis.lcr.player

import com.skidis.lcr.Direction.{Center, Direction, Left, Right}

object PlayerNeighbor{
  def apply(players: Seq[Player])(playerName: String, direction: Direction): Option[Player] = {
    val player = players find (_.playerName == playerName)
    (player, direction) match {
      case (Some(p), Left) => Option(players((players.indexOf(p) + 1) % players.size))
      case (Some(p), Right) => Option(players((players.indexOf(p) + players.size -1) % players.size))
      case (Some(p), Center) => Option(players(players.indexOf(p) % players.size))
      case _ => None
    }
  }
}
