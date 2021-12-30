package ui.interfaces;

abstract public class FieldValidator {
	public enum Type { STR, INT, FLT };
	private Type type;
	public boolean allowBlank;
	
	public FieldValidator(boolean allowBlank, Type type) {
		allowBlank = false;
		type = Type.STR;
	}
	
	abstract public void onValidFields(String rawFldTxt);
	abstract public void onInvalidFields(String rawFldTxt);

	public void validate( String rawFldTxt ) {
		if ( blankChecker( rawFldTxt ) || txtFldValidator( rawFldTxt ) )
			onValidFields(rawFldTxt);
		else 
			onInvalidFields(rawFldTxt);
	}

	private boolean txtFldValidator( String rawFldTxt ) {
		switch (type) {
		case STR:
			return stringValidator(rawFldTxt);
		case INT:
			return integerValidator(rawFldTxt);
		case FLT:
			return floatValidator(rawFldTxt);
		}

		return false;
	}

	private boolean blankChecker(String rawFldTxt) { 
		return allowBlank || !rawFldTxt.isEmpty();
	}

	private boolean stringValidator( String rawFldTxt ) {
		return rawFldTxt.matches("^[A-za-z ]+$");
	}

	private boolean integerValidator( String rawFldTxt ) {
		try {
			Integer.parseUnsignedInt(rawFldTxt);
		} catch ( Exception e ) {
			return false;
		}
		return true;
	}
	private boolean floatValidator( String rawFldTxt ) {
		try {
			Double.parseDouble(rawFldTxt);
		} catch ( Exception e ) {
			return false;
		}
		return true;
	}
}
