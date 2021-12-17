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
	RGSTR_SKIER_REPEAT(true),
	RGSTR_SKIER_VERIFY(true),
	RGSTR_SKIER_FINISH(true),
	ACPT(true),
	BACK(true),
	EXIT(true);

	public final boolean isVirt;
	Screen(boolean isVirt){
		this.isVirt = isVirt;
	}
}