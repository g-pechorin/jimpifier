# Tu Use

See [by hand](by-hand/) for instructions on how to generate `.jimp`

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
	* You want the not-Jetbrains `ANTLR v4 Grammar Plugin ` 1.6 (or maybe later ... not sure)
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
	* ... if you don't do it - IDEA can't se generated code
1. You're done it - the project is ready to play
	* Watch those syntax errors trickle out of the .scala files!


# Structure

This "thing" is done in 2/3 steps

1. lexing and parsing - read the text file and construct a sort of Concrete Syntax Tree
	* this is handled by Antlr
1. syntax analysis - read the CST and construct a sort of AST as an IR
	* this is handled by a `.scala` `object` named `nottingham/ibit/jimp/Syntax.scala` that consumes the Antlr constructs

# Validation

There were some old unit tests that relied on a mountain of `.jimp` text files.
Now - only a few of those remain; what's left is an auto-generated set of tests from a dumped `.zip` file.
(The `.zip` holds the original mountain of text - only a slight improvement)

## Jimp Tests / GLTron.apk

These were picked out of a port of GLTron.
 
## Soak Tests / Android.jar

There's an additional set of tests in `soak.zip` that can be added by running the `soak` goal.
These were generated the `android.jar` in Version 22 of the ADK.
They seem a bit excessive - but they're their if you want them. 
(If you want to get rid of them - delete your `build/generated/` directory and re-run the `test` goal)
