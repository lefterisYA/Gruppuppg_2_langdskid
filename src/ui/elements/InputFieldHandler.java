package ui.elements;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;

import ui.GUI;
import ui.GuiCallback;

public class InputFieldHandler {
	private final LinkedList<InputField> inpFlds;
	private final GUI ui;
			
	public InputFieldHandler(GUI ui) {
		inpFlds = new LinkedList<>();
		this.ui = ui;
	}
	
	public InputField gnrt(String label, InputField.Type type) {
		InputField newInpFld = new InputField(ui, type, true);
		inpFlds.add(newInpFld);

		return newInpFld;
	}

	public InputField gnrt(String label, InputField.Type type, boolean emptyAllowed, GuiCallback validityCback) {
		InputField newInpFld = new InputField(ui, type, emptyAllowed);
		inpFlds.add(newInpFld);
		
		newInpFld.addFocusListener( new FocusListener() {
			@Override public void focusLost(FocusEvent e) {
				if ( allFieldsAreValid() ) {
					validityCback.onValidFields();
				}
				else {
					validityCback.onInvalidFields();
				}
			}
			
			@Override public void focusGained(FocusEvent e) { }
		});
		
		return newInpFld;
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
}