package peterlavalle.jimpifier.ast.ssa

import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

object Branch {

	sealed trait TBranch {
		val label: String
	}

	sealed trait TConditionalBranch extends TBranch with TSSA {
		val l: TRValue
		val o: String
		val r: TRValue
		override val result: TLValue = null
	}

	def unapply(obj: AnyRef) =
		obj match {
			case con: TConditionalBranch =>
				Option(con.l, con.o, con.r, con.label)
			case _ =>
				None
		}

	case class IsEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch {
		val o = "=="
	}

	case class NotEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch {
		val o = "!="
	}

	case class GreaterEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch {
		val o = ">="
	}

	case class GreaterThan(l: TRValue, r: TRValue, label: String) extends TConditionalBranch {
		val o = ">"
	}

	case class LessEqual(l: TRValue, r: TRValue, label: String) extends TConditionalBranch {
		val o = "<="
	}

	case class LessThan(l: TRValue, r: TRValue, label: String) extends TConditionalBranch {
		val o = "<"
	}

	case class GoTo(label: String) extends TBranch with TSSA {
		override val result: TLValue = null
	}


	sealed trait TSwitchBranch extends TBranch

	case class Case(i: Int, label: String) extends TSwitchBranch

	case class Default(label: String) extends TSwitchBranch

	case class Switch(value: TRValue, branches: List[TSwitchBranch]) extends TSSA {
		override val result: TLValue = null
	}

}
