package com.skidis.lcr

import com.skidis.dice.{Die, DieSide}
import com.skidis.lcr.Direction.Direction
import com.skidis.lcr.Direction._

sealed trait LcrDieSide extends DieSide

case class DirectionDieSide(direction: Direction) extends LcrDieSide
object NoActionDieSide extends LcrDieSide

object LcrDie extends Die[LcrDieSide] {
  override def sides: Seq[LcrDieSide] = Seq(
    DirectionDieSide(Left), DirectionDieSide(Center), DirectionDieSide(Right),
    NoActionDieSide, NoActionDieSide, NoActionDieSide
  )
}

