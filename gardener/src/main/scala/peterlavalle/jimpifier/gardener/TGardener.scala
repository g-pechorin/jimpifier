package peterlavalle.jimpifier.gardener

import peterlavalle.jimpifier.ast._
import peterlavalle.jimpifier.ast.lva.{Accessor, Global, Indexor}
import peterlavalle.jimpifier.ast.rva._
import peterlavalle.jimpifier.ast.ssa.Branch.GoTo
import peterlavalle.jimpifier.ast.ssa._
import peterlavalle.jimpifier.ast.tra.{TLValue, TRValue, TSSA}

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
			module.parent,
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

			case _: Global =>
				lv

			case Accessor(obj, field) =>
				Accessor(lvalue(module, method, block, ssa, obj), field)

			case Indexor(arr, index) =>
				Indexor(lvalue(module, method, block, ssa, arr), rvalue(module, method, block, ssa, index))

			case register: Register => apply(module, method, register)

			case missing =>
				sys.error("Peter missed the lvalue " + missing)
		}

	def rvalue(module: Module, method: Method, block: Block, ssa: TSSA, rv: TRValue): TRValue =
		rv match {

			case null =>
				// doesn't make as much sense as a null lvalue ... which is ironic
				null
			case _: AllocateObject |
			     _: Literal.TLiteral =>
				rv

			case CastValue(tType, value) =>
				CastValue(tType, rvalue(module, method, block, ssa, value))

			case EnumValue(tType, value) =>
				EnumValue(
					rvalue(module, method, block, ssa, value).asInstanceOf[Literal.LiteralClass],
					rvalue(module, method, block, ssa, value)
				)


			case Indexor(l, r) =>
				Indexor(lvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixAdd(l, r) =>
				InfixOperations.InfixAdd(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixBitRight(l, r) =>
				InfixOperations.InfixBitRight(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixCMP(l, r) =>
				InfixOperations.InfixCMP(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixCMPG(l, r) =>
				InfixOperations.InfixCMPG(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixCMPL(l, r) =>
				InfixOperations.InfixCMPL(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixDivide(l, r) =>
				InfixOperations.InfixDivide(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixModulus(l, r) =>
				InfixOperations.InfixModulus(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixMultiply(l, r) =>
				InfixOperations.InfixMultiply(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InfixOperations.InfixSubtract(l, r) =>
				InfixOperations.InfixSubtract(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r))

			case InvokeGlobal(tType, name, args) =>
				InvokeGlobal(tType, name, args.map(rvalue(module, method, block, ssa, _)))

			case InvokeMethod(obj, name, args) =>
				InvokeMethod(rvalue(module, method, block, ssa, obj), name, args.map(rvalue(module, method, block, ssa, _)))

			case InvokeSpecial(register, name, args) =>
				InvokeSpecial(apply(module, method, register), name, args.map(rvalue(module, method, block, ssa, _)))

			case LengthOf(arr) =>
				LengthOf(rvalue(module, method, block, ssa, arr))

			case NegateValue(arr) =>
				NegateValue(rvalue(module, method, block, ssa, arr))

			case NewArray(tType, size) =>
				NewArray(tType, rvalue(module, method, block, ssa, size))

			case lv: TLValue => lvalue(module, method, block, ssa, lv)

			case missing =>
				sys.error("Peter missed the rvalue " + missing)
		}

	def instruction(module: Module, method: Method, block: Block, ssa: TSSA): TSSA =
		ssa match {
			case _: GoTo =>
				ssa

			case Assign(lv, rv) =>
				Assign(lvalue(module, method, block, ssa, lv), rvalue(module, method, block, ssa, rv))

			case Branch.GreaterEqual(l, r, label) =>
				Branch.GreaterEqual(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r), label)

			case Branch.GreaterThan(l, r, label) =>
				Branch.GreaterThan(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r), label)

			case Branch.IsEqual(l, r, label) =>
				Branch.IsEqual(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r), label)

			case Branch.LessEqual(l, r, label) =>
				Branch.LessEqual(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r), label)

			case Branch.LessThan(l, r, label) =>
				Branch.LessThan(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r), label)

			case Branch.NotEqual(l, r, label) =>
				Branch.NotEqual(rvalue(module, method, block, ssa, l), rvalue(module, method, block, ssa, r), label)

			case Branch.Switch(value, branches) =>
				Branch.Switch(rvalue(module, method, block, ssa, value), branches)

			case Monitors.Enter(value) =>
				Monitors.Enter(rvalue(module, method, block, ssa, value))

			case Monitors.Exit(value) =>
				Monitors.Exit(rvalue(module, method, block, ssa, value))

			case Raise(value) =>
				Raise(rvalue(module, method, block, ssa, value))

			case Return(value) =>
				Return(rvalue(module, method, block, ssa, value))

			case missing =>
				sys.error("Peter missed the instruction " + missing)
		}
}
