package UI;

public interface GuiCallback {
	default void onClick(String buttonLabel) { };
	default void onCancel() { };
	default void onNewUsrInp(String val) { };
	default void onNewScrn(Screen newScrn) { };
	default void onValidFields() { };
	default void onInvalidFields() { };
}