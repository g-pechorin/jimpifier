package jimpifier.sable

import java.io.InputStream

import jimple.TJimpleTest
import junit.framework.TestCase

class SableSyntaxTest extends TestCase with TJimpleTest {
	override def apply(sourceName: String) {
		val inputStream: InputStream = classOf[TJimpleTest].getResourceAsStream(sourceName)
		require(null != inputStream)
		SableSyntax(
			inputStream
		)
	}
}