package peterlavalle.jimpifier.compile

import jimp.TJimpTests
import junit.framework.TestCase
import org.antlr.v4.runtime.{ANTLRInputStream, BailErrorStrategy, CommonTokenStream}

abstract class AJimpTest(sourceFile: String) extends TestCase with TWorkaround {

	def parser = {
		val tokenStream = new CommonTokenStream(
			new JimpLexer({
				val stream: ANTLRInputStream = new ANTLRInputStream(classOf[TJimpTests].getResourceAsStream(sourceFile))
				stream.name = sourceFile
				stream
			})
		)
		val parser =
			new JimpParser(
				tokenStream
			)
		parser.setErrorHandler(new BailErrorStrategy())
		parser
	}

	def testParser(): Unit = {
		parser.module()
	}

	def testSyntax(): Unit = {
		Syntax(parser.module())
	}
}
