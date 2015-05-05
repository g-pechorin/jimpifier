package peterlavalle.jimpifier.compile

import java.io.ByteArrayInputStream

import junit.framework.Assert._
import peterlavalle.jimpifier.ast.Register
import peterlavalle.jimpifier.ast.lva.Indexor


class GLTronSyntaxTest extends GLTronParseTest {

	// "$r2[$i0] = 5"

	def testArrayLVal(): Unit = {
		assertEquals(
			Indexor(Register("$r2", "java.lang.String[]"), Register("$i0", "int")),
			Syntax.lv(
				_.getText match {
					case "$r2" => Register("$r2", "java.lang.String[]")
					case "$i0" => Register("$i0", "int")
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
