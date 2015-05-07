package jimpifier.sable

import java.io.{InputStream, InputStreamReader, PushbackReader}

import peterlavalle.jimpifier.ast.Module
import soot.jimple.parser.lexer.Lexer
import soot.jimple.parser.node.Start
import soot.jimple.parser.parser.Parser


object SableSyntax {
	def apply(inputStream: InputStream): Module = {
		require(null != inputStream)

		val file: Start = new Parser(
			new Lexer(
				new PushbackReader(
					new InputStreamReader(inputStream), 1024
				)
			)
		).parse()

		file.apply(new TAnalysis() {

		})

		???
	}
}