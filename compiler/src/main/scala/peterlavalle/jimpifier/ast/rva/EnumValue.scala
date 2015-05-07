package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.Literal.LiteralClass
import peterlavalle.jimpifier.ast.tra.TRValue

case class EnumValue(eType: LiteralClass, value: TRValue) extends TRValue {
	override lazy val tType = ???
}
