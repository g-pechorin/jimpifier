package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.Visibility.TVisibility
import peterlavalle.jimpifier.ast.tra.{TNamed, TStatic, TTyped, TVisible}
import peterlavalle.jimpifier.ast.typ.TType

case class Method(visibility: TVisibility, isStatic: Boolean, name: String, args: List[TType], tType: TType, registers: List[Register], blocks: List[Block], handlers: List[Handler]) extends TNamed with TTyped with TStatic with TVisible {

	if (blocks.nonEmpty) {
		require(null == blocks.head.name)
		blocks.tail.foreach(b => require(null != b.name))
	}
}
