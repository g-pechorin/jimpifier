package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

object Branch {

	sealed trait TConditionalBranch extends TSSA {
		override val result: TLValue = null
		val label: String
	}

	case class IsEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch
	case class NotEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch
	case class GreaterEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch
	case class GreaterThan(l: TRValue, r: TRValue, label: String) extends TConditionalBranch
	case class LessEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch
	case class LessThan(l: TRValue, r: TRValue, label: String) extends TConditionalBranch

	case class GoTo(label: String) extends TConditionalBranch


	sealed trait TSwitchBranch

	case class Case(i: Int, label: String) extends TSwitchBranch

	case class Default(label: String) extends TSwitchBranch

	case class Switch(value: TRValue, branches: List[TSwitchBranch]) extends TSSA {
		override val result: TLValue = null
	}

}
