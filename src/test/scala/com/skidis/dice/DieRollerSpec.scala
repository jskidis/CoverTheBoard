package com.skidis.dice

import org.scalatest.{FunSpec, MustMatchers}

class DieRollerSpec extends FunSpec with MustMatchers {
  case class NumberSide(n: Int) extends DieSide

  class NumberDie(val sides: Seq[NumberSide]) extends Die[NumberSide]
  val numSides = 100
  val dieSides: Seq[NumberSide] = 1 to numSides map NumberSide
  val die: Die[NumberSide] = new NumberDie(dieSides)

  // Oh the joys of testing randomness. I could use property based testing, but it seems overkill here
  describe("Die Roller") {
    it("rolls a random side from die") {
      val thrower = new BasicDieRoller[NumberSide](dieSides)
      val (roll1, roll2) = (thrower.next, thrower.next)

      // What are chance that both rolls happen to be the first two numbers
      // In the case 1 in 10,000, so I'm ok with that kind of false positive odds
      (roll1, roll2) must not be (dieSides.head, dieSides.tail.head)
    }

    it("returns all sides if thrown enough") {
      val thrower = new BasicDieRoller[NumberSide](dieSides)
      val rolls = 1 to numSides * 20 map { _ => thrower.next }

      // Hopefully if you throw a 100 sided-die 20,000 times, each number will come up at least once
      rolls.groupBy(identity).keys must contain theSameElementsAs dieSides

    }
  }
}
