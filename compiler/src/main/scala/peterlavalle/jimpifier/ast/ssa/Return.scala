package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

case class Return(resurn: TRValue) extends TSSA {
	val result: TLValue = null
}
