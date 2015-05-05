package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.Visibility.TVisibility
import peterlavalle.jimpifier.ast.tra.{TNamed, TStatic, TTyped, TVisible}

case class Method(visibility: TVisibility, isStatic: Boolean, name: String, args: List[String], tType: String, registers: List[Register], blocks: List[Block], handlers: List[Handler]) extends TNamed with TTyped with TStatic with TVisible {

	if (blocks.nonEmpty) {
		require(null == blocks.head.name)
		blocks.tail.foreach(b => require(null != b.name))
	}
}
