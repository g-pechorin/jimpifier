package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue

case class LengthOf(array: TRValue) extends TRValue {
	override val tType: String = array.tType + "[]"
}
