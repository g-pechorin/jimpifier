package peterlavalle.jimpifier.ast.rva

import org.antlr.v4.runtime.tree.TerminalNode
import peterlavalle.jimpifier.ast.tra.TRValue

object InfixOperations {

	sealed trait TInfixOperation extends TRValue {
		override lazy val tType: String = ???
	}

	case class InfixAdd(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixCMP(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixCMPG(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixCMPL(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixDivide(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixModulus(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixMultiply(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixSubtract(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixBitRight(l: TRValue, r: TRValue) extends TInfixOperation

	case class InfixBitLeft(l: TRValue, r: TRValue) extends TInfixOperation

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
