package UI;

import skiing.Skier;

public interface UI {
	static enum Type {
			GUI,
			CONSOLE,
			TEST
	};
	
	public abstract Skier addSkierDialog(int playerNumber);
	public abstract void postMsg(String msg);
	public abstract int getUserInt(String msg);
	public abstract String getUserStrng(String msg);
	public abstract Double getUserDouble(String msg);
	public abstract void showIntroScreen();
	public abstract void clearMsgScreen();
	//public abstract void showScreen(Screen newScreen);
	//public abstract Screen getNextScreen();
}
