package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra.TRValue
import peterlavalle.jimpifier.ast.typ.{ClassType, Primitive, TType}

case object Literal {

	sealed trait TLiteral extends TRValue

	sealed trait TSpecial extends TLiteral

	case class LiteralInt(i: Int) extends TLiteral {
		override val tType = Primitive.PInt
	}

	case class LiteralFloat(f: Float) extends TLiteral {
		override val tType = Primitive.PFloat
	}

	case class LiteralDouble(d: Double) extends TLiteral {
		override val tType = Primitive.PDouble
	}

	case class LiteralLong(l: Long) extends TLiteral {
		override val tType = Primitive.PLong
	}

	case class LiteralString(s: String) extends TLiteral {
		override val tType = ClassType("java/lang/String")
	}

	case class LiteralClass(t: String) extends TLiteral {
		override val tType = ClassType("java/lang/Class")
	}

	case object This extends TSpecial {
		override lazy val tType = ???
	}

	case object Null extends TLiteral {
		override lazy val tType = null
	}

	case object CaughtException extends TSpecial {
		override lazy val tType = ???
	}

	case class Parameter(i: Int, tType: TType) extends TLiteral with TSpecial

}
