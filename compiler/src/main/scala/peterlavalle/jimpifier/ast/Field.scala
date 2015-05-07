package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra._

case class Field(visibility: Visibility.TVisibility, isStatic: Boolean, isFinal: Boolean, isEnum: Boolean, name: String, tType: String)
	extends TVisible
	with TNamed
	with TTyped
	with TStatic
	with TFinal {

}
