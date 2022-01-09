package main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JButton;

import skiing.Group;
import skiing.Skier;
import skiing.SkierHandler;
import timekeeping.Time;
import ui.ElmntPos;
import ui.GUI;
import ui.interfaces.GuiCallback;
import ui.Screen;
import ui.elements.Button;
import ui.interfaces.FieldValidator;
import ui.interfaces.FieldValidator.Type;

public class ProgLogic {
	private final GUI ui;
	String chosenGroup;

	Group group;
	private final SkierHandler skierList = new SkierHandler();
	List<String> groups;

	public static void main(String[] args) {
		ProgLogic gLogic = new ProgLogic();
		gLogic.test();
	}

	private void test() {
//		Clock clk = new Clock();
//		int[] test = clk.g


//		skierList.addSkiertoList( new Skier( "Jessica", "Laft", "herr", 33 ));
//		skierList.addSkiertoList( new Skier( "Joacim", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Lefte", "Laft", "herr", 30 ));
		skierList.addSkiertoList( new Skier( "Ottom", "Laft", "herr", 31 ));
		skierList.addSkiertoList( new Skier( "namn2", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn3", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn5", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn7", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn8", "Laft", "herr", 33 ));
		skierList.addSkiertoList( new Skier( "Namn9", "Laft", "herr", 33 ));
//		skierList.addSkiertoList( new Skier( "Asa", "Laft", "dam", 23 ));
//		skierList.addSkiertoList( new Skier( "Åsa", "Laft", "dam", 23 ));
		skierList.addSkiertoList( new Skier( "Britt", "Laft", "dam", 23 ));

		skierList.generateAllGroups();
		group = skierList.getGroup("H33");
//		group.generateGroupList(skierList, "H33");
		group.generateGroupListTime(new Time(10,0,0), new Time(30, Time.Unit.SECONDS), 100);
		screenHandler(Screen.INTRO);

		System.out.println("skierList.getUniqueGroupsList():");
		for ( String group : skierList.getUniqueGroupsList() )
			System.out.println(group);

		System.out.println("Med SkierList skierList.getSkierList():");
		for ( Skier skier : skierList.getSkierList() ) {
			System.out.println(skier.getPlayerNumber() + " " + skier.getFirstName() + "\t" + skier.getTimeHandler().getStartTime() );
		}

		System.out.println("Med Grouplist group.getSkierList():");
		for ( Skier skier : group.getSkierList() ) {
			System.out.println(skier.getPlayerNumber() + " " + skier.getFirstName() + "\t" + skier.getTimeHandler().getStartTime() );
		}
	}

	public ProgLogic() {
		GuiCallback<Screen> newScrnCback;
		newScrnCback = new GuiCallback<Screen>() {
			@Override public void onClick(Screen nextScreen) { screenHandler(nextScreen); }
		};

		GUI ui = new GUI(newScrnCback);
//		// TEMP CODE:
//		uniqueClasses.add("D11");
//		uniqueClasses.add("D61");
//		uniqueClasses.add("D13");
//		uniqueClasses.add("H11");
//		uniqueClasses.add("H21");
//		uniqueClasses.add("H13");
		this.ui=ui;
	}

	public void run() {
		screenHandler(Screen.INTRO); // next screen
	}

	// outgoing, show new screen
	public void screenHandler(Screen currScrn) {
		screenHandler(currScrn, null);
	}

	FieldValidator nmbrValidator;
	FieldValidator textValidator;
	FieldValidator timeValidator;
    Button reptBttn = null;
	Button acptBttn = null;
	Button backBttn = null;
	Map<GUI.UsrInpTypes, String[][]> usrInp;

