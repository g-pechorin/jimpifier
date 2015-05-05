package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

case class Raise(exception: TRValue) extends TSSA {
	override val result: TLValue = null
}
