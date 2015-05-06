package peterlavalle.jimpifier.cook

import java.io.Writer

import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.lva.Accessor
import peterlavalle.jimpifier.ast.rva.{InvokeSpecial, NewArray}
import peterlavalle.jimpifier.ast.ssa.{Assign, Return}
import peterlavalle.jimpifier.ast.tra._

case object CookJimp extends TCook {

	def apply(field: Field): String =
		"%s%s%s%s %s"
			.format(
		    if (null != field.visibility) field.visibility.toString.toLowerCase + " " else "",
		    if (field.isStatic) "static " else "",
		    if (field.isFinal) "final " else "",
		    tTypeString(field.tType),
		    field.name
			)

	def lvalue(lv: TLValue): String =
		lv match {
			case Accessor(obj, atr) =>
				lvalue(obj) + "." + atr
			case Register(name, _) =>
				name
			case wat =>
				sys.error("Peter missed lvalue ; " + wat)
		}

	def rvalue(rv: TRValue): String =
		rv match {
			case InvokeSpecial(Register(rName, _), fName, args) =>
				"specialinvoke %s.%s(%s)"
					.format(
				    rName,
				    fName,
				    args.map(rvalue).foldLeft("")(_ + ", " + _).replaceAll("^, ", "")
					)

			case Literal.LiteralInt(i) =>
				i.toString

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
			???

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
				        args.map(tTypeString).foldLeft("")(_ + ", " + _).replaceAll("^, ", "")
					    )
					)
					.append(
				    "    {\n"
					)

				// TODO : knit groups of registers together :(
				registers
					.foreach {
					case Register(rName, rType) =>
						writer
							.append(
						    "        %s %s;\n"
							    .format(
						        tTypeString(rType),
						        rName
							    )
							)
				}

				blocks.foreach(apply(writer, _))

				writer
					.append(
				    "    }\n"
					)
		}


	override def cook(output: (String) => Writer)(module: Module): Unit =
		module match {
			case Module(visibility, isFinal, isEnum, name, parent, fields, methods) =>
				require(name.matches("[\\w\\$]+(/[\\w\\$]+)*"), "Name %s is not conformant".format(name))
				require(parent.matches("[\\w\\$]+(/[\\w\\$]+)*"), "Name %s is not conformant".format(parent))

				val writer =
					output(tTypeString(name) + ".jimp")
						.append(
					    "%s%s%sclass %s extends %s\n"
						    .format(
					        if (null != visibility) visibility.toString.toLowerCase + " " else "",
					        if (isFinal) "final " else "",
					        if (isEnum) "enum " else "",
					        tTypeString(name),
					        tTypeString(parent)
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
