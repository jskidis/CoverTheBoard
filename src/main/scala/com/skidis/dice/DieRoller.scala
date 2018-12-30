package com.skidis.dice

import scala.util.Random

trait DieRoller[D <: DieSide] {
  def next: D
}

case class BasicDieRoller[D <: DieSide](sides: Seq[D]) extends DieRoller[D] {
  def next: D = Random.shuffle(sides).head
}
