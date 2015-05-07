package peterlavalle.jimpifier.cook

import java.io.Writer

import peterlavalle.jimpifier.ast.Module

trait TCook {
	def cook(output: (String => Writer))(module: Module)
}
