package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra.{TLValue, TNamed, TTyped}
import peterlavalle.jimpifier.ast.typ.TType

case class Register(name: String, tType: TType) extends TNamed with TTyped with TLValue {

}
