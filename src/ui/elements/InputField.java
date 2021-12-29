package ui.elements;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import ui.GUI;

public class InputField extends JTextField {
	private static final long serialVersionUID = -7247990876209927070L;
	public enum Type { STRNG, INTGR, FLOAT };
	private Type type;
	private boolean emptyAllowed;
	private boolean hasValidValue;
	
	public InputField(GUI ui, Type type, boolean emptyAllowed) { 
		this.type = type;
		this.emptyAllowed = emptyAllowed;
		
		setPreferredSize(new Dimension(300, 20));

		setFocusTraversalKeysEnabled(false); // So we can handle VK_TAB keyevent.

		this.addKeyListener( new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if ( e.getKeyCode() == KeyEvent.VK_ENTER ){ 
					ui.txtFldCbck();
				} else if ( e.getKeyCode() == KeyEvent.VK_TAB  ){ 
					ui.focusInpFldAtRelativeIdx(e.isShiftDown() ? -1 : 1);
				}
			}

			@Override public void keyReleased(KeyEvent e) { }

			@Override public void keyTyped(KeyEvent e) { }
			
		} );

		this.addCaretListener( new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				updateFieldValidity();	
			}
		});

	}

	public void updateFieldValidity() {
		String currText = getText();
		if ( currText.isEmpty() ) {
			hasValidValue=emptyAllowed;
			return;
		}

		try {
			switch (type) {
			case FLOAT:
				Float.parseFloat(currText);
				break;
			case INTGR:
				Integer.parseInt(currText);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			hasValidValue=false;
			return;
		}
		hasValidValue=true;
	}
	
	public boolean hasValidValue() {
		return hasValidValue;
	}
}

