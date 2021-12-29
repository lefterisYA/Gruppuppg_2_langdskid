package ui;

public class UserInput<T> {
	T value;
	
	public UserInput() {
//		this.value = value;
	}
	
	public T getValue(){
		return value;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public String getStrgValu() {
		return (String) value;
	}
}
