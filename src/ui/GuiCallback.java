package ui;

public interface GuiCallback<T> {
	public void onClick(T val);
//	default void onClick(String label) { };
//	default void onClick(Screen newScreen) { };
//	default void onClick(int val) { };

//	default void onCancel() { };
//	default void onNewUsrInp(String val) { };
//	default void onNewScrn(Screen newScrn) { };
}

interface NewScrnCback {
	default void onNewScrn(Screen newScrn) { };
}