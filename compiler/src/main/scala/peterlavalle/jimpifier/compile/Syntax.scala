package peterlavalle.jimpifier.compile

import org.antlr.v4.runtime.tree.TerminalNode
import peterlavalle.jimpifier.ast.Visibility
import peterlavalle.jimpifier.ast.typ.{ClassType, Primitive, TType, ArrayOf}
import peterlavalle.jimpifier.compile.JimpParser._
import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.lva.{Accessor, Global, Indexor}
import peterlavalle.jimpifier.ast.rva._
import peterlavalle.jimpifier.ast.ssa._
import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

import scala.collection.JavaConversions._

object Syntax {

	def tType(name: String): TType =
		if (name.endsWith("[]"))
			ArrayOf(tType(name.reverse.substring(2).reverse))
		else if (Primitive ? name)
			Primitive(name)
		else
			ClassType(name)

	def apply(t: Literal_classContext) = Literal.LiteralClass(t.STRING().getText.substring(1).reverse.substring(1).reverse)

	def apply(d: Literal_doubleContext) = Literal.LiteralDouble(d.getText.toDouble)

	def apply(s: Literal_stringContext) = Literal.LiteralString(s.getText.substring(1).reverse.substring(1).reverse)

	def apply(i: Literal_integerContext) = Literal.LiteralInt(i.getText.toInt)

	def apply(f: Literal_floatContext) = Literal.LiteralFloat(f.getText.toFloat)

	def apply(l: Literal_longContext) = Literal.LiteralLong(l.getText.reverse.tail.reverse.toLong)

	def apply(visibility: TerminalNode): Visibility.TVisibility =
		if (null == visibility)
			null
		else
			visibility.getText match {
				case "public" => Visibility.Public
				case "private" => Visibility.Private
				case "protected" => Visibility.Protected
			}

	def apply(module: ModuleContext): Module = {
		Module(
			isFinal = null != module.K_final(),
			isEnum = null != module.K_enum(),
			visibility = Syntax(module.K_visibility()),
			name = tType(module.classname(0).getText),
			parent = tType(module.classname(1).getText),
			fields = module.field().map(apply).toList,
			methods = module.method().map(apply).toList
		)
	}

	def apply(field: FieldContext): Field =
		Field(
			visibility = Syntax(field.K_visibility()),
			isStatic = null != field.K_static(),
			isFinal = null != field.K_final(),
			name = field.DUMBNAME().getText,
			tType = tType(field.typename().getText)
		)

	def apply(handler: HandlerContext): Handler =
		Handler(
			ClassType(handler.classname().getText),
			handler.DUMBNAME(0).getText,
			handler.DUMBNAME(1).getText,
			handler.DUMBNAME(2).getText
		)

	def apply(classname: ClassnameContext): TType =
		tType(classname.getText)

	def apply(typename: TypenameContext): TType =
		typename.BRACK_CLOSE().foldLeft(Syntax(typename.classname()))((l, r) => ArrayOf(l))

	def apply(registers: RegistersContext): List[Register] = {
		registers.REGISTER().map(_.getText).map(Register(_, apply(registers.typename()))).toList
	}

	def lv(lookup: (TerminalNode => Register), context: LvalueContext): TLValue = {
		context match {
			case array: Lval_arrayContext =>
				Indexor(lv(lookup, array.lvalue()), rv(lookup, array.rvalue()))

			case field: Lval_fieldContext =>
				lv(lookup, field.lvalue()) match {
					case Global(container, name) =>
						Global(container ++ List(name), field.DUMBNAME().getText)
					case other: TLValue =>
						Accessor(other, field.DUMBNAME().getText)
				}

			case register: Lval_registerContext =>
				lookup(register.REGISTER())

			case global: Lval_globalContext =>
				Global(global.DUMBNAME().reverse.tail.reverseMap(_.getText).toList, global.DUMBNAME().last.getText)

			case wat =>
				sys.error(wat.getClass.toString)
		}
	}

	def rv(lookup: (TerminalNode => Register), context: RvalueContext): TRValue =
		context match {

			case classOf: Literal_classContext =>
				Syntax(classOf)

			case double: Literal_doubleContext =>
				Syntax(double)

			case enum: EnumValueOfContext =>
				EnumValue(
					enum.STRING().getText.substring(1).reverse.substring(1).reverse,
					rv(lookup, enum.rvalue())
				)

			case float: Literal_floatContext =>
				Syntax(float)

			case length: LengthContext =>
				LengthOf(Syntax.rv(lookup, length.rvalue()))

			case integer: Literal_integerContext =>
				Syntax(integer)

			case long: Literal_longContext =>
				Syntax(long)

			case lval: Rvalue_lvalueContext =>
				Syntax.lv(lookup, lval.lvalue())

			case negate: NegationContext =>
				NegateValue(rv(lookup, negate.rvalue()))

			case _: Literal_nullContext =>
				Literal.Null

			case special: SpecialContext =>
				val parameter = "@parameter(\\d+)".r
				special.SPECIAL().getText match {
					case "@this" =>
						Literal.This
					case parameter(s) =>
						Literal.Parameter(s.toInt)
					case "@caughtexception" =>
						Literal.CaughtException
				}

			case string: Literal_stringContext =>
				Syntax(string)

			case wat =>
				sys.error(wat.getClass.toString)
		}

