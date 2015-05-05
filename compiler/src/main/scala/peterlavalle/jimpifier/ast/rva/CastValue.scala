package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue

case class CastValue(tType: String, value: TRValue) extends TRValue {

}
