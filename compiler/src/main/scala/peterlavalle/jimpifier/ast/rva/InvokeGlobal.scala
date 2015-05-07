package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue

case class InvokeGlobal(host: String, name: String, args: List[TRValue]) extends TRValue {
	require(name.matches("[\\w\\$]+"))
	override lazy val tType = ???
}
