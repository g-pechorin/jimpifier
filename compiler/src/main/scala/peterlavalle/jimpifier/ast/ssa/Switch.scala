package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

object Switch {

	sealed trait TBranch

	case class Case(i: Int, label: String) extends TBranch

	case class Default(label: String) extends TBranch

	case class Statement(value: TRValue, branches: List[TBranch]) extends TSSA {
		override val result: TLValue = null
	}
}
