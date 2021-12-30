package ui;

@SuppressWarnings("unchecked")
public class UserInput<T> {
	enum ValueType { STR, INT, SCR };
	private final T value;
	private final ValueType type;
	
	public UserInput(String value) {
		this.value = (T) value;
		type = ValueType.STR;
	}

	public UserInput(Integer value) {
		this.value = (T) value;
		type = ValueType.STR;
	}

	public UserInput(Screen value) {
		this.value = (T) value;
		type = ValueType.STR;
	}
	
	public T getVal(){
		return value;
	}
	
	public int getInteger(){
		return (int) value;
	}
	
	public String getString() {
		return (String) value;
		
	}

	public Screen getScreen() {
		return (Screen) value;
		
	}

	public ValueType getValueType() {
		return type;
	}
}
