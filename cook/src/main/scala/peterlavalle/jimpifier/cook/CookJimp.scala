package peterlavalle.jimpifier.cook

import java.io.Writer

import peterlavalle.jimpifier.ast.Visibility.TVisibility
import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.rva.InvokeSpecial
import peterlavalle.jimpifier.ast.ssa.{Return, Assign}
import peterlavalle.jimpifier.ast.tra._

case object CookJimp extends TCook {

	def apply(writer: Writer, field: Field): Unit = ???

	def lvalue(lv: TLValue): String =
		lv match {
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
			    case Assign(lv, Literal.This) =>
				    lvalue(lv) + " := @this"
			    case Return(null) =>
				    "return"
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
				if (isStatic) ???

				writer
					.append(
				    "\n    %s%s %s(%s)\n"
					    .format(
				        if (null != visibility) visibility.toString.toLowerCase + " " else "",
				        tType,
				        name,
				        args.map(a => ???.toString).foldLeft("")(_ + ", " + _).replaceAll("^, ", "")
					    )
					)
					.append(
				    "    {"
					)

				// TODO : kniot groups of registers together :(
				registers
					.foreach {
					case Register(rName, rType) =>
						writer
							.append(
						    "\n        %s %s;\n"
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

				fields.foreach(apply(writer, _))

				methods.foreach(apply(writer, _))

				writer
					.append(
				    "}\n"
					)
					.close()
		}
}
