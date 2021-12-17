package main;

import UI.GUI;
import UI.GuiCallback;
import UI.Screen;

public class ProgLogic {
	private final GUI ui;
	static Thread runningThread;
	UserReplyHandler repHand;

	public ProgLogic(GUI ui) {
		this.ui=ui;
		repHand = new UserReplyHandler(ui, this);

		ui.rgsrCallback( new GuiCallback() {
			public void onNewScrn(Screen nextScrn) { screenHandler(nextScrn); }
			public void onNewUsrInp(String val) { repHand.guiCallback(val); }
			public void onSuccess() { }
			public void onCancel() { }
		} );
		
	}
	
	public void run() {
		ui.showScreen(Screen.INTRO);
	}
	
	public void screenHandler(Screen currScrn) {
//		System.out.println( Thread.currentThread().getStackTrace()[1] + ": " + ( currScrn != null ? currScrn.name() : "null" ));
		switch (currScrn) {
		case CREATE_RACE:
			ui.showScreen(currScrn);
			repHand.sendMsgs( currScrn, new String[] { 
					"Ange klass:", 
					"Ange starttid", 
					"Ange startIntervall:", 
					"Ange första startnummer" 
			});
			break;

		case INTRO:
			ui.showScreen(currScrn);
			// NOT ACTUALLY NEEDED!
			break;

		case PRINT_STRTLIST:
			ui.showScreen(currScrn);
			// TODO
			break;

		case RGSTR_SKIER:
			ui.showScreen(currScrn);
			repHand.sendMsgs( currScrn, new String[] { 
					"Ange namn på tävlande.",
					"Ange tävlandes klubb" 
			});
			break;


		case EXIT:
			System.exit(0);

		case BACK:
			ui.showScreen(ui.getLastScreen());
			break;

		default:
			break;
		}
	}
	
	public void usrInpFnsh(String[] messages, Screen callScrn) {
		Screen nextScrn = null;
		
		switch (callScrn) {
		case CREATE_RACE:
			nextScrn = Screen.INTRO;
			break;

		case INTRO:
			// NOT ACTUALLY NEEDED!
			break;

		case PRINT_STRTLIST:
			// TODO
			break;

		case RGSTR_SKIER:
			nextScrn = Screen.INTRO;
			break;

		default:
			nextScrn = Screen.INTRO;
			break;
		}
		screenHandler(nextScrn);
	}

}

class UserReplyHandler {
	String[] usrMsgs;
	String[] usrRpls;
	int msgIdx;
	GUI ui;
	ProgLogic lgc;
	Screen callScrn;
	
	public UserReplyHandler(GUI ui, ProgLogic lgc) {
		this.ui		=	ui;
		this.lgc 	= 	lgc;
	}
	
	public void sendMsgs(Screen callScrn, String[] msgs) {
		usrMsgs = msgs;
		usrRpls = new String[usrMsgs.length];
		msgIdx=0;
		ui.getUserStrng(usrMsgs[0]);
		this.callScrn = callScrn;
	}
	
	public void guiCallback(String val) {
		usrRpls[msgIdx] = val;
		if ( msgIdx < usrMsgs.length-1 )
			ui.getUserStrng(usrMsgs[++msgIdx]);
		else
			lgc.usrInpFnsh(usrRpls, callScrn);
//			for ( int i=0; i<usrRpls.length; i++) {
//				System.out.println(usrRpls[i]);
//			}
	}
}