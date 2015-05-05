package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra.TTyped
import peterlavalle.jimpifier.ast.typ.TType

case class Handler(tType: TType, from: String, to: String, wit: String) extends TTyped {

	require(from != to && to != wit && wit != from)
}
