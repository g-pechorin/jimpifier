package peterlavalle.jimpifier.jump

import java.io

object Demo extends App with TJumpDown {

	override val mode: String = "j"
	val soot: io.File = new io.File("by-hand/soot-trunk.2015-05-04.jar")
	val android: io.File = new io.File(System.getProperty("user.home"), "AppData/Local/Android/sdk/platforms")

	// pull open GLTron (the target for this marvelous tool)
	// requires android-22 (I think)
	apply(
		new io.File("by-hand/app-release-unsigned.apk"),
		new io.File("jump/build/jimp-gltron")
	)

	// pull open OpenTyrian (a NDK / SDL app - we're not worrying about it much)
	// requires android-19
	apply(
		new io.File("jump/src/test/resources/OpenTyrian-2.1.25.apk"),
		new io.File("jump/build/jimp-tyrian")
	)
}
