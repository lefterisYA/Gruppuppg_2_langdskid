package UI;

public interface GuiCallback {
	default void onSuccess() { };
	default void onCancel() { };
	default void onNewUsrInp(String val) { };
	default void onNewScrn(Screen newScrn) { };
}