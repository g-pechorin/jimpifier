package peterlavalle.jimpifier.compile

import org.antlr.v4.runtime.{ANTLRInputStream, BailErrorStrategy, CommonTokenStream}
import peterlavalle.jimpifier.ast.Literal.This
import peterlavalle.jimpifier.ast.Visibility.Public
import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.lva.Accessor
import peterlavalle.jimpifier.ast.rva.{InvokeSpecial, NewArray}
import peterlavalle.jimpifier.ast.ssa.{Assign, Return}
import peterlavalle.jimpifier.ast.typ.{ArrayOf, ClassType, Primitive}
import peterlavalle.jimpifier.compile.JimpParser.MethodContext

class com_glTron_Video_GraphicUtils$vec2_test extends AJimpTest("com.glTron.Video.GraphicUtils$vec2.jimp") {

	def parseLine() = {
		val stream = new ANTLRInputStream("$r0.this$0 = $r1")
		stream.name = getName
		val lexer = new JimpLexer(stream)
		val tokens = new CommonTokenStream(lexer)
		val parser = new JimpParser(tokens)
		parser.statement()
	}

	def testLineParse(): Unit = {
		parseLine()
	}

	def testLineSyntax(): Unit = {
		val r0 = Register("$r0", ClassType("com/glTron/Video/GraphicUtils$vec2"))
		val r1 = Register("$r1", ClassType("com/glTron/Video/GraphicUtils"))

		val expect = Assign(Accessor(r0, "this$0"), r1)
		val actual = Syntax(List(), List(r0, r1), parseLine())

		assertEquals(expect, actual)
	}

	def parseMethod(): MethodContext = {
		val stream = new ANTLRInputStream(source)
		stream.name = getName
		val lexer = new JimpLexer(stream)
		val tokens = new CommonTokenStream(lexer)
		val parser = new JimpParser(tokens)
		parser.method()
	}

	def testMethodParse() {
		parseMethod()
	}

	val source =
		"""
		  |   public void <init>(com.glTron.Video.GraphicUtils)
		  |    {
		  |        com.glTron.Video.GraphicUtils$vec2 $r0;
		  |        com.glTron.Video.GraphicUtils $r1;
		  |        float[] $r2;
		  |
		  |        $r0 := @this;
		  |
		  |        $r1 := @parameter0;
		  |
		  |        $r0.this$0 = $r1;
		  |
		  |        specialinvoke $r0.<init>();
		  |
		  |        $r2 = newarray (float)[2];
		  |
		  |        $r0.v = $r2;
		  |
		  |        return;
		  |    }
		""".stripMargin


	def testMethodSyntax() {
		assertEquals(
			Method(
				visibility = Public,
				isStatic = false,
				name = "<init>",
				args = List(ClassType("com/glTron/Video/GraphicUtils")),
				tType = Primitive.PVoid,
				registers = List(
					Register("$r0", ClassType("com/glTron/Video/GraphicUtils$vec2")),
					Register("$r1", ClassType("com/glTron/Video/GraphicUtils")),
					Register("$r2", ArrayOf(Primitive.PFloat))
				),
				blocks = List(
					Block(
						null,
						List(
							Assign(Register("$r0", ClassType("com/glTron/Video/GraphicUtils$vec2")), This),
							Assign(Register("$r1", ClassType("com/glTron/Video/GraphicUtils")), Literal.Parameter(0, ClassType("com/glTron/Video/GraphicUtils"))),
							Assign(Accessor(Register("$r0", ClassType("com/glTron/Video/GraphicUtils$vec2")), "this$0"), Register("$r1", ClassType("com/glTron/Video/GraphicUtils"))),
							Assign(null, InvokeSpecial(Register("$r0", ClassType("com/glTron/Video/GraphicUtils$vec2")), "<init>", List())),
							Assign(Register("$r2", ArrayOf(Primitive.PFloat)), NewArray(ArrayOf(Primitive.PFloat), Literal.LiteralInt(2))),
							Assign(Accessor(Register("$r0", ClassType("com/glTron/Video/GraphicUtils$vec2")), "v"), Register("$r2", ArrayOf(Primitive.PFloat))),
							Return(null)
						)
					)
				),
				handlers = List()
			),
			Syntax(parseMethod())
		)
	}
}

class R$layout_test extends AJimpTest("com.glTron.R$layout.jimp") {

	def fieldParse() = {
		val source =
			"""
			  |public static final int main;
			""".stripMargin
		val tokenStream = new CommonTokenStream(
			new JimpLexer({
				val stream: ANTLRInputStream = new ANTLRInputStream(source)
				stream.name = getName
				stream
			})
		)
		val parser =
			new JimpParser(
				tokenStream
			)
		parser.setErrorHandler(new BailErrorStrategy())
		parser.field()
	}

	def testFieldParse() {
		fieldParse()
	}

	def testFieldSyntax() {
		assertEquals(
			Field(
				Visibility.Public,
				isStatic = true,
				isFinal = true,
				isEnum = false,
				name = "main",
				tType = Primitive.PInt
			),
			Syntax(fieldParse())
		)
	}
}

class R_test extends AJimpTest("com.glTron.R.jimp") {
	def methodParse() = {
		val source =
			"""
			  |public void <init>()
			  |    {
			  |        com.glTron.R $r0;
			  |
			  |        $r0 := @this;
			  |
			  |        specialinvoke $r0.<init>();
			  |
			  |        return;
			  |    }
			""".stripMargin
		val tokenStream = new CommonTokenStream(
			new JimpLexer({
				val stream: ANTLRInputStream = new ANTLRInputStream(source)
				stream.name = getName
				stream
			})
		)
		val parser =
			new JimpParser(
				tokenStream
			)
		parser.setErrorHandler(new BailErrorStrategy())
		parser.method()
	}

	def testMethodParse() {
		methodParse()
	}

	def testMethodSyntax() {
		assertEquals(
			Method(
				Visibility.Public,
				isStatic = false,
				name = "<init>",
				args = List(),
				tType = Primitive.PVoid,
				List(
					Register("$r0", ClassType("com/glTron/R"))
				),
				List(
					Block(null,
						List(
							Assign(Register("$r0", ClassType("com/glTron/R")), Literal.This),
							Assign(null, InvokeSpecial(Register("$r0", ClassType("com/glTron/R")), "<init>", List())),
							Return(null)
						)
					)
				),
				List()
			),
			Syntax(methodParse())
		)
	}
}
