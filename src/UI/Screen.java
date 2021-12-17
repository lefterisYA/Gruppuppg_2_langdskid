package UI;

public enum Screen {
	INTRO(true),
	CREATE_RACE(false),
	PRINT_STRTLIST(false),
	EDIT_CHECKPOINTS(false),
	SEE_RACE(false),
	CHECKPOINT_WATCHER(false),
	FINISHING_WATCHER(false),
	RGSTR_SKIER(false),
	ACPT(true),
	BACK(true),
	RGSTR_SKIER_VERIFY(true),
	EXIT(true);

	public final boolean isVirt;
	Screen(boolean isVirt){
		this.isVirt = isVirt;
	}
}