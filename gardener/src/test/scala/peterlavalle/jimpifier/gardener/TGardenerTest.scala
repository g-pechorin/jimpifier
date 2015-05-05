package peterlavalle.jimpifier.gardener

import jimp.TJimpTests
import junit.framework.TestCase
import peterlavalle.jimpifier.compile.{Syntax, TAntlr4Parser}


trait TGardenerTest extends TestCase with TJimpTests with TAntlr4Parser {
	val streamer: TGardener

	override def apply(sourceName: String): Unit = {
		streamer(
			Syntax(parser(sourceName).module())
		)
	}
}
