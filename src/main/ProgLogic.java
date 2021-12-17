package main;

import UI.GUI;
import UI.GuiCallback;
import UI.Screen;
import skiing.Skier;
import skiing.SkierList;

public class ProgLogic {
	private final GUI ui;
	UserReplyHandler repHand;

	public final SkierList skierList = new SkierList();

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
	
	// outgoing, show new screen
	public void screenHandler(Screen currScrn) { 
		screenHandler(currScrn, null); 
	}

	// ingoing, process/handle user inpu:
	public void usrInpFnsh(String[] usrReplies, Screen callScrn) {
		screenHandler(callScrn, usrReplies);
	}

	// both of above methods call the following so we have out/in gathered in the same switch case statements.
	private void screenHandler(Screen scrn, String[] usrReplies) {
//		System.out.println( Thread.currentThread().getStackTrace()[1] + ": " + ( currScrn != null ? currScrn.name() : "null" ));


		boolean isNewScrn = usrReplies == null ? false : true;
		
		switch (scrn) {
		case CREATE_RACE:
			if ( !isNewScrn ) {
				ui.showScreen(scrn);
				repHand.sendMsgsSync( scrn, new String[] { 
						"Välj klass:", 
						"Ange starttid", 
						"Ange startIntervall:", 
						"Ange första åkares startnummer" 
				});
			} else { 
				screenHandler(Screen.INTRO); // next screen
			}
			break;

		case INTRO:
			if ( !isNewScrn ) {
				ui.showScreen(scrn);
			} else {
				screenHandler(Screen.INTRO); // next screen
			}
			break;

		case PRINT_STRTLIST:
			if ( !isNewScrn ) {
				ui.showScreen(scrn);
			} else {
				screenHandler(Screen.INTRO); // next screen
			}
			break;
		
		case RGSTR_SKIER_REPEAT,RGSTR_SKIER_FINISH:
			if ( parseSkierArray() )
				if ( scrn == Screen.RGSTR_SKIER_FINISH )
					ui.showScreen(Screen.INTRO);
				else
					screenHandler(Screen.RGSTR_SKIER);
			else
				repHand.retry("Du måste ange åldern i siffor!");
			break;

		case RGSTR_SKIER:
			if ( !isNewScrn ) {
				ui.showScreen(scrn);
				repHand.sendMsgsSync( scrn, new String[] { 
						"Ange namn på tävlande:",
						"Ange kön på tävlande:",
						"Ange ålder på tävlande:"
				});
			} else {
				ui.showScreen(Screen.RGSTR_SKIER_VERIFY); // next screen
			}
			break;

		case EXIT:
			System.exit(0);

		case BACK:
			if ( !isNewScrn ) {
				ui.showScreen(ui.getLastScreen());
			} else {
				screenHandler(Screen.INTRO); // next screen
			}
			break;

		default:
			break;
		}
	}
	

	private boolean parseSkierArray() {
		String[] usrReplies = repHand.getPrevUserReplies();
		try {
			int age = Integer.parseInt(usrReplies[2]);

			String[] name = usrReplies[0].split(" ");
			String firstName = name[0];
			String lastName = name.length > 1 ? name[name.length-1] : "";

			skierList.addSkiertoList( new Skier( firstName, lastName, usrReplies[1], age ));
			System.out.println(name+" "+age+" added!");
			return true;
		} catch (Exception e) {
			System.out.println(usrReplies[2]+" failed to parse int!!!");
			return false;
		}
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
	
	public void sendMsgsAsync(Screen callScrn, String[] msgs) {
		// TODO
	}

	public void sendMsgsSync(Screen callScrn, String[] msgs) {
		usrMsgs = msgs;
		usrRpls = new String[usrMsgs.length];
		msgIdx=0;
		this.callScrn = callScrn;
		ui.getUserStrng(usrMsgs[0]);
	}
	
	public void retry(String newMsg) {
		ui.getUserStrng(newMsg);
	};
	
	public String[] getPrevUserReplies() {
		return usrRpls;
	}
	public void guiCallback(String val) {
		usrRpls[msgIdx] = val;
		if ( msgIdx < usrMsgs.length-1 )
			ui.getUserStrng(usrMsgs[++msgIdx]);
		else
			lgc.usrInpFnsh(usrRpls, callScrn);
	}
//			for ( int i=0; i<usrRpls.length; i++) {
//				System.out.println(usrRpls[i]);
//			}
}