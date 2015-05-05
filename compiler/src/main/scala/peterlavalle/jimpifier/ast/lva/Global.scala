package peterlavalle.jimpifier.ast.lva

import peterlavalle.jimpifier.ast.tra.TLValue

case class Global(container: List[String], name: String) extends TLValue {

	(name :: container).foreach(n => require(n.matches("[\\w\\$]+")))

	override lazy val tType: String = ???
}
