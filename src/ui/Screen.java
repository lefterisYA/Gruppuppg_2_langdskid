package ui;

public enum Screen {
	INTRO(true),
	CREATE_RACE(false),
	CREATE_RACE_2(false),
	PRINT_STRTLIST(false),
	EDIT_CHECKPOINTS(false),
	SEE_RACE(false),
	SET_SKIER_MIDTIME(true),
	SET_SKIER_FINTIME(true),
	CHECKPOINT_WATCHER(false),
	FINISHING_WATCHER(false),
	RGSTR_SKIER(false),
	RGSTR_SKIER_REPEAT(true),
	RGSTR_SKIER_VERIFY(true),
	RGSTR_SKIER_FINISH(true),
	LIVE_SCOREBOARD(false),
	FINISH_SCOREBOARD(false),
	ACPT(true),
	BACK(true),
	EXIT(true);

	public final boolean isVirt;
	Screen(boolean isVirt){
		this.isVirt = isVirt;
	}
}