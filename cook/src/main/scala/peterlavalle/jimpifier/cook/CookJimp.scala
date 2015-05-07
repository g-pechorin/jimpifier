package peterlavalle.jimpifier.cook

import java.io.Writer

import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.lva.{Global, Indexor, Accessor}
import peterlavalle.jimpifier.ast.rva._
import peterlavalle.jimpifier.ast.ssa._
import peterlavalle.jimpifier.ast.tra._

case object CookJimp extends TCook {

	def apply(field: Field): String =
		List(
			if (null != field.visibility) field.visibility.toString.toLowerCase + " " else "",
			if (field.isStatic) "static " else "",
			if (field.isFinal) "final " else "",
			if (field.isEnum) "enum " else "",
			tTypeString(field.tType),
			" ",
			field.name
		).reduce(_ + _)

	def lvalue(lv: TLValue): String =
		lv match {
			case Accessor(obj, atr) =>
				lvalue(obj) + "." + atr
			case Global(container, name) =>
				container.foldRight(name)(_ + "." + _)
			case Indexor(arr, idx) =>
				require(arr.tType.endsWith("[]"))
				"%s[%s]".format(lvalue(arr), rvalue(idx))
			case Register(name, _) =>
				name
			case wat =>
				sys.error("Peter missed lvalue ; " + wat)
		}

	def argsString(ar: List[AnyRef]): String =
		ar match {
			case Nil => ""
			case args =>
				args.map {
					case rv: TRValue => rvalue(rv)
					case st: String => st
				}
					.reduce(_ + ", " + _)
		}

	def rvalue(rv: TRValue): String =
		rv match {
			case AllocateObject(tType) =>
				"new " + tTypeString(tType)

			case CastValue(tType, value) =>
				"(" + tTypeString(tType) + ") " + rvalue(value)

			case EnumValue(eType, value) =>
				"java.lang.Enum.valueOf(" + argsString(List(eType, value)) + ")"

			case InfixOperations(l, o, r) =>
				rvalue(l) + " " + o + " " + rvalue(r)

			case InvokeGlobal(host, name, args) =>
				"%s.%s(%s)"
					.format(
				    host,
				    name,
				    argsString(args)
					)

			case InvokeMethod(host, name, args) =>
				"%s.%s(%s)"
					.format(
				    rvalue(host),
				    name,
				    argsString(args)
					)

			case InvokeSpecial(Register(rName, _), fName, args) =>
				"specialinvoke %s.%s(%s)"
					.format(
				    rName,
				    fName,
				    argsString(args)
					)

			case Literal.LiteralClass(c) =>
				"class \"" + c + "\"" // uses spikey-form

			case Literal.LiteralDouble(d) =>
				d.toString

			case Literal.LiteralFloat(f) =>
				f.toString + "F"

			case Literal.LiteralInt(i) =>
				i.toString

			case Literal.LiteralLong(l) =>
				l.toString + "L"

			case Literal.LiteralString(s) =>
				'"' + s + '"'

			case Literal.Null =>
				"null"

			case LengthOf(arr) =>
				"lengthof " + rvalue(arr)

			case NegateValue(value) =>
				"neg " + rvalue(value)

			case NewArray(eType, size) =>
				"newarray (" + tTypeString(eType) + ")[" + rvalue(size) + "]"

			case lv: TLValue =>
				lvalue(lv)

			case wat =>
				sys.error("Peter missed rvalue ; " + wat)
		}

	def apply(writer: Writer, ssa: TSSA): Unit = {
		writer
			.append("\n        ")
			.append(
		    ssa match {
			    case Assign(null, rv) =>
				    rvalue(rv)
			    case Assign(lv, special: Literal.TSpecial) =>
				    lvalue(lv) + " := @" + special.toString.replaceAll("\\W+", "").toLowerCase
			    case Assign(lv, rv) =>
				    lvalue(lv) + " = " + rvalue(rv)
			    case Branch(l: TRValue, o: String, r: TRValue, label: String) =>
				    "if " + rvalue(l) + " " + o + " " + rvalue(r) + " goto " + label
			    case Branch.GoTo(label) =>
				    "goto " + label
			    case Branch.Switch(value: TRValue, branches: List[Branch.TSwitchBranch]) =>
				    (List("lookupswitch(", rvalue(value), ")\n        {\n") ++ branches.map {
					    case Branch.Case(i, l) =>
						    "case %d: goto %s".format(i, l)
					    case Branch.Default(l) =>
						    "default: goto %s".format(l)
				    }.map("            " + _ + ";\n") ++ List("        }")
					    ).reduce(_ + _)
			    case Monitors.Enter(value) =>
				    "entermonitor " + rvalue(value)
			    case Monitors.Exit(value) =>
				    "exitmonitor " + rvalue(value)
			    case Raise(exception) =>
				    "throw " + rvalue(exception)
			    case Return(null) =>
				    "return"
			    case Return(rv) =>
				    "return " + rvalue(rv)
			    case wat =>
				    sys.error("Peter missed SSA ; " + wat)
		    }
			)
			.append(";\n")
	}

	def apply(writer: Writer, block: Block): Unit = {
		if (null != block.name)
			writer.append("\n     " + block.name + ":")

		block.ssa.foreach(apply(writer, _))
	}

	def tTypeString(tType: String): String =
		tType.replaceAll("/", ".")

	def apply(writer: Writer, method: Method): Unit =
		method match {
			case Method(visibility, isStatic, name, args, tType, registers, blocks, handlers) =>
				writer
					.append(
				    "\n    %s%s%s %s(%s)\n"
					    .format(
				        if (null != visibility) visibility.toString.toLowerCase + " " else "",
				        if (isStatic) "static " else "",
				        tType,
				        name,
				        argsString(args.map(tTypeString))
					    )
					)
					.append(
				    "    {"
					)

				// knit groups of registers together :)
				if (registers.nonEmpty) {
					def knit(registers: List[Register], yarn: List[(String, String)]): List[(String, String)] =
						(registers, yarn) match {
							case (Nil, _) => yarn.reverse

							case (Register(rName, rType) :: rTail, (yType, yText) :: yTail) if rType == yType =>
								knit(rTail, (yType, yText + ", " + rName) :: yTail)

							case (Register(rName, rType) :: rTail, _) =>
								knit(rTail, (rType, rName) :: yarn)
						}

					knit(registers, List())
						.foldLeft(writer.append("\n")) {
						case (w, (rType, rName)) =>
							w.append(
								"        " + tTypeString(rType) + " " + rName + ";\n"
							)
					}
				}

				blocks.foreach(apply(writer, _))

				if (handlers.nonEmpty)
					writer.append(
						handlers.foldLeft("\n") {
							case (left, Handler(hType: String, from: String, to: String, wit: String)) =>
								left + "        catch %s from %s to %s with %s;\n"
									.format(
								    tTypeString(hType),
								    from,
								    to,
								    wit
									)
						}
					)

				writer
					.append(
				    "    }\n"
					)
		}


	override def cook(output: (String) => Writer)(module: Module): Unit =
		module match {
			case Module(visibility, isFinal, isEnum, name, parent, interfaces, fields, methods) =>
				require(name.matches("[\\w\\$]+(/[\\w\\$]+)*"), "Name %s is not conformant".format(name))
				require(parent.matches("[\\w\\$]+(/[\\w\\$]+)*"), "Name %s is not conformant".format(parent))

				val writer =
					output(tTypeString(name) + ".jimp")
						.append(
					    "%s%s%sclass %s extends %s%s\n"
						    .format(
					        if (null != visibility) visibility.toString.toLowerCase + " " else "",
					        if (isFinal) "final " else "",
					        if (isEnum) "enum " else "",
					        tTypeString(name),
					        tTypeString(parent),
					        if (interfaces.isEmpty)
						        ""
					        else {
						        " implements " + interfaces.map(tTypeString).reduce(_ + ", " + _)
					        }
						    )
						)
						.append(
					    "{\n"
						)

				fields.map(apply).foreach {
					case text =>
						writer.append("    " + text + ";\n")
				}

				methods.foreach(apply(writer, _))

				writer
					.append(
				    "}\n"
					)
					.close()
		}
}
