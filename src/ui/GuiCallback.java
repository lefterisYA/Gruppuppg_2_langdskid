package ui;

public interface GuiCallback {
	default void onClick(String buttonLabel) { };
	default void onClick(int val) { };
	default void onCancel() { };
	default void onNewUsrInp(String val) { };
	default void onNewScrn(Screen newScrn) { };
}