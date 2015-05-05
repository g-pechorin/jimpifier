package peterlavalle.jimpifier.ast.typ

case object Primitive {

	trait TPrimitiveType extends TType {
		val name = toString.substring(1).toLowerCase
	}

	case object PVoid extends TPrimitiveType

	case object PByte extends TPrimitiveType
	case object PShort extends TPrimitiveType
	case object PInt extends TPrimitiveType
	case object PLong extends TPrimitiveType

	case object PFloat extends TPrimitiveType
	case object PDouble extends TPrimitiveType

	case object PBoolean extends TPrimitiveType
	case object PChar extends TPrimitiveType
}
