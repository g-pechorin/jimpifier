package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra.TTyped

case class Handler(tType: String, from: String, to: String, wit: String) extends TTyped {
	require(tType.matches("[\\w\\$]+(\\.[\\w\\$]+)*"))
	require(from != to && to != wit && wit != from)
}
