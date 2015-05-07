package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra._
import peterlavalle.jimpifier.ast.typ.TType

case class Field(visibility: Visibility.TVisibility, isStatic: Boolean, isFinal: Boolean, isEnum: Boolean, name: String, tType: TType)
	extends TVisible
	with TNamed
	with TTyped
	with TStatic
	with TFinal {

}
