
package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.Register
import peterlavalle.jimpifier.ast.tra.TRValue

case class InvokeMethod(host: TRValue, name: String, args: List[TRValue]) extends TRValue {
	require(name.matches("[\\w\\$]+"))

	override lazy val tType = ???
}
