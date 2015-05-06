package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.rva.NewArray
import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

case class Assign(result: TLValue, value: TRValue) extends TSSA {
	value match {
		case NewArray(eType, _) =>
			require(result.tType == (eType + "[]"), "Result type of `%s` is not correct fo eType `%s`".format(result.tType, eType))
		case _ =>
			;
	}
}
