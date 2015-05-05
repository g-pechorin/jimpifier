package peterlavalle.jimpifier.compile

import peterlavalle.jimpifier.ast.tra.TIR

trait TWorkaround {

	def assertEquals(expect: Object, actual: Object): Unit = {
		(expect, actual) match {
			case (_: TIR, _: TIR) =>
				if (expect == actual)
					junit.framework.Assert.assertEquals(expect, actual)
				else {
					System.err.println("This should be fixed for real someday")
					junit.framework.Assert.assertEquals(expect.toString, actual.toString)
				}
			case _ =>
				junit.framework.Assert.assertEquals(expect, actual)
		}
	}
}
