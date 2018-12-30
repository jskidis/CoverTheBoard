package com.skidis.lcr.player

trait Player {
  def playerName: String
  def itemCount: Int

  def loseItem: Player
  def gainItem: Player
}
