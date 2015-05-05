package peterlavalle.jimpifier.ast

import peterlavalle.jimpifier.ast.tra.{TLValue, TNamed, TTyped}

case class Register(name: String, tType: String) extends TNamed with TTyped with TLValue {

}
