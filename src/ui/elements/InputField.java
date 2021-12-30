package ui.elements;

import java.awt.Dimension;
import java.awt.GridLayout;

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

	public InputField(String title) { 
		super(new GridLayout(1,2));
		txtFld = new JTextField();

		txtFld.setPreferredSize(new Dimension(300, 20));
		txtFld.setFocusTraversalKeysEnabled(false); // So we can handle VK_TAB keyevent.
		
		add(new JLabel(title));
		add(txtFld);
	}

	public InputField(String title, FieldValidator validator) {
		this(title);
		txtFld.addCaretListener( (CaretListener) new CaretListener() {
			@Override
			public void caretUpdate(CaretEvent e) {
				hasValidValue = validator.getValidity( txtFld.getText() ) ;
			}
		});
	}

	public JTextField getTxtFld() {
		return txtFld;
	}
	
	public String getText() {
		return txtFld.getText();
	}

	public boolean hasValidValue() {
		return hasValidValue;
	}
}
	
	
	
//	public void updateFieldValidity() {
//		String currText = getText();
//		if ( currText.isEmpty() ) {
//			hasValidValue=emptyAllowed;
//			return;
//		}
//
//		try {
//			switch (type) {
//			case FLOAT:
//				Float.parseFloat(currText);
//				break;
//			case INTGR:
//				Integer.parseInt(currText);
//				break;
//			default:
//				break;
//			}
//		} catch (Exception e) {
//			hasValidValue=false;
//			return;
//		}
//		hasValidValue=true;
//	}
	

