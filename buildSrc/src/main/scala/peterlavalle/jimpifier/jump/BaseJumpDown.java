package jimpifier.jump;

import peterlavalle.jimpifier.jump.TJumpDown;
import peterlavalle.jimpifier.jump.TJumpDown$class;
import scala.Function1;
import scala.runtime.BoxedUnit;

import java.io.File;

public class BaseJumpDown implements TJumpDown {
	@Override
	public File soot() {
		return _soot;
	}

	public File _soot;
	public File _android;
	public String _mode = "J";

	@Override
	public String mode() {
		return _mode;
	}

	@Override
	public File android() {
		return _android;
	}

	@Override
	public int apply(File input, File jimpDir) {
		return TJumpDown$class.apply(this, input, jimpDir);
	}

	@Override
	public Function1<String, BoxedUnit> out() {
		return TJumpDown$class.out(this);
	}

	@Override
	public Function1<String, BoxedUnit> err() {
		return TJumpDown$class.err(this);
	}
}
