This is a WIP that should yield a sneaky toolchain to inject code into .apk files by rewriting them AoT.
The intent is to intercept OpenGL ES calls and do "other" noble-good things that the original authors hadn't considered.

* [compiler/](compiler/) reads `.jimp` into a sort of [AST](http://en.wikipedia.org/wiki/Abstract_syntax_tree). Maybe it's [IR](http://en.wikipedia.org/wiki/Intermediate_language#Intermediate_representation). Technically - it's probably going to be IR even if it lacks the finer details that usual characterize IR
* [cook/](cook/) writes out piles of source files
* [jump/](jump/) contains a trait which uses [Soot](http://sable.github.io/soot/) to generate `.jimp` files. It also has demonstrations of this trait.
* [gardener/](gardener/) contains a trait which can be extended to consume an AST and emits a new improved version of the AST. As it stands - the provided trait performs a deep copy to allow minimal overriding.
* [sable/](sable/) a compiler for `.jimple` from Soot's nighties

See [by hand](by-hand/) for instructions on how I oringinally generated `.jimp` files.

# Tu Use

These instructions are detailed - not hard or long.

1. Really, follow these steps.
	* Don't worry about errors until you've followed the steps
	* ... no really - from "cold checkout" there's a bunch of errors
1. Setup IntelliJ IDEA (Community Edition is what I use)
	* You need 14.1.2 (or maybe later ... not sure)
	* You need the Gradle plugin active
		* ... so probably the Groovy one as well
		* YOU DON'T NEED TO INSTALL GRADLE
			* ... ever IMO
	* You need JetBrain's Scala plugin 1.4.15 (or maybe later ... not sure)
	* You may want the not-Jetbrains `ANTLR v4 Grammar Plugin ` 1.6 (or maybe later ... not sure)
		* NOT V3!
		* this doesn't actually do anything until you start changing the `.g4` files
		* If you don't know why you need it - you probably should get it
		* Don't install a version 3 plugin
	* I use a MarkDown plugin ... but it's not important
1. In IntelliJ, "File > Open ..." the `build.gradle` file in the root of the jimpifier project
	* this'll be next to the `settings.gradle` file
	* Don't Import Project
	* Don't try to run a Gradle Idea plugin
	* Just open the damn thing
 	* if there's a `.idea/` folder or a `.iml` file when you check it out - someone is messing with your medicine
1. IGNORE ALL OF THE ERRORS SO FAR!
	* They're chicken-or-egg problems
1. Run the Gradle task "test" for the project
	* this will probably fail
	* it's in the Gradle menu > Tasks > verification > test
1. ONLY NOW - push the "Refresh All Gradle projects" button
	* it's the first one in the "Gradle Projects" menu
	* I'm not going to tell you what it looks like - there are other buttons that look like it
		* ... and they do nothing useful AFAICT
1. When that's done - right click on the `build/` folders and select "Mark Directory As > Cancel Exclusion"
	* You'll have to do this everytime you "Refresh All Gradle projects"
		* ... which should only be when modules are shuffled around
	* ... if you don't do it - IDEA can't see the generated code
1. You're done it - the project is ready to play
	* Watch those syntax errors trickle out of the .scala files!

