package peterlavalle.jimpifier.ast.lva

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue}
import peterlavalle.jimpifier.ast.typ.ArrayOf

case class Indexor(array: TLValue, index: TRValue) extends TLValue {

	override val tType = array.tType match {
		case ArrayOf(eType) => eType
	}
}
