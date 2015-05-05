package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue
import peterlavalle.jimpifier.ast.typ.ClassType

case class AllocateObject(tType: ClassType) extends TRValue {
}
