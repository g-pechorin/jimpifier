package peterlavalle.jimpifier.ast.rva

import peterlavalle.jimpifier.ast.tra.TRValue

case class NewArray(tType: String, size: TRValue) extends TRValue {

}
