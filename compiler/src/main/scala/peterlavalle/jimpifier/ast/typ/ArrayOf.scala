package peterlavalle.jimpifier.ast.typ

import peterlavalle.jimpifier.ast.tra.TTyped

case class ArrayOf(tType: TType) extends TType with TTyped {
	override val name: String = tType + "[]"
}
