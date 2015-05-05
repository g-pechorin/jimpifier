package peterlavalle.jimpifier.ast.tra

import peterlavalle.jimpifier.ast.Visibility.TVisibility

trait TVisible extends TIR {
	val visibility: TVisibility
}
