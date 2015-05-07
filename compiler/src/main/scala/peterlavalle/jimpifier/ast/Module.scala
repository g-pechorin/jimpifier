package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.Visibility.TVisibility
import peterlavalle.jimpifier.ast.tra.{TFinal, TNamed, TTyped, TVisible}

case class Module(
	                 visibility: TVisibility,
	                 isFinal: Boolean,
	                 isEnum: Boolean,
	                 name: String,
	                 parent: String,
	                 interfaces: List[String],
	                 fields: List[Field],
	                 methods: List[Method])
	extends TVisible with TNamed with TFinal {

	
}