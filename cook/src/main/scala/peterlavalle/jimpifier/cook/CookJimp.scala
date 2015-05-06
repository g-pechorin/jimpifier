package peterlavalle.jimpifier.cook

import java.io.Writer

import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.Visibility.TVisibility

case object CookJimp extends TCook {

	def apply(writer: Writer, field: Field): Unit = ???

	def apply(writer: Writer, method: Method): Unit = ???

	override def cook(output: (String) => Writer)(module: Module): Unit =
		module match {
			case Module(visibility, isFinal, isEnum, name, parent, fields, methods) =>
				require(name.matches("[\\w\\$]+(/[\\w\\$]+)*"), "Name %s is not conformant".format(name))
				require(parent.matches("[\\w\\$]+(/[\\w\\$]+)*"), "Name %s is not conformant".format(parent))

				val writer =
					output(name.replaceAll("/", ".") + ".jimp")
						.append(
					    "%s%s%sclass %s extends %s\n"
						    .format(
					        if (null != visibility) visibility.toString.toLowerCase + " " else "",
					        if (isFinal) "final " else "",
					        if (isEnum) "enum " else "",
					        name.replace('/', '.'),
					        parent.replace('/', '.')
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
