package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.Visibility.TVisibility
import peterlavalle.jimpifier.ast.tra.{TFinal, TVisible}
import peterlavalle.jimpifier.ast.typ.TType

case class Module(
	                 visibility: TVisibility,
	                 isFinal: Boolean,
	                 isEnum: Boolean,
	                 name: TType,
	                 parent: TType,
	                 interfaces: List[TType],
	                 fields: List[Field],
	                 methods: List[Method]) extends TVisible with TFinal {
}