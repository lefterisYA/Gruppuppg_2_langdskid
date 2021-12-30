package ui;

public class ElmntPos {
	final public int x;
	final public int y;
	final public int width;
	final public boolean xIsRelative;
	final public boolean yIsRelative;

	public ElmntPos(int x, int y) {
		this(x, y, 1, true, true);
	}

	public ElmntPos(int x, int y, int wdh) {
		this(x, y, wdh, true, true);
	}

	public ElmntPos(int x, int y, boolean xyIsRel) {
		this(x, y, 1, xyIsRel, xyIsRel);
	}
	
	public ElmntPos(int x, int y, int wdh, boolean xyIsRel) {
		this(x, y, wdh, xyIsRel, xyIsRel);
	}
	
	public ElmntPos(int x, int y, boolean xRel, boolean yRel) {
		this(x, y, 1, xRel, xRel);
	}

	public ElmntPos(int x, int y, int width, boolean xRel, boolean yRel) {
		this.x = x;
		this.y = y;
		this.width = width;
		xIsRelative = xRel;
		yIsRelative = yRel;
	}

}
