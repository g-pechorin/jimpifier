
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
A binary is probably stuffed in the project tree somewhere.

 
## Soak Tests / Android.jar

There's an additional set of tests in `soak.zip` that can be added by running the `soak` goal.
These were generated the `android.jar` in Version 22 of the ADK.
They seem a bit excessive - but they're there if you want them. 
(If you want to get rid of them - delete your `build/generated/` directory and re-run the `test` goal)

# Flaws

This tool will probably fall-over for a whole bunch of reasons.

* doesn't use consistent type names.
	* I'd like `foo/bar$Something` but I'm sure it's using `foo.bar$Something` somewhere and wouldn't be surprised to see `foo.bar.Something`
* probably can't handle `native`, `interface` or `abstract` methods and classes
* `jimpifier.ast` and `jimpifier.compiler` should be in separate modules
	* ... but I know that there's CST -> AST logic in `jimpifier.ast` which shouldn't be there