	def apply(registers: List[Register], statement: StatementContext): TSSA = {

		def lookup(register: TerminalNode): Register = {
			require(null != register)
			registers.filter(_.name == register.getText) match {
				case List(one) =>
					one

				case Nil =>
					sys.error("Unknown register `" + register.getText + "` @ " + register.getSymbol.getLine + "/" + register.getSymbol.getCharPositionInLine)
			}
		}

		def rvalue(context: RvalueContext): TRValue = Syntax.rv(lookup, context)
		def lvalue(context: LvalueContext): TLValue = Syntax.lv(lookup, context)

		statement match {

			case allocate: AllocateContext =>
				Assign(
					lvalue(allocate.lvalue()),
					AllocateObject(tType(allocate.typename().getText).asInstanceOf[ClassType])
				)

			case assign: AssignContext =>
				Assign(
					lvalue(assign.lvalue()),
					rvalue(assign.rvalue())
				)

			case cast: CastContext =>
				Assign(
					lvalue(cast.lvalue()),
					CastValue(
						tType(cast.typename().getText),
						rvalue(cast.rvalue())
					)
				)

			case infix: InfixContext =>
				val List(l: TRValue, r: TRValue) = infix.rvalue().map(rvalue).toList

				Assign(
					lvalue(infix.lvalue()),
					InfixOperations(l, infix.INFIX(), r)
				)

			case invoke: Statement_invokeContext =>
				Assign(
					result = if (null != invoke.lvalue()) lvalue(invoke.lvalue()) else null,
					invoke.invoke() match {
						case special: Invoke_specialContext =>
							InvokeSpecial(
								owner = if (null != special.REGISTER()) lookup(special.REGISTER()) else null,
								name = ((special.K_init(), special.DUMBNAME()) match {
									case (v, null) => v
									case (null, v) => v
								}).getText,
								args = if (null != special.rvalue()) special.rvalue().map(rvalue).toList else List()
							)

						case global: Invoke_globalContext =>
							InvokeGlobal(
								global.typename().getText,
								global.DUMBNAME().getText,
								global.rvalue().map(rvalue).toList
							)

						case invoke: Invoke_methodContext =>
							InvokeMethod(
								lookup(invoke.REGISTER()),
								invoke.DUMBNAME().getText,
								invoke.rvalue().map(rvalue).toList
							)
					}
				)

			case jump: JumpContext =>
				lazy val List(l: TRValue, r: TRValue) = jump.rvalue().map(rvalue).toList
				if (jump.COMPARE() != null)
					jump.COMPARE().getText match {
						case "!=" => Branch.NotEqual(l, r, jump.DUMBNAME().getText)
						case "==" => Branch.IsEqual(l, r, jump.DUMBNAME().getText)
						case ">=" => Branch.GreaterEqual(l, r, jump.DUMBNAME().getText)
						case ">" => Branch.GreaterThan(l, r, jump.DUMBNAME().getText)
						case "<=" => Branch.LessEqual(l, r, jump.DUMBNAME().getText)
						case "<" => Branch.LessThan(l, r, jump.DUMBNAME().getText)
						case wat =>
							sys.error("COMPARE(" + wat + ")")
					}
				else
					Branch.GoTo(jump.DUMBNAME().getText)

			case monitor: MonitorContext =>
				monitor.K_monitor().getText match {
					case "entermonitor" =>
						Monitors.Enter(rvalue(monitor.rvalue()))
					case "exitmonitor" =>
						Monitors.Exit(rvalue(monitor.rvalue()))
				}

			case newarray: NewarrayContext =>
				Assign(
					lvalue(newarray.lvalue()),
					NewArray(
						tType(newarray.typename().getText).asInstanceOf[ArrayOf],
						rvalue(newarray.rvalue())
					)
				)

			case raise: RaiseContext =>
				Raise(rvalue(raise.rvalue()))

			case resurn: ResurnContext =>
				Return(if (null != resurn.rvalue()) rvalue(resurn.rvalue()) else null)

			case switch: SwitchContext =>
				Branch.Switch(
					rvalue(switch.rvalue()),
					switch.lookup_case().map {
						case dcase: DcaseContext =>
							Branch.Default(dcase.DUMBNAME().getText)
						case ccase: CcaseContext =>
							Branch.Case(ccase.INTEGER().getText.toInt, ccase.DUMBNAME().getText)
					}.toList
				)

			case wat =>
				sys.error(wat.getClass.toString)
		}
	}

	def apply(method: MethodContext): Method = {
		val registers = method.registers().flatMap(apply).toList

		// TODO ; build the blocks "here" but use the handlers to infer which type the @caughtexception is

		Method(
			visibility = Syntax(method.K_visibility()),
			isStatic = null != method.K_static(),
			name = method.method_name().getText,
			args = method.typename().tail.map(apply).toList,
			tType = tType(method.typename().head.getText),
			registers = registers,
			blocks = Block(null, method.statement().map(Syntax(registers, _)).toList) :: method.block().map(b => Block(b.DUMBNAME().getText, b.statement().map(Syntax(registers, _)).toList)).toList,
			handlers = method.handler().map(apply).toList
		)
	}
}
