package main;

import java.awt.Component;

import javax.swing.JComponent;

import UI.GUI;
import UI.GuiCallback;
import UI.Screen;
import UI.InputField;
import skiing.Skier;
import skiing.SkierList;

public class ProgLogic {
	private final GUI ui;
	UserReplyHandler repHand;

	public final SkierList skierList = new SkierList();

	public ProgLogic(GUI ui) {
		this.ui=ui;
		repHand = new UserReplyHandler(ui, this);

		GuiCallback cBack = new GuiCallback() {
			public void onNewScrn(Screen nextScrn) 		{ screenHandler(nextScrn); 		}
			public void onNewUsrInp(String val) 		{ repHand.guiCallback(val); 	}
		};
		ui.rgsrCallback( cBack );
	}
	
	public void run() {
		screenHandler(Screen.INTRO); // next screen
	}
	
	// outgoing, show new screen
	public void screenHandler(Screen currScrn) { 
		screenHandler(currScrn, null); 
	}

	// ingoing, get/process/handle user input:
	public void usrInpFnsh(String[] usrReplies, Screen callScrn) {
		screenHandler(callScrn, usrReplies);
	}

	// both of methods above call the following. This is just so we have outgoing and ingoing information from the user
	// gathered in the same switch case statements.
	private void screenHandler(Screen scrn, String[] usrReplies) {
		System.out.println( Thread.currentThread().getStackTrace()[1] + ": " + ( scrn != null ? scrn.name() : "null" ));

		// set to true when called by GUI. Eventual user processing/verifying/handling happens when its true.
		boolean isCallback = usrReplies == null ? false : true; 
		
		ui.addToScreenStack(scrn);
		
		switch (scrn) {
		case INTRO:
			if ( isCallback ) { 				// Nothing to be done,
				screenHandler(Screen.INTRO); 	// just show next screen.
			} else {
				ui.clrScrn();
				ui.setTitle("Välkommen");
				ui.addButton( "Skapa tävling", 			Screen.CREATE_RACE, 		0, 1, true);
				ui.addButton( "Lägg till tävlande",		Screen.RGSTR_SKIER, 		1, 0, true);
				ui.addButton( "Visa slutresultat",		Screen.PRINT_STRTLIST,	 	1, 0, true);
				ui.addButton( "Se tävling", 			Screen.PRINT_STRTLIST,	 	-2, 1, true);
				ui.addButton( "Starta klocka",			ui.clockStart, 				1, 0, true);
				ui.addButton( "WIP",					Screen.PRINT_STRTLIST,	 	1, 0, true);
				ui.addVertSpcr(400);
				ui.addButton( "OK",						Screen.ACPT, 				-2, 1, true);
				ui.addButton( "Avsluta",				Screen.EXIT, 				2, 0, true);

				ui.setBodyText("Var god gör ett val:");
				ui.addVertSpcr(1);
				ui.update();
			}
			break;

		case RGSTR_SKIER_REPEAT: // RGSTR_SKIER,CREATE_RACE: fallthrough too:
		case RGSTR_SKIER:
			if ( isCallback ) {
				screenHandler(Screen.RGSTR_SKIER_VERIFY);
			} else {
				ui.clrScrn();
				ui.update();
			}

			ui.clrUsrInpField();
			ui.setTitle(scrn == Screen.CREATE_RACE ? "Ny tävling" : "Registrera tävlande");

			ui.addVertSpcr(20);

			GuiCallback onClickCback = new GuiCallback() {
				@Override public void onClick()  	{ screenHandler(Screen.RGSTR_SKIER_FINISH);  }
			};
			ui.addButton( "Lägg Till",				onClickCback,	 	-3, 3, true);
			Component lastComp = ui.getLastComp();
			lastComp.setEnabled(false);
			GuiCallback cback = new GuiCallback() {
				@Override public void onValidFields()  	{ lastComp.setEnabled(true);  }
				@Override public void onInvalidFields() { lastComp.setEnabled(false); }
			};

			ui.addButton( "Avbryt",					Screen.BACK,	 	1, 0, true);
			ui.addButton( "Avsluta",				Screen.EXIT, 		2, 0, true);

			ui.addInpField("Namn på tävlande:", InputField.Type.STRNG, false, cback, 1, 2, false);
			ui.addInpField("Kön:", InputField.Type.STRNG, false, cback, 0, 1, true);
			ui.addInpField("Ålder:", InputField.Type.INTGR, false, cback, 0, 1, true);
			ui.addVertSpcr(200);

			ui.update();
			break;

		case CREATE_RACE:
			break;

		case RGSTR_SKIER_FINISH:
			String[] inpFldVals = ui.getInpFieldVals();
			String[] name = inpFldVals[0].split(" ");
			String firstName = name[0];
			String lastName = name.length > 1 ? name[name.length-1] : "";
			int age = Integer.parseInt(inpFldVals[2]);
			String gender = inpFldVals[1];

			skierList.addSkiertoList( new Skier( firstName, lastName, gender, age ));
			System.out.println(firstName+" "+age+" added!"+" It's a"+gender+"!");

		case RGSTR_SKIER_VERIFY:
			ui.setTitle("Klart?");
			
			ui.addButton( "Lägg till fler",	Screen.RGSTR_SKIER_REPEAT,	 	-3, 0, true);
			break;

		case PRINT_STRTLIST:
			if ( !isCallback ) {
				screenHandler(scrn);
			} else {
				screenHandler(Screen.INTRO); // next screen
			}
			break;
		
		case EXIT:
			System.exit(0);

		case BACK:
			if ( !isCallback ) {
				screenHandler(ui.getLastScreen());
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