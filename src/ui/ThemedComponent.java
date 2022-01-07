package ui;

import java.awt.Color;

import javax.swing.JComponent;

public abstract class ThemedComponent extends JComponent {
	public final Color bgColor;
	public final Color fgColor;
	public final Color accColor;
	
	public ThemedComponent( Color bgColor, Color fgColor, Color accColor ){
		this.bgColor = bgColor;
		this.fgColor = fgColor;
		this.accColor = accColor;
	}
	
	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
	}
}
