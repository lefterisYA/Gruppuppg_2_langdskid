package ui;

import main.ProgLogic;
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
	public abstract String getUserStrng(String msg) throws InterruptedException;
	public abstract Double getUserDouble(String msg);
	public abstract void showIntroScreen();
	public abstract void clearMsgScreen();
	public abstract void showScreen(Screen newScreen);
	public abstract Screen getNextScreen() throws InterruptedException;
	public abstract void setNextScreen(Screen intro);
	public abstract void registerCallingThread(Thread thread);
	public abstract void registerCallingThread(ProgLogic progLogic);
}
