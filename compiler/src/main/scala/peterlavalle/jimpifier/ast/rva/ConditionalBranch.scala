package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

object ConditionalBranch {

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

}
