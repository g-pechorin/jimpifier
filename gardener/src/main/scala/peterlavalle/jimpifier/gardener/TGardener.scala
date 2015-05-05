package peterlavalle.jimpifier.gardener

import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.rva.InvokeSpecial
import peterlavalle.jimpifier.ast.ssa.{Return, Assign}
import peterlavalle.jimpifier.ast.tra.{TRValue, TLValue, TSSA}

/**
 * Trait which can be extended to manipulate pieces of AST
 */
trait TGardener {

	def apply(module: Module): Module =
		Module(
			module.visibility,
			module.isFinal,
			module.isEnum,
			module.name,
			module.tType,
			fields(module, module.fields),
			methods(module, module.methods)
		)

	def fields(module: Module, fields: List[Field]): List[Field] =
		fields.map(apply(module, _))

	def apply(module: Module, field: Field): Field =
		Field(
			field.visibility,
			field.isStatic,
			field.isFinal,
			field.name,
			field.tType
		)

	def methods(module: Module, methods: List[Method]): List[Method] =
		methods.map(apply(module, _))

	def apply(module: Module, method: Method): Method =
		Method(
			method.visibility,
			method.isStatic,
			method.name,
			method.args,
			method.tType,
			registers(module, method, method.registers),
			blocks(module, method, method.blocks),
			handlers(module, method, method.handlers)
		)

	def registers(module: Module, method: Method, registers: List[Register]): List[Register] =
		registers.map(apply(module, method, _))

	def apply(module: Module, method: Method, register: Register): Register =
		Register(register.name, register.tType)

	def blocks(module: Module, method: Method, blocks: List[Block]): List[Block] =
		blocks.map(apply(module, method, _))

	def apply(module: Module, method: Method, handler: Handler): Handler =
		Handler(handler.tType, handler.from, handler.to, handler.wit)

	def handlers(module: Module, method: Method, handlers: List[Handler]): List[Handler] =
		handlers.map(apply(module, method, _))

	def apply(module: Module, method: Method, block: Block): Block =
		Block(
			block.name,
			block.ssa.map(instruction(module, method, block, _))
		)

	def lvalue(module: Module, method: Method, block: Block, ssa: TSSA, lv: TLValue): TLValue =
		lv match {

			case null => null

			case register: Register => apply(module, method, register)

			case missing =>
				sys.error("Peter missed the lvalue " + missing)
		}

	def rvalue(module: Module, method: Method, block: Block, ssa: TSSA, rv: TRValue): TRValue =
		rv match {

			case null =>
				// doesn't make as much sense as a null lvalue ... which is ironic
				null

			case lv: TLValue => lvalue(module, method, block, ssa, lv)
			case Literal.This => Literal.This

			case InvokeSpecial(register, name, args) =>
				InvokeSpecial(apply(module, method, register), name, args.map(rvalue(module, method, block, ssa, _)))

			case missing =>
				sys.error("Peter missed the rvalue " + missing)
		}

	def instruction(module: Module, method: Method, block: Block, ssa: TSSA): TSSA =
		ssa match {
			case Assign(lv, rv) =>
				Assign(lvalue(module, method, block, ssa, lv), rvalue(module, method, block, ssa, rv))

			case Return(rv) =>
				Return(rvalue(module, method, block, ssa, rv))


			case missing =>
				sys.error("Peter missed the instruction " + missing)
		}
}
