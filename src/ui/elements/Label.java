package ui.elements;

import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {
	private static final long serialVersionUID = 7737583840566915828L;
	public Label(String text, Font font) {
		super(text);
		setFont(font);	
	}
	public Label(Font font) {
		setFont(font);	
	}
}