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
import timekeeping.TimeHandler;
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
	FieldValidator nmbrValidator;
	FieldValidator textValidator;
	FieldValidator timeValidator;
    Button reptBttn = null;
	Button acptBttn = null;
	Button backBttn = null;
	Map<GUI.UsrInpTypes, String[][]> usrInp;
	Group group;

	private final SkierHandler skierHandler = new SkierHandler();
	List<String> groups;

	public ProgLogic() {
		GuiCallback<Screen> newScrnCback;
		newScrnCback = new GuiCallback<Screen>() {
			@Override public void onClick(Screen nextScreen) { screenHandler(nextScreen); }
		};

		GUI ui = new GUI(newScrnCback);
		this.ui=ui;
	}

	public void run() {
		screenHandler(Screen.INTRO); // next screen
	}

	private void screenHandler(Screen scrn) {
		Button backBttn = ui.makeButton( "Avbryt", Screen.BACK);

		ui.addToScreenStack(scrn);

		switch (scrn) {
		case INTRO:
			ui.clrScrn();
			ui.setTitle( "Välkommen" );
            ui.clearBackHist();

			ui.addButton( "Lägg till tävlande",		Screen.RGSTR_SKIER, 				new ElmntPos( 0, 1, true));
            ui.addButton( "Skapa tävling", 			Screen.CREATE_RACE_PICK_GROUP, 		new ElmntPos( 1, 0, true));
            ui.addButton( "Visa startlista",		Screen.PRINT_STRTLIST,	 			new ElmntPos( 1, 0, true));
			ui.addButton( "Se tävling", 			Screen.SEE_RACE,		 			new ElmntPos( 0, 1, false, true));
			ui.addButton( "Live-tavla",				Screen.LIVE_SCOREBOARD,				new ElmntPos( 1, 0, true));
			ui.addButton( "Visa skidåkare",    		Screen.SHOW_SKIER_INPUT,   			new ElmntPos( 1, 0, true));
			ui.addButton( "Fyll åkarlista automatiskt",	Screen.FILL_FAKE_DATA_SKIERS,	new ElmntPos( 0, 1, false, true));
            ui.addButton( "Fyll tävling automatiskt",	Screen.FILL_FAKE_DATA_RACE,	    new ElmntPos( 1, 0, true));
			ui.addVertSpcr(400);
			ui.addButton( "Avsluta",				Screen.EXIT, 						new ElmntPos( 0, 1, false, true));

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
			skierHandler.addSkiertoList( new Skier( firstName, lastName, sex, age ));

        // FALLTHROUGH!
		case RGSTR_SKIER_VERIFY:
			ui.setTitle("Tillagd! Lägg till fler?");
			ui.remove(acptBttn);
			ui.addButton( reptBttn, new ElmntPos(0, 0, true));
            // ui.swap(acptBttn, reptBttn);
            ui.getInputFieldHandler().disableInpFlds();
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
            for ( Skier skier : skierHandler.getSkierList() ){
                if (skier.getPlayerNumber() == skiNmbr) {
                    matchedSkier = skier;
                    break;
                }
            }

            String timeInfo = matchedSkier == null ? "Ingen träff!" :
                            "Namn: "       + matchedSkier.getName() + "\n"
                          + "Åkarnummer: " + matchedSkier.getPlayerNumber() + "\n";

            if ( matchedSkier != null && matchedSkier.getTimeHandler().hasFinished() && matchedSkier.getTimeHandler().hasFinished() ){
                timeInfo +=     "Starttid:" + matchedSkier.getTimeHandler().getStartTime() + "\n"
                               + "Sluttid:" + matchedSkier.getTimeHandler().getFinishTime() + "\n"
                             + "Total tid:" + matchedSkier.getTimeHandler().getRunningTimeToFinish() + "\n";
            }

            ui.setBodyText( timeInfo);
            ui.addVertSpcr(200);
            ui.addButton( backBttn,					            	new ElmntPos(0, 1, false, true));
            ui.addButton( "Huvudmeny",	Screen.INTRO,				new ElmntPos(1, 0, true, true));

            ui.update();
            break;

        case FILL_FAKE_DATA_SKIERS:
            ui.clrScrn();
            ui.setTitle("La till följande skid-åkare:");
            skierHandler.addSkiertoList( new Skier( "Jessica", "Fajer", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Joacim", "Hojoj", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Lefte", "Rondell", "herr", 30 ));
            skierHandler.addSkiertoList( new Skier( "Ottom", "Gryshch", "herr", 31 ));
            skierHandler.addSkiertoList( new Skier( "Namno", "Namnison", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Namnim", "Svennson", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Banan", "Blöjson", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Låg", "Höjson", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Mus", "Datorson", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Kava", "Flugar", "herr", 33 ));
            skierHandler.addSkiertoList( new Skier( "Asa", "Holoj", "dam", 23 ));
            skierHandler.addSkiertoList( new Skier( "Åsa", "Hakoj", "dam", 23 ));
            skierHandler.addSkiertoList( new Skier( "Britt", "Fregulsson", "dam", 23 ));

            skierHandler.generateAllGroups();

            String info = "";
            for ( Skier skier : skierHandler.getSkierList() ) {
                info += String.format("%-30s %-10s %-10s\n", skier.getName(), skier.getAge(), skier.getGender());
            }

            ui.setBodyText(info);
            ui.addButton("Tillbaka", Screen.INTRO, new ElmntPos(0, 1, false, true));

            ui.update();
            break;

        case FILL_FAKE_DATA_RACE:
            ui.clrScrn();

            if (skierHandler.getUniqueGroupsList().size() < 1) {
                ui.setTitle("Lägg till några skidåkare först");
            } else {
                chosenGroup = skierHandler.getUniqueGroupsList().get(0);
                group = new Group(chosenGroup);
                group.generateGroupList(skierHandler, chosenGroup);

                ui.setTitle("La till följande tävling:");
                Time anHourAgo = new Time().diffTo(new Time(1, 0, 0));
                group.generateGroupListTime(anHourAgo, new Time(30, Time.Unit.SECONDS), 100);

                ui.setBodyText(
                        "Grupp: "           + chosenGroup + "\n"
                        + "Starttid: "        + group.getFirstStart().toString() + "\n"
                        + "Startinterval: "   + group.getStartInterval() + "\n"
                        + "Första åknummer: " + 100
                        );
            }

            ui.addButton("Tillbaka", Screen.INTRO, new ElmntPos(0, 1, false, true));

            ui.update();
            break;

        case CREATE_RACE_PICK_GROUP:
            ui.clrScrn();

            ui.setTitle("Var god välj skid-klass");
            for ( String group : skierHandler.getUniqueGroupsList() )
                ui.addButton( group,				Screen.CREATE_RACE,	 	new ElmntPos(0, 1, true));

            ui.addVertSpcr(200);
            ui.addButton( backBttn,						new ElmntPos(0, 1, false, true));

            ui.update();
            break;

		case CREATE_RACE:
            chosenGroup = ui.getUsrInp().get(GUI.UsrInpTypes.bttnPrsd)[0][0];

            skierHandler.generateAllGroups();
			group = new Group(chosenGroup);
			group.generateGroupList(skierHandler, chosenGroup);

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
			usrInp = ui.getUsrInp();
			Time startTime = new Time( usrInp.get(GUI.UsrInpTypes.dropDown)[0] );
            Time startInterval = new Time( usrInp.get(GUI.UsrInpTypes.dropDown)[1] );
			int firstNumber = Integer.parseInt(usrInp.get(GUI.UsrInpTypes.inputFld)[0][0]);

			group.generateGroupListTime( startTime, startInterval, firstNumber );

			screenHandler(Screen.INTRO);
			break;

		case SEE_RACE:
            ui.clrScrn();
            if (!checkRaceExists()) break;
            ui.setTitle(chosenGroup);
            ui.setBodyText(chosenGroup + " Första starttid: " + group.getFirstStart().toString() );
            ui.runClock();

			GuiCallback<Integer> chkPntCback = new GuiCallback<Integer>() {
				@Override
				public void onClick(Integer skierNum) {
					Skier skier = group.getSkierFromPlayerNumber(skierNum);
					skier.getTimeHandler().setCheckPointTime();
                    Time runningTime = skier.getTimeHandler().getRunningTimeToCheckpoint();
                    disableTableButton(skier.getPlayerNumber(), 0, runningTime);
				}
			};

			GuiCallback<Integer> fnshCback = new GuiCallback<Integer>() {
				@Override
				public void onClick(Integer skierNum) {
					Skier skier = group.getSkierFromPlayerNumber(skierNum);
					skier.getTimeHandler().setFinishTime();
                    Time runningTime = skier.getTimeHandler().getRunningTimeToFinish();
                    disableTableButton(skier.getPlayerNumber(), 1, runningTime);
				}
			};

			ui.addButtonTable( group.getSkierList().length, 0, 1, true );
			for ( Skier skier : group.getSkierList() ) {
				ui.getButtonTable().addRow(skier.getFirstName(), skier.getPlayerNumber(), chkPntCback, fnshCback);

                if ( skier.getTimeHandler().hasPassedCheckpont() )
                    disableTableButton(skier.getPlayerNumber(), 0, skier.getTimeHandler().getCheckPointTime());
                if ( skier.getTimeHandler().hasFinished() )
                    disableTableButton(skier.getPlayerNumber(), 1, skier.getTimeHandler().getFinishTime());
			}

			ui.addButton( "Bakåt",					Screen.BACK,	 		new ElmntPos(0, 3, false));
			ui.addButton( "Fortsätt",				Screen.LIVE_SCOREBOARD,	new ElmntPos(1, 3, false));

			ui.update();
			break;

		case LIVE_SCOREBOARD:
			ui.clrScrn();
            if (!checkRaceExists()) break;
			ui.setTitle("Resultat-tavla");
			ui.setBodyText(chosenGroup);
			ui.addTable(new String[] { "Åkarnummer", "Namn", "Starttid", "Mellanmål", "Slutmål", "Total tid" }, 0, 1, true);

			group.sortSkierListGoalTime();

			for ( Skier skier : group.getSkierList() ) {
                TimeHandler skierTime = skier.getTimeHandler();
				String startingTime = skierTime.getStartTime().toString();
                String checkPTime = skierTime.getCheckPointTime()       == null ? "-" : skierTime.getCheckPointTime().toString();
                String finishTime = skierTime.getFinishTime()           == null ? "-" : skierTime.getFinishTime().toString();
                String totalTime  = skierTime.getRunningTimeToFinish()  == null ? "-" : skierTime.getRunningTimeToFinish().toString();
				String skierNumber = String.valueOf(skier.getPlayerNumber());
                ui.getTextTable().addTableRow(new String[] {
                        skierNumber, skier.getFirstName(), startingTime, checkPTime, finishTime, totalTime
                });
			}
			ui.addButton( "Bakåt",					Screen.BACK,	 	new ElmntPos(0, 1, true));
			ui.addButton( "Huvudmeny",				Screen.INTRO,	 	new ElmntPos(1, 0, true));
			ui.update();

			break;

		case PRINT_STRTLIST:
            ui.clrScrn();
            if (!checkRaceExists()) break;
            ui.setTitle("Start-lista");
            // ui.setBodyText(chosenGroup);
            ui.addTable(new String[] { "Åkarnummer", "Namn", "Starttid", }, 1, 1, true);

            group.sortSkierListStartTime();

            for ( Skier skier : group.getSkierList() ) {
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

    private boolean checkRaceExists() {
        if (group == null) {
            ui.setTitle("Ingen tävling har skapats.");
            ui.setBodyText("Var god skapa en först.");
            ui.addButton( "Bakåt",					Screen.BACK,	 		new ElmntPos(0, 1, false, true));
            ui.update();
            return false;
        }
        return true;
    }
    
    private void disableTableButton(int skierNum, int bttnNum, Time runningTime) {
		( (JButton) ui.getButtonTable().getTblCmp(skierNum, bttnNum) ).setText(runningTime.toString());
		( (JButton) ui.getButtonTable().getTblCmp(skierNum, bttnNum) ).setEnabled(false);

    }
}
