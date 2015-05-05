package peterlavalle.jimpifier.ast.tra

import peterlavalle.jimpifier.ast.typ.TType

trait TTyped extends TIR {
	val tType: TType
}
