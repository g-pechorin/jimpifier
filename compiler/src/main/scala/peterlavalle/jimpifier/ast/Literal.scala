package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.compile.JimpParser._
import peterlavalle.jimpifier.ast.tra.TRValue

case object Literal {

	sealed trait TLiteral extends TRValue

	sealed trait TSpecial extends TLiteral

	case class LiteralInt(i: Int) extends TLiteral {
		override val tType: String = "int"
	}

	case class LiteralFloat(f: Float) extends TLiteral {
		override val tType: String = "float"
	}

	case class LiteralDouble(d: Double) extends TLiteral {
		override val tType: String = "double"
	}

	case class LiteralLong(l: Long) extends TLiteral {
		override val tType: String = "long"
	}

	case class LiteralString(s: String) extends TLiteral {
		override val tType: String = "java.lang.String"
	}

	case class LiteralClass(t: String) extends TLiteral {
		override val tType: String = "java.lang.Class"
	}

	case object This extends TSpecial {
		override lazy val tType: String = ???
	}

	case object Null extends TLiteral {
		override lazy val tType: String = ???
	}

	case object CaughtException extends TSpecial {
		override lazy val tType: String = ???
	}

	case class Parameter(i: Int) extends TSpecial {
		override lazy val tType: String = ???
	}

}
