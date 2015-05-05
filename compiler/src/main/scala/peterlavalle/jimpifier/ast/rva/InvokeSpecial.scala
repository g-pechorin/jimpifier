package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.Register
import peterlavalle.jimpifier.ast.tra.TRValue

case class InvokeSpecial(owner: Register, name: String, args: List[TRValue]) extends TRValue {

	override lazy val tType: String = ???
}
