package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.rva.NewArray
import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

case class Assign(result: TLValue, value: TRValue) extends TSSA {
	value match {
		case NewArray(tType, _) =>
			require(result.tType == (tType + "[]"))
		case _ =>
			;
	}
}
