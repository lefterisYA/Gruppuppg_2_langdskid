package ui;

public class UserInput<T> {
	enum ValueType { STRING, INT };
	private final T value;
	private final ValueType type;
	
	@SuppressWarnings("unchecked")
	public UserInput(String value) {
		this.value = (T) value;
		type = ValueType.STRING;
	}

	@SuppressWarnings("unchecked")
	public UserInput(Integer value) {
		this.value = (T) value;
		type = ValueType.STRING;
	}
	
	public T getVal(){
		return value;
	}
	
	public int getIntValue(){
		return (int) value;
	}
	
	public String getStrVal() {
		return (String) value;
		
	}

	public ValueType getValueType() {
		return type;
	}
}
