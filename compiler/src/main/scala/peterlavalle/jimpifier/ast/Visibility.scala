package peterlavalle.jimpifier.ast

import org.antlr.v4.runtime.tree.TerminalNode

object Visibility {

	sealed trait TVisibility

	case object Public extends TVisibility

	case object Private extends TVisibility

	case object Protected extends TVisibility

}
