package com.skidis.lcr

case class LcrPlayer(playerName: String, itemCount: Int = 0) extends Player {
  override def loseItem: Player = copy(itemCount = itemCount -1)
  override def gainItem: Player = copy(itemCount = itemCount +1)
}
