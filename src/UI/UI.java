package UI;

import skiing.Skier;

public interface UI {
	static enum Type {
			GUI,
			CONSOLE,
			TEST
	};
//
//	public UI(Type type) {
//		newUi(type);
//	}
	
//	private void newUi(Type type) {
//		switch (type) {
//		case CONSOLE:
//			sc = new Scanner(System.in);
//			break;
//		case GUI:
//			sc = new Scanner(System.in);
//			break;
//		default:
//			sc = new Scanner(System.in);
//		}
//	}
	
	public abstract Skier addSkierDialog(int playerNumber);
	public abstract void postMsg(String msg);
	public abstract int getUserInt(String msg);
	public abstract String getUserStrng(String msg);
	public abstract Double getUserDouble(String msg);
}
