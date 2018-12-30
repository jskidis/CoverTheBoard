package com.skidis.lcr

trait Player {
  def playerName: String
  def itemCount: Int

  def loseItem: Player
  def gainItem: Player
}

