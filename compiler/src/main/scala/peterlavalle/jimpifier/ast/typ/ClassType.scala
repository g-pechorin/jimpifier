package peterlavalle.jimpifier.ast.typ

case class ClassType(name: String) {
	require(name.matches("[\\w$]+(/[\\w$]+)*"))
}
