package peterlavalle.jimpifier.ast.lva

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue}

case class Indexor(array: TLValue, index: TRValue) extends TLValue {

	override lazy val tType: String = {
		require(array.tType.endsWith("[]"))
		array.tType.reverse.substring(2).reverse
	}
}
