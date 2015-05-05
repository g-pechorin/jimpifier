package peterlavalle.jimpifier.compile

import jimp.TJimpTests
import junit.framework.TestCase

class GLTronParseTest extends TestCase with TJimpTests with TAntlr4Parser {


	override def apply(source: String): Unit = {
		parser(source).module()
	}
}
