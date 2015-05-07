package peterlavalle.jimpifier.compile

import java.io.ByteArrayInputStream

import junit.framework.Assert._
import peterlavalle.jimpifier.ast.Register
import peterlavalle.jimpifier.ast.lva.Indexor
import peterlavalle.jimpifier.ast.typ.{ArrayOf, ClassType, Primitive}


class GLTronSyntaxTest extends GLTronParseTest {

	// "$r2[$i0] = 5"

	def testArrayLVal(): Unit = {
		assertEquals(
			Indexor(Register("$r2", ArrayOf(ClassType("java/lang/String"))), Register("$i0", Primitive.PInt)),
			Syntax.lv(
				List(),
				_.getText match {
					case "$r2" => Register("$r2", ArrayOf(ClassType("java/lang/String")))
					case "$i0" => Register("$i0", Primitive.PInt)
				},
				parser(
					new ByteArrayInputStream(
						"$r2[$i0]".getBytes
					)).lvalue()
			)
		)
	}

	override def apply(source: String): Unit = {
		Syntax(
			parser(source).module()
		)
	}
}
