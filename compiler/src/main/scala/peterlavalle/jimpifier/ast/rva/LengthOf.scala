package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue
import peterlavalle.jimpifier.ast.typ.ArrayOf

case class LengthOf(array: TRValue) extends TRValue {
	override lazy val tType = ArrayOf(array.tType)
}
