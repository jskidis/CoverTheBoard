package com.skidis

package object dice {
  trait DieSide

  trait Die[D <: DieSide] {
    def sides: Seq[D]
  }
}
