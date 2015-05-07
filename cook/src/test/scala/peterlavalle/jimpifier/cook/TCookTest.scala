package peterlavalle.jimpifier.cook

import java.io.{StringWriter, Writer}

import jimp.TJimpTests
import junit.framework.Assert._
import junit.framework.TestCase
import org.easymock.EasyMock
import peterlavalle.jimpifier.compile.{Syntax, TAntlr4Parser}

import scala.io.Source

trait TCookTest extends TestCase with TJimpTests with TAntlr4Parser {

	val cook: TCook

	override def apply(sourceName: String): Unit = {

		val stringWriter = new StringWriter()

		val mockOutput = EasyMock.createMock(classOf[String => Writer])

		EasyMock.expect(mockOutput(sourceName)).andReturn(stringWriter).once()

		EasyMock.replay(mockOutput)
		cook.cook(mockOutput)(Syntax(parser(sourceName).module()))

		EasyMock.verify(mockOutput)

		assertEquals(
			Source.fromInputStream(classOf[TJimpTests].getResourceAsStream(sourceName)).mkString.replaceAll("\r?\n", "\n"),
			stringWriter.toString.replaceAll("\r?\n", "\n")
		)
	}
}
