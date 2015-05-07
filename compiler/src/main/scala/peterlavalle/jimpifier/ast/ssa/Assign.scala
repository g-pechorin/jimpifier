package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.rva.NewArray
import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}
import peterlavalle.jimpifier.ast.typ.ArrayOf

case class Assign(result: TLValue, value: TRValue) extends TSSA {
	value match {
		case NewArray(aType, _) =>
			require(result.tType == aType , "Result type of `%s` is not correct for eType `%s`".format(result.tType, aType))
		case _ =>
			;
	}
}
