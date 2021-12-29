package main;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;

import skiing.GroupList;
import skiing.Skier;
import skiing.SkierList;
import ui.GUI;
import ui.GuiCallback;
import ui.Screen;
import ui.UI;
import ui.elements.InputField;

public class ProgLogic {
	private final GUI ui;
	GroupList group;
	String chosenGroup;

	private final SkierList skierList = new SkierList();
	private final LinkedList<String> uniqueClasses = new LinkedList<String>();
	List<String> groups;
	
	public static void main(String[] args) {
		ProgLogic gLogic = new ProgLogic(new GUI());
		gLogic.test();
	}
	
	private void test() {
		skierList.addSkiertoList( new Skier( "Left", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Otto", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Jessica", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Joacim", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "namn2", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn3", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn5", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn7", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn8", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn9", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Asa", "Laft", "dam", 23 ));
		skierList.addSkiertoList( new Skier( "Åsa", "Laft", "dam", 23 ));
		skierList.addSkiertoList( new Skier( "Britt", "Laft", "dam", 23 ));
		
		group = new GroupList();
		group.generateGroupList(skierList, "H33");
		group.generateGroupListTime(new int[] {10,00,00}, 30, 100);
		screenHandler(Screen.INTRO);
		
		System.out.println("skierList.getUniqueGroupsList():");
		for ( String group : skierList.getUniqueGroupsList() )
			System.out.println(group);

		System.out.println("Med SkierList skierList.getSkierList():");
		for ( Skier skier : skierList.getSkierList() ) {
			System.out.println(skier.getPlayerNumber() + " " + skier.getFirstName());
		}

		System.out.println("Med Grouplist group.getSkierList():");
		for ( Skier skier : group.getSkierList() ) {
			System.out.println(skier.getPlayerNumber() + " " + skier.getFirstName());
		}
	}

	public ProgLogic(GUI ui) {
		
		// TEMP CODE:
		uniqueClasses.add("D11");
		uniqueClasses.add("D61");
		uniqueClasses.add("D13");
		uniqueClasses.add("H11");
		uniqueClasses.add("H21");
		uniqueClasses.add("H13");
		this.ui=ui;

		GuiCallback cBack = new GuiCallback() {
			public void onNewScrn(Screen nextScrn) 		{ screenHandler(nextScrn); 		}
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
				ui.setTitle( "Välkommen" );
				ui.addButton( "Skapa tävling", 			Screen.CREATE_RACE, 		0, 1, true);
				ui.addButton( "Lägg till tävlande",		Screen.RGSTR_SKIER, 		1, 0, true);
				ui.addButton( "Visa slutresultat",		Screen.PRINT_STRTLIST,	 	1, 0, true);
				ui.addButton( "Se tävling", 			Screen.SEE_RACE,		 	-2, 1, true);
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
				@Override public void onClick(String temp)  	{ screenHandler(Screen.RGSTR_SKIER_FINISH);  }
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
			if ( isCallback ) {

				chosenGroup = usrReplies[0];
				System.out.println(chosenGroup + "Was chosen");

				group = new GroupList();
				group.generateGroupList(skierList, chosenGroup);

				ui.clrScrn();
				ui.clrUsrInpField();
				ui.update();
				ui.setTitle("Var god välj skid-klass");

				GuiCallback cbackCreateRaceR = new GuiCallback() {
					@Override public void onClick(String ignore)  	{ screenHandler(Screen.CREATE_RACE_2);  }
				};
				
				ui.addInpField("Ange starttid (Första åktid):", InputField.Type.STRNG, 1, 2, false);
				ui.addInpField("Ange startiinterfall:", InputField.Type.STRNG, 0, 1, true);
				ui.addInpField("Ange första skid-åkares nummer:", InputField.Type.INTGR, 0, 1, true);

				ui.addButton( "Avbryt",					Screen.BACK,	 	1, 0, true);
				ui.addButton( "Fortsätt",				cbackCreateRaceR, 		2, 0, true);

				break;
			} else {
				GuiCallback cbackCrtRace = new GuiCallback() {
					public void onClick(String label){
						screenHandler(Screen.CREATE_RACE, new String[] {label});

					}
				};
				
				ui.clrScrn();
				ui.clrUsrInpField();
				ui.update();
				
				List<String> groups = skierList.getUniqueGroupsList();
				
				int yRelPos=0;
				ui.setTitle("Var god välj skid-klass");
				for ( String skiGroup : groups ) {
					System.out.println(skiGroup);
					ui.addButton( skiGroup,					cbackCrtRace,	 	0, yRelPos++, true);
				}
				
			}
			
			break;

		case CREATE_RACE_2:
			String[] inpFldVals2 = ui.getInpFieldVals();
			for ( String rep : inpFldVals2 ){
				System.out.println(rep);
			}
			
			int[] parts = new int[3];
			int i = 0;
			for ( String part : inpFldVals2[0].split(":") )
				parts[i++]=Integer.parseInt(part);

			group.generateGroupListTime(
					parts, Integer.parseInt(inpFldVals2[1]), Integer.parseInt(inpFldVals2[2]) 
				);
			

			for ( Skier skr : group.getSkierList() )
				System.out.println(skr.getFirstName());
			screenHandler(Screen.INTRO);
			break;
			
		case SEE_RACE:
			ui.clrScrn();
			ui.clrUsrInpField();
			ui.update();
			ui.setTitle(chosenGroup);
			ui.runClock();
			
			ui.setBodyText(chosenGroup);
			
			GuiCallback chkPntCback = new GuiCallback() {
				@Override
				public void onClick(int skierNum) { ui.updateSkierLinepass(skierNum, GUI.Linetype.CHECKPOINT); }
			};
			GuiCallback fnshCback = new GuiCallback() {
				@Override
				public void onClick(int skierNum) { 
					ui.updateSkierLinepass(skierNum, GUI.Linetype.FINISHLINE); 
					}
			};
			
			
			ui.addVertSpcr(10, -2, 1, true);
			int skierNum=0;
			for ( Skier skier : skierList.getSkierList() ) {
				System.out.println("adding "+skier.getName() + " " + skier.getPlayerNumber());
				ui.addSeeRaceTableRow(skier.getFirstName(), skierNum++, chkPntCback, fnshCback, 0, 1, true);
			}

			ui.addButton( "Avbryt",					Screen.BACK,	 	-1, 1, true);
			ui.addButton( "Fortsätt",				Screen.BACK, 		1, 0, true);

			break;
			
		case SEE_RACE_UPDATE_SKIER:
			break;

		case RGSTR_SKIER_FINISH:
			String[] inpFldVals = ui.getInpFieldVals();
			String[] name = inpFldVals[0].split(" ");
			String firstName = name[0];
			String lastName = name.length > 1 ? name[name.length-1] : "";
			int age = Integer.parseInt(inpFldVals[2]);
			String gender = inpFldVals[1];

			skierList.addSkiertoList( new Skier( firstName, lastName, gender, age ));
			System.out.println(firstName+" "+age+" added!"+" It's a "+gender+"!");

		case RGSTR_SKIER_VERIFY:
			ui.setTitle("Klart?");
			
			ui.addButton( "Lägg till fler",	Screen.RGSTR_SKIER_REPEAT,	 	-3, 0, true);
			
			break;

		case PRINT_STRTLIST:
//			if ( isCallback ) {
//				screenHandler(Screen.INTRO); // next screen
//			} else {
//				screenHandler(scrn);
//			}
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
}