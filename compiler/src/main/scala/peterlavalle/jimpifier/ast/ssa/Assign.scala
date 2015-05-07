package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.rva.NewArray
import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}
import peterlavalle.jimpifier.ast.typ.ArrayOf

case class Assign(result: TLValue, value: TRValue) extends TSSA {
	value match {
		case NewArray(eType, _) =>
			require(result.tType == ArrayOf(eType ), "Result type of `%s` is not correct for eType `%s`".format(result.tType, eType))
		case _ =>
			;
	}
}
