package ui.elements;

import java.util.LinkedList;

import ui.GUI;
import ui.interfaces.FieldValidator;

public class InputFieldHandler {
	private final LinkedList<InputField> inpFlds;
//	private final GUI ui;
			
	public InputFieldHandler(GUI ui) {
		inpFlds = new LinkedList<>();
//		this.ui = ui;
	}
	
	public InputField gnrt(String label) {
		InputField newInpFld = new InputField(label);
		inpFlds.add(newInpFld);

		return newInpFld;
	}

	public InputField gnrt( String label, FieldValidator validator ) {
		InputField newInpFld = new InputField(label, validator, this);
		inpFlds.add(newInpFld);
		return newInpFld;
	}

	public void fldValidityChangedCB(InputField caller) {
		if ( allFieldsAreValid() )
			caller.getValidator().onValidFields(caller.getTxtFld().getText());
		else
			caller.getValidator().onInvalidFields(caller.getTxtFld().getText());
	}
	
	private boolean allFieldsAreValid() {
		for ( InputField fld : inpFlds )
			if ( ! fld.hasValidValue() ) {
				return false;
			}
		return true;
	}
	
	public String[] getInpFldVals() {
		String[] ret = new String[inpFlds.size()];
		int i=0;
		for ( InputField fld : inpFlds )
			ret[i++] = fld.getText();
		return ret;
	}
	
	public void clrInpFlds() {
		inpFlds.clear();
	}

	public void focusInpFldAtRelativeIdx(int relIdx) {
		for ( int i=0; i<inpFlds.size(); i++ ) {
			if ( ! inpFlds.get(i).getTxtFld().isFocusOwner() )
				continue;
			else if ( i + relIdx >= inpFlds.size() )
				inpFlds.getFirst().getTxtFld().requestFocus();
			else if ( i + relIdx < 0 )
				inpFlds.getLast().getTxtFld().requestFocus();
			else
				inpFlds.get(i+relIdx).getTxtFld().requestFocus();
			return;
		}
	}
}
//	private void addKeyListeners(InputField field) {
//		field.getTxtFld().addKeyListener( (KeyListener) new KeyListener() {
//			public void keyPressed(KeyEvent e) {
//				if ( e.getKeyCode() == KeyEvent.VK_ENTER ){ 
////					ui.txtFldCbck(); // TODO
//				} else if ( e.getKeyCode() == KeyEvent.VK_TAB  ){ 
//					focusInpFldAtRelativeIdx(e.isShiftDown() ? -1 : 1);
//				}
//			}
//
//			@Override public void keyReleased(KeyEvent e) { }
//
//			@Override public void keyTyped(KeyEvent e) { }
//			
//		} );
//	}
	
//	private void addValidityCheckListeners(InputField field, FieldValidator validator) {
//		field.getTxtFld().addFocusListener( new FocusListener() {
//			@Override public void focusLost(FocusEvent e) {
//				if ( allFieldsAreValid() ) {
//					validator.onValidFields(field.getTxtFld().getText());
//				}
//				else {
//					validator.onInvalidFields(field.getTxtFld().getText());
//				}
//			}
//			
//			@Override public void focusGained(FocusEvent e) { }
//		});
//	}
	

//	public LinkedList<InputField> getTextFields() {
//		LinkedList<InputField> retVal = new LinkedList<InputField>();
//		for ( InputField fld : inpFlds ) {
//			retVal.add(fld);
//		}
//		return retVal;
//	}

//	public LinkedList<String> getTextFieldVals() {
//		LinkedList<String> retVal = new LinkedList<String>();
//		for ( InputField fld : inpFlds ) {
//			retVal.add(fld.getText());
//		}
////		for ( Component comp : getComponents() ) {
////			if ( comp instanceof InputField ) {
////				retVal.add(((InputField) comp).getText());
////			} else if ( comp instanceof Panel ) {
////				retVal.addAll( ( (Panel) comp ).getTextFieldVals() );
////			}
////		}
//		return retVal;
//	}
