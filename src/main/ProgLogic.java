package main;

import UI.GUI;
import UI.GuiCallback;
import UI.Screen;

public class ProgLogic {
	private final GUI ui;
	static Thread runningThread;
	UserReplyHandler repHand;
	Screen nextScrn;

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
		ui.showScreen(currScrn);
		switch (currScrn) {
		case CREATE_RACE:
			repHand.sendMsgs( new String[] { 
					"Ange klass:", 
					"Ange starttid", 
					"Ange startIntervall:", 
					"Ange första startnummer" 
			});
			nextScrn = Screen.INTRO;
			break;

		case INTRO:
			break;

		case PRINT_STRTLIST:
			break;

		case RGSTR_SKIER:
			repHand.sendMsgs( new String[] { 
					"Ange namn på tävlande.",
					"Ange tävlandes klubb" 
			});

			nextScrn = Screen.INTRO;
			break;

		case EXIT:
			System.exit(0);

		case ACPT:
			System.out.println("WIP: ACPT pressed");
			break;

		case BACK:
			System.out.println("");
			break;

		default:
			break;
		}
	}
	
	public void usrInpFnsh(String[] messages) {
		
		screenHandler(nextScrn);
	}
}

class UserReplyHandler {
	String[] usrMsgs;
	String[] usrRpls;
	int msgIdx;
	GUI ui;
	ProgLogic lgc;
	
	public UserReplyHandler(GUI ui, ProgLogic lgc) {
		this.ui		=	ui;
		this.lgc 	= 	lgc;
	}
	
	public void sendMsgs(String[] msgs) {
		usrMsgs = msgs;
		usrRpls = new String[usrMsgs.length];
		msgIdx=0;
		ui.getUserStrng(usrMsgs[0]);
	}
	
	public void guiCallback(String val) {
		usrRpls[msgIdx] = val;
		if ( msgIdx < usrMsgs.length-1 )
			ui.getUserStrng(usrMsgs[++msgIdx]);
		else
			lgc.usrInpFnsh(usrRpls);
//			for ( int i=0; i<usrRpls.length; i++) {
//				System.out.println(usrRpls[i]);
//			}
	}
}