These are some spotty notes on how I got my first set of `.jimp` files for this.


1. build the :GL-Tron:app by hand
	1. download https://github.com/g-pechorin/android-gltron
	1. unzip it (or whatever) and hit `gradlew clean build`
	1. `GLTron/app/build/? outputs ?/apk` will have some .apk files you can work from
1. use soot. [read the docs for 30 seconds](http://sable.github.io/soot/) then [download the last stable](http://www.sable.mcgill.ca/software/soot-2.5.0.jar)
	* if you're into flagellation - you can [download the source](https://github.com/Sable/soot/archive/soot-2.5.0.zip) (which builds with Ant)
1. [some stuff is here](https://github.com/Sable/soot/wiki/Instrumenting-Android-Apps-with-Soot) but you probably want;
	* spit out the .class files like this;
		java -jar soot-trunk.jar -android-jars "C:\Users\pal\AppData\Local\Android\sdk\platforms" -src-prec apk -process-dir app-release-unsigned.apk
	* spit out jimple like this?
		java -jar soot-trunk.jar -android-jars "C:\Users\pal\AppData\Local\Android\sdk\platforms" -src-prec apk -process-dir app-release-unsigned.apk -f J
		
	* HAHHA - No. Do this;
		java -jar soot-trunk.jar -android-jars "C:\Users\pal\AppData\Local\Android\sdk\platforms" -src-prec apk -process-dir app-release-unsigned.apk -f j
	* replace calls as you please?
	* spit out `.class` files as a pre-build step
		C:\Users\pal\Desktop\android-gltron\GL-Tron\tuan-by-hand>java -jar soot-trunk.jar -soot-class-path "C:\Users\pal\AppData\Local\Android\sdk\platforms\android-22\android.jar" --process-path jimpleTuanified
	* compile your own entry-point and away you go!