	private void screenHandler(Screen scrn, String[] usrReplies) {
		System.out.println( Thread.currentThread().getStackTrace()[1] + ": " + ( scrn != null ? scrn.name() : "null" ));
		Button backBttn = ui.makeButton( "Avbryt", Screen.BACK);

		ui.addToScreenStack(scrn);

		switch (scrn) {
		case INTRO:
			ui.clrScrn();
			ui.setTitle( "Välkommen" );
            ui.clearBackHist();

			ui.addButton( "Skapa tävling", 			Screen.CREATE_RACE_PICK_GROUP, 		new ElmntPos( 0, 1, true));
			ui.addButton( "Lägg till tävlande",		Screen.RGSTR_SKIER, 				new ElmntPos( 1, 0, true));
			ui.addButton( "Visa slutresultat",		Screen.FINISH_SCOREBOARD, 			new ElmntPos( 1, 0, true));
			ui.addButton( "Se tävling", 			Screen.SEE_RACE,		 			new ElmntPos( 0, 1, false, true));
			ui.addButton( "Live-tavla",				Screen.LIVE_SCOREBOARD,				new ElmntPos( 1, 0, true));
			ui.addButton( "Visa skidåkare",    		Screen.SHOW_SKIER_INPUT,   			new ElmntPos( 1, 0, true));
			ui.addButton( "Visa startlista",		Screen.PRINT_STRTLIST,	 			new ElmntPos( 0, 1, false, true));
			ui.addButton( "WIP",					Screen.PRINT_STRTLIST,	 			new ElmntPos( 1, 0, true));
			ui.addButton( "WIP",					Screen.PRINT_STRTLIST,	 			new ElmntPos( 1, 0, true));
			ui.addVertSpcr(400);
			ui.addButton( "OK",						Screen.ACPT, 						new ElmntPos( 0, 1, false, true));
			ui.addButton( "Avsluta",				Screen.EXIT, 						new ElmntPos( 2, 0, true));

			ui.setBodyText("Var god gör ett val:");
			ui.addVertSpcr(1);
			ui.update();
			break;

		case RGSTR_SKIER:
			ui.clrScrn();
			ui.setTitle("Lägg till skid-åkare.");
			ui.addVertSpcr(20);

			acptBttn = ui.makeButton( "Lägg Till",	Screen.RGSTR_SKIER_FINISH);
            reptBttn = ui.makeButton( "Lägg till fler", Screen.RGSTR_SKIER_REPEAT);
			acptBttn.setEnabled(false);

			textValidator = new FieldValidator(false, Type.STR) {
				@Override public void onValidFields(String rawFldTxt)   	{ acptBttn.setEnabled(true);  }
				@Override public void onInvalidFields(String rawFldTxt)   	{ acptBttn.setEnabled(false);  }
			};

			nmbrValidator = new FieldValidator(false, Type.INT) {
				@Override public void onValidFields(String rawFldTxt)   	{ acptBttn.setEnabled(true);  }
				@Override public void onInvalidFields(String rawFldTxt)   	{ acptBttn.setEnabled(false);  }
			};

			ui.addInpField("Namn på tävlande:", textValidator, new ElmntPos(0, 1, 3, false, true));

			String[][] genders = {{"Herr", "Dam"}};
			ui.addDropdown("Kön:", genders, new ElmntPos(0, 1, 3, false, true), 300, 30);

			ui.addInpField("Ålder:", 			nmbrValidator, new ElmntPos(0, 1, 3, false, true));
			ui.addVertSpcr(200);

			ui.addButton( backBttn,						new ElmntPos(1, 1, false, true));
			ui.addButton( acptBttn,			 			new ElmntPos(1, 0, true, true));

			ui.update();
			break;

        case RGSTR_SKIER_REPEAT:
            ui.setTitle("Lägg till skid-åkare.");
            ui.remove(reptBttn);
            ui.addButton( acptBttn,			 			new ElmntPos(0, 0, true));
            ui.getInputFieldHandler().clrInpFlds();
            ui.update();
            break;

		case RGSTR_SKIER_FINISH:
			usrInp = ui.getUsrInp();

			String sex = usrInp.get(GUI.UsrInpTypes.dropDown)[0][0];

			String[] name = usrInp.get(GUI.UsrInpTypes.inputFld)[0][0].split(" ");
			String firstName = name[0];
			String lastName = name.length > 1 ? name[name.length-1] : "";

            int age = Integer.parseInt( usrInp.get(GUI.UsrInpTypes.inputFld)[0][1] );
			skierList.addSkiertoList( new Skier( firstName, lastName, sex, age ));
			System.out.println(firstName+" "+age+" added!"+" It's a "+sex+"!");

        // FALLTHROUGH!
		case RGSTR_SKIER_VERIFY:
			ui.setTitle("Tillagd! Lägg till fler?");
			ui.remove(acptBttn);
			ui.addButton( reptBttn, new ElmntPos(0, 0, true));
            // ui.swap(acptBttn, reptBttn);
            ui.getInputFieldHandler().disableInpFlds();
            ui.update();

			break;

		case CREATE_RACE_PICK_GROUP:
			ui.clrScrn();

			ui.setTitle("Var god välj skid-klass");
			for ( String group : skierList.getUniqueGroupsList() )
				ui.addButton( group,				Screen.CREATE_RACE,	 	new ElmntPos(0, 1, true));

			ui.addVertSpcr(200);
			ui.addButton( backBttn,						new ElmntPos(0, 1, false, true));

			ui.update();
			break;

        case SHOW_SKIER_INPUT:
            ui.clrScrn();
            nmbrValidator = new FieldValidator(false, Type.INT) {
                @Override public void onValidFields(String rawFldTxt)   	{ acptBttn.setEnabled(true);  }
                @Override public void onInvalidFields(String rawFldTxt)   	{ acptBttn.setEnabled(false);  }
                };

            acptBttn = ui.makeButton( "Fortsätt",	Screen.SHOW_SKIER);
            acptBttn.setEnabled(false);

            ui.setTitle("Visa skidåkare");
            ui.addInpField("Skidåkar-nummer", nmbrValidator, new ui.ElmntPos(0, 1, false, true));
            ui.addVertSpcr(200);
            ui.addButton( backBttn,						new ElmntPos(0, 1, false, true));
            ui.addButton( acptBttn,						new ElmntPos(1, 0, true, true));

            ui.update();
            break;

        case SHOW_SKIER:
            usrInp = ui.getUsrInp();
            ui.clrScrn();

            int skiNmbr = Integer.parseInt(usrInp.get(GUI.UsrInpTypes.inputFld)[0][0]);
            Skier matchedSkier = null;
            for ( Skier skier : skierList.getSkierList() ){
                if ( skier.getPlayerNumber() == skiNmbr )
                    matchedSkier = skier;
            }
            // Skier chosenSkier = skierList.getSkier(skiNmbr);

            String timeInfo = "";
            if ( matchedSkier.getTimeHandler().hasFinished() && matchedSkier.getTimeHandler().hasFinished() ){
                timeInfo =  "Starttid:" + matchedSkier.getTimeHandler().getStartTime() + "\n"
                            + "Sluttid:" + matchedSkier.getTimeHandler().getFinishTime() + "\n"
                        + "Total tid:" + matchedSkier.getTimeHandler().getRunningTimeToFinish() + "\n";
            }

            ui.setBodyText( "Namn:" + matchedSkier.getName() + "\n" + timeInfo);

            ui.addVertSpcr(200);
            ui.addButton( backBttn,						new ElmntPos(0, 1, false, true));

            ui.update();
            break;

		case CREATE_RACE:
			// chosenGroup = ui.butt
            chosenGroup = ui.getUsrInp().get(GUI.UsrInpTypes.bttnPrsd)[0][0];
			System.out.println(chosenGroup + "Was chosen");

			group = new Group(chosenGroup);
			group.generateGroupList(skierList, chosenGroup);

			ui.clrScrn();
            ui.setTitle("Var god fyll i");

			acptBttn = ui.makeButton( "Fortsätt",	Screen.CREATE_RACE_2);
			acptBttn.setEnabled(false);

			timeValidator = new FieldValidator(false, Type.STR) {
				@Override public void onValidFields(String rawFldTxt)   	{ acptBttn.setEnabled(true);  }
				@Override public void onInvalidFields(String rawFldTxt)   	{ acptBttn.setEnabled(false);  }

				@Override
				protected boolean stringValidator(String rawFldTxt) {
					return rawFldTxt.matches("([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]");
				}
			};

			nmbrValidator = new FieldValidator(false, Type.INT) {
				@Override public void onValidFields(String rawFldTxt)   	{ acptBttn.setEnabled(true);  }
				@Override public void onInvalidFields(String rawFldTxt)   	{ acptBttn.setEnabled(false);  }
			};

			List<String> nums = IntStream.range(0, 60).boxed().map(x -> String.format("%02d", x)).collect(Collectors.toList());
			String[] sixty = nums.toArray(new String[nums.size()]);
			String[] twentyfour = nums.subList(0, 24).toArray(new String[23]);
			String[][] usrChoices = new String[][] { twentyfour, sixty, sixty }	;

			ui.addDropdown("Ange starttid (Första åktid):", usrChoices, new ElmntPos(0,1, false, true), 350, 30);
			ui.addDropdown("Ange startintervall:", usrChoices, new ElmntPos(0,1, false, true), 300, 30);

			ui.addInpField("Ange första skid-åkares nummer:",	nmbrValidator, new ElmntPos(0,1, false, true));

			ui.addButton( "Avbryt",		Screen.BACK, 	new ElmntPos(0, 1, false, true));
			ui.addButton( acptBttn, 					new ElmntPos(1, 0, true, true));

			ui.update();
			break;

		case CREATE_RACE_2:
//			String[] inpFldVals2 = ui.getInpFieldVals();
			usrInp = ui.getUsrInp();
			Time startTime = new Time( usrInp.get(GUI.UsrInpTypes.dropDown)[0] );
            Time startInterval = new Time( usrInp.get(GUI.UsrInpTypes.dropDown)[1] );
			int firstNumber = Integer.parseInt(usrInp.get(GUI.UsrInpTypes.inputFld)[0][0]);

			group.generateGroupListTime( startTime, startInterval, firstNumber );

			for ( Skier skr : group.getSkierList() )
				System.out.println(skr.getFirstName());

			screenHandler(Screen.INTRO);
			break;

		case SEE_RACE:
			GuiCallback<Integer> chkPntCback = new GuiCallback<Integer>() {

				@Override
				public void onClick(Integer skierNum) {
					Skier skier = group.getSkierFromPlayerNumber(skierNum);
					skier.getTimeHandler().setCheckPointTime();
					// Time chkpntTime = skier.getTimeHandler().getCheckPointTime();
                    Time runningTime = skier.getTimeHandler().getRunningTimeToCheckpoint();
					( (JButton) ui.getButtonTable().getTblCmp(skierNum, 0) ).setText(runningTime.toString());
					( (JButton) ui.getButtonTable().getTblCmp(skierNum, 0) ).setEnabled(false);
				}
			};

			GuiCallback<Integer> fnshCback = new GuiCallback<Integer>() {
				@Override
				public void onClick(Integer skierNum) {
//					group.getSkierFromPlayerNumber(skierNum).setGoalTime(ui.getCurrTimeInts());
//					int[] arr =  group.getSkierFromPlayerNumber(skierNum).getGoalTimeFinish();
//					( (JButton) ui.getButtonTable().getTblCmp(skierNum, 1) ).setText(String.format("%02d:%02d:%02d", arr[0], arr[1], arr[2]));
					Skier skier = group.getSkierFromPlayerNumber(skierNum);
					skier.getTimeHandler().setFinishTime();
					// Time finishTime = skier.getTimeHandler().getFinishTime();
                    Time runningTime = skier.getTimeHandler().getRunningTimeToFinish();
					( (JButton) ui.getButtonTable().getTblCmp(skierNum, 1) ).setText(runningTime.toString());
					( (JButton) ui.getButtonTable().getTblCmp(skierNum, 1) ).setEnabled(false);
				}
			};

			ui.clrScrn();
			ui.setTitle(chosenGroup);
			ui.setBodyText(chosenGroup);
			ui.runClock();

			ui.addButtonTable( group.getSkierList().length, 0, 1, true );
			for ( Skier skier : group.getSkierList() ) {
				System.out.println("adding "+skier.getName() + " " + skier.getPlayerNumber());
				ui.getButtonTable().addRow(skier.getFirstName(), skier.getPlayerNumber(), chkPntCback, fnshCback);

			}

			ui.addButton( "Bakåt",					Screen.BACK,	 		new ElmntPos(0, 3, false));
			ui.addButton( "Fortsätt",				Screen.LIVE_SCOREBOARD,	new ElmntPos(1, 3, false));

			ui.update();
			break;

		case LIVE_SCOREBOARD:
			ui.clrScrn();

			ui.setTitle(chosenGroup);
			ui.setBodyText(chosenGroup);
			ui.addTable(new String[] { "Åkarnummer", "Namn", "Starttid", "Mellanmål", "Slutmål", "Total tid" }, 0, 1, true);

			group.sortSkierListGoalTime();

			for ( Skier skier : group.getSkierList() ) {
				System.out.println("adding "+skier.getName() + " " + skier.getPlayerNumber());
				String startingTime = skier.getTimeHandler().getStartTime().toString();
				String checkPTime = skier.getTimeHandler().getCheckPointTime().toString();
				String finishTime = skier.getTimeHandler().getFinishTime().toString();
				String totalTime = skier.getTimeHandler().getRunningTimeToFinish().toString();

				String skierNumber = String.valueOf(skier.getPlayerNumber());
				ui.getTextTable().addTableRow(new String[] { skierNumber, skier.getFirstName(), startingTime, checkPTime, finishTime, totalTime }); // , 0, 1, true);
			}
			ui.addButton( "Bakåt",					Screen.BACK,	 	new ElmntPos(0, 1, true));
			ui.addButton( "Huvudmeny",				Screen.INTRO,	 	new ElmntPos(1, 0, true));
			ui.update();

			break;

		case FINISH_SCOREBOARD:
			ui.clrScrn();

			ui.setTitle("Slut-resultat:");
			ui.setBodyText(chosenGroup);
			ui.addTable(new String[] { "Åkarnummer", "Namn", "Mellanmål", "Slutmål", "Total tid" }, 1, 1, true);

			group.sortSkierListCheckpointTime();

			for ( Skier skier : group.getSkierList() ) {
//				String checkPTime = String.format("%02d:%02d:%02d",
//						skier.getCheckpointTime()[0] , skier.getCheckpointTime()[1] , skier.getCheckpointTime()[2] );

				System.out.println("adding "+skier.getName() + " " + skier.getPlayerNumber());
				String checkPTime = skier.getTimeHandler().getCheckPointTime().toString();
				String finishTime = skier.getTimeHandler().getFinishTime().toString();
				String skierNumber = String.valueOf(skier.getPlayerNumber());
                String runningTime = skier.getTimeHandler().getRunningTimeToFinish().toString();

				ui.getTextTable().addTableRow(new String[] { skierNumber, skier.getFirstName(), checkPTime, finishTime, runningTime }); // , 0, 1, true);
			}
			ui.addButton( "Bakåt",					Screen.BACK,	 	new ElmntPos(0, 1, true));
			ui.addButton( "Huvudmeny",				Screen.INTRO,	 	new ElmntPos(1, 0, true));
			ui.update();

			break;

		case PRINT_STRTLIST:
            ui.clrScrn();

            ui.setTitle("Start-lista");
            // ui.setBodyText(chosenGroup);
            ui.addTable(new String[] { "Åkarnummer", "Namn", "Starttid", }, 1, 1, true);

            group.sortSkierListStartTime();

            for ( Skier skier : group.getSkierList() ) {
                System.out.println("adding "+skier.getName() + " " + skier.getPlayerNumber());
                String skierNumber = String.valueOf(skier.getPlayerNumber());
                String skierStTime = skier.getTimeHandler().getStartTime().toString();

                ui.getTextTable().addTableRow(new String[] { skierNumber, skier.getName(), skierStTime }); // , 0, 1, true);
            }
            ui.addButton( "Bakåt",					Screen.BACK,	 	new ElmntPos(0, 1, true));
            ui.addButton( "Huvudmeny",				Screen.INTRO,	 	new ElmntPos(1, 0, true));
            ui.update();
			break;

        case BACK:
            screenHandler(ui.getLastScreen());
            break;

		case EXIT:
			System.exit(0);

		default:
			break;
		}
	}
}
