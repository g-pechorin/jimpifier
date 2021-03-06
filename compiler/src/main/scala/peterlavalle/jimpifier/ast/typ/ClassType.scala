package peterlavalle.jimpifier.ast.typ

case class ClassType(name: String) extends TType {
	require(name.matches("[\\w$]+(/[\\w$]+)*"), "The name `" + name + "` is not spikey")
}
