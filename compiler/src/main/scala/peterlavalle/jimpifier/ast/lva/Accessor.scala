package peterlavalle.jimpifier.ast.lva

import peterlavalle.jimpifier.ast.tra.TLValue

case class Accessor(register: TLValue, name: String) extends TLValue {
	require(register.getClass != classOf[Global])
	override lazy val tType: String = ???
}
