package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue
import peterlavalle.jimpifier.ast.typ.TType

case class CastValue(tType: TType, value: TRValue) extends TRValue {

}
