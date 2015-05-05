package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue
import peterlavalle.jimpifier.ast.typ.ArrayOf

case class NewArray(tType: ArrayOf, size: TRValue) extends TRValue {

}
