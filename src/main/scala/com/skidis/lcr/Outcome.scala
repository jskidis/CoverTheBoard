package com.skidis.lcr

import com.skidis.lcr.Direction.Direction
import com.skidis.lcr.player.Player

trait Outcome

case class PlayerLosesToPotOutcome(player: Player) extends Outcome
case class PlayerLosesToOtherOutcome(player: Player, direction: Direction) extends Outcome
