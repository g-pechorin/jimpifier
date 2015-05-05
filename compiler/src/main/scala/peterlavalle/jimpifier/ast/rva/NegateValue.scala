package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue

case class NegateValue(value: TRValue) extends TRValue {
	override lazy val tType: String = ???
}
