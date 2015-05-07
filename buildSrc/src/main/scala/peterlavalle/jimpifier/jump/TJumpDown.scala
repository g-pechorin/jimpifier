package peterlavalle.jimpifier.jump

import java.io

/**
 * This trait implements the project. Use trait-injection to fill it out (... or check the Demo object in src/test/scala if that's gibberish)
 */
trait TJumpDown {
	val soot: io.File
	val android: io.File

	val mode: String

	def out: (String => Unit) = System.out.println

	def err: (String => Unit) = System.err.println

	def apply(input: io.File, jimpDir: io.File) = {

		//
		// java -jar soot-trunk.jar -android-jars "C:\Users\pal\AppData\Local\Android\sdk\platforms" -src-prec apk -process-dir app-release-unsigned.apk -f j

		import sys.process._

		val cmd =
			"""java -jar "%s" -android-jars "%s" -src-prec apk -process-dir %s -f %s -output-dir "%s" """
				.format(
			    soot,
			    android,
			    input,
			    mode,
			    jimpDir
				)

		//run the command
		println(cmd)

		cmd.!(ProcessLogger(out, err))

	}
}
