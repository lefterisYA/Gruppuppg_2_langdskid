package ui.elements;

import java.awt.Dimension;
import java.awt.GridLayout;
// import java.awt.event.KeyEvent;
// import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import ui.interfaces.FieldValidator;

public class InputField extends JPanel {
	private static final long serialVersionUID = -7247990876209927070L;

	private JTextField txtFld;
	private boolean hasValidValue;
	FieldValidator validator;
	InputFieldHandler handler;

	public InputField(String title) {
		super(new GridLayout(1,2));
		txtFld = new JTextField();

		txtFld.setPreferredSize(new Dimension(300, 20));
		// txtFld.setFocusTraversalKeysEnabled(false); // So we can handle VK_TAB keyevent.
		// addKeyListeners();

		add(new JLabel(title));
		add(txtFld);
	}

	public InputField(String title, FieldValidator validator, InputFieldHandler handler) {
		this(title);
		this.validator = validator;
		this.handler = handler;

		addValidityCheckListeners();
	}

	private void addValidityCheckListeners() {
		txtFld.addCaretListener( (CaretListener) new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				updateValidity();
			}
		});
    }

//		txtFld.addFocusListener( new FocusListener() {
//			@Override public void focusLost(FocusEvent e) {
//				if ( handler.allFieldsAreValid() ) {
//					validator.onValidFields(getTxtFld().getText());
//				}
//				else {
//					validator.onInvalidFields(getTxtFld().getText());
//				}
//			}
//
//			@Override public void focusGained(FocusEvent e) { }
//		});

// 	private void addKeyListeners() {
// 		txtFld.addKeyListener( (KeyListener) new KeyListener() {
// 			public void keyPressed(KeyEvent e) {
// 				if ( e.getKeyCode() == KeyEvent.VK_ENTER ){
// //					ui.txtFldCbck(); // TODO
// 				} else if ( e.getKeyCode() == KeyEvent.VK_TAB  ){
// 					handler.focusInpFldAtRelativeIdx(e.isShiftDown() ? -1 : 1);
// 				}
// 			}
//
// 			@Override public void keyReleased(KeyEvent e) { }
// 			@Override public void keyTyped(KeyEvent e) { }
//
// 		} );
// 	}

	public FieldValidator getValidator(){
		return validator;
	}

	public JTextField getTxtFld() {
		return txtFld;
	}

	public String getText() {
		return txtFld.getText();
	}

	private void updateValidity() {
		boolean newValidity = validator.getValidity( txtFld.getText() ) ;
		if ( hasValidValue == newValidity )
			return;
		else {
			hasValidValue = newValidity;
			handler.fldValidityChangedCB(this);
		}

	}

	public boolean hasValidValue() {
		return hasValidValue;
	}
}
