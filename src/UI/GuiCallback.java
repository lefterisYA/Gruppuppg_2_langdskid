package UI;

public interface GuiCallback {
	void onSuccess();
	void onCancel();
	void onNewScrn(Screen newScrn);
	void onNewUsrInp(String val);
}