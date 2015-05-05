package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

object Monitors {

	trait TMonitor extends TSSA {
		override val result: TLValue = null
	}

	case class Enter(value: TRValue) extends TMonitor

	case class Exit(value: TRValue) extends TMonitor

}
