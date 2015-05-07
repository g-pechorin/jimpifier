package peterlavalle.jimpifier.ast.rva

import org.antlr.v4.runtime.tree.TerminalNode
import peterlavalle.jimpifier.ast.tra.TRValue

object InfixOperations {

	sealed trait TInfixOperation extends TRValue {
		val l: TRValue
		val r: TRValue

		// HACK ; this really should be in the cook, not here in the model
		val str: String


		override lazy val tType = ???
	}

	// ... but then we couldn't do this!
	def unapply(rv: TRValue) =
		rv match {
			case infix: TInfixOperation =>
				Option((infix.l, infix.str, infix.r))
			case _ => None
		}

	case class InfixAdd(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "+"
	}

	case class InfixCMP(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "cmp"
	}

	case class InfixCMPG(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "cmpg"
	}

	case class InfixCMPL(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "cmpl"
	}

	case class InfixDivide(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "/"
	}

	case class InfixModulus(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "%"
	}

	case class InfixMultiply(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "*"
	}

	case class InfixSubtract(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "-"
	}

	case class InfixBitRight(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = ">>"
	}

	case class InfixBitLeft(l: TRValue, r: TRValue) extends TInfixOperation {
		val str = "<<"
	}

	// TODO : HACK ; this really should be in the compiler, not here in the model
	def apply(l: TRValue, o: TerminalNode, r: TRValue) =
		(o.getText match {
			case "+" => InfixAdd
			case "/" => InfixDivide
			case "%" => InfixModulus
			case "*" => InfixMultiply
			case "-" => InfixSubtract

			case ">>" => InfixBitRight
			case "<<" => InfixBitLeft

			case "cmp" => InfixCMP
			case "cmpg" => InfixCMPG
			case "cmpl" => InfixCMPL

			case wat =>
				sys.error("Infix(" + wat + ")")
		})(l, r)
}
