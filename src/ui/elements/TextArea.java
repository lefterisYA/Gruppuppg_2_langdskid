package ui.elements;

import java.awt.Font;

import javax.swing.JTextArea;

public class TextArea extends JTextArea {
	private static final long serialVersionUID = -3995422772846981975L;

	public TextArea(Font font) {
		setEditable(false);
		setBounds(0, 0, 600, 200);
		setFont(font);
		setOpaque(false);
	}
}