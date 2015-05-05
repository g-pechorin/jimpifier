package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra.{TNamed, TSSA}

case class Block(name: String, ssa: List[TSSA]) extends TNamed {

}
