package peterlavalle.jimpifier.compile

import java.io.InputStream

import jimp.TJimpTests
import org.antlr.v4.runtime.{ANTLRInputStream, BailErrorStrategy, CommonTokenStream}

trait TAntlr4Parser {

	def getName(): String

	def parser(sourceStream: InputStream, name: String = getName()): JimpParser = {
		val parser =
			new JimpParser(new CommonTokenStream(
				new JimpLexer({
					val stream: ANTLRInputStream = new ANTLRInputStream(sourceStream)
					stream.name = name
					stream
				})
			)
			)
		parser.setErrorHandler(new BailErrorStrategy())
		parser
	}

	def parser(sourceFile: String): JimpParser = {
		parser(classOf[TJimpTests].getResourceAsStream(sourceFile), sourceFile)
	}
}
