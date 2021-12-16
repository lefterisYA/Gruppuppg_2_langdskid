package main;

import UI.Screen;
import UI.UI;
import skiing.SkiSlope;
import skiing.Skier;

public class ProgLogic {
	private final UI ui;
	static Thread runningThread;
	static private boolean doIntrp = false;
	// Race race;

	public ProgLogic(UI ui) {
		this.ui=ui;
	}

	public void run() {
        Screen currScreen = Screen.INTRO;

		while ( true ) {
			System.out.println( Thread.currentThread().getStackTrace()[1] + ": " + ( currScreen != null ? currScreen.name() : "null" ));
			currScreen = screenPickerInNewThread(currScreen);
			if (currScreen == null)
				currScreen = ui.getNextScreen();
		}
	}

	public Screen screenPickerInNewThread(Screen currScren) {
        try {
        	ScrnPickLogic screenPicker;
        	screenPicker = new ScrnPickLogic(ui, currScren);
        	runningThread = new Thread(screenPicker);
        	ui.registerCallingThread(this);

//        	for ( int i=0; i<10; i++) {
//        		screenPicker = new ScrnPickLogic(ui, currScren);
//        		System.out.println( screenPicker.getId() );
//        	}

        	runningThread.run();
        	while ( !runningThread.isInterrupted() ) {
        		runningThread.join(300);
        		if ( doIntrp ) {
        			runningThread.interrupt();
        			System.out.println( "INTERRUPT!!!" );
        			break;
        		}
        	}
        	doIntrp=false;

        	System.out.println( "DONE" );
			return screenPicker.getNextScrn();
		} catch (Exception e) {
			System.out.println( "INTERRUPT" );
			e.printStackTrace();
			return null;
		}
	}
	
	static public void interrupt() {
		doIntrp = true;
		runningThread.interrupt();
	}

	// Deklarerar skidåkare
	public Skier[] playerDeclaration(int amountOfPlayers) {
		Skier[] list = new Skier[amountOfPlayers];
		for (int i = 0; i < list.length; i++) { // Går igenom så många skidåkare som valts och ger dom ett nummer
			ui.postMsg("Beskriv spelare nummer " + (i + 1));
			Skier skier = ui.addSkierDialog(i);
			list[i] = skier;
		}
		return list;
	}

	private static void Race(Skier[] list, SkiSlope skiSlope) {
		// Håller koll på tiden som har gått
		int secondsPassedTotal = 0;

		// Den förstå for-loopen räknar tiden, baserat på hur lång banan är
		for (double i = 0; i <= skiSlope.trackLength + 1; i++) { // Den andra går igenom listan och
			for (int j = 0; j < list.length; j++) { // ökar individuellt alla skidåkares
				list[j].position = list[j].position + list[j].speed; // position med deras hastighet, en gång per
																		// sekund.
				for (int k = 0; k < skiSlope.checkpoints.length; k++) {
					if (list[j].position >= skiSlope.checkpoints[k] && Boolean.parseBoolean(list[j].checkpointCheckList.get(k)) == false) {
						list[j].checkpointTime[k] = secondsPassedTotal; // Den tredje går igenom checkpointsen,
																		// kollar ifall de
						list[j].checkpointCheckList.set(k, "true"); // har gått igenom en checkpoint och sparar tiden
					}
				}
				if (list[j].position >= skiSlope.trackLength && list[j].goal == false) {
//					list[j].goalTime = secondsPassedTotal; // Den här if-satsen kollar ifall de har gått i mål.
					list[j].setGoalTime( secondsPassedTotal ); // Den här if-satsen kollar ifall de har gått i mål.
					list[j].goal = true;
				}
			}

			// Räknar tid
			secondsPassedTotal++;
		}
	}
}

class ScrnPickLogic implements Runnable {
	private Screen currScrn;
	private Screen nextScrn;
	private UI ui;

	public ScrnPickLogic(UI ui, Screen currScrn) {
		this.ui 		= ui;
		this.currScrn 	= currScrn;
		
//		ui.registerCallingThread(Thread.currentThread());
		// Thread.currentThread().interrupt();
	}

	@Override
	public void run() {
		System.out.println("HEREthred: " + Thread.currentThread().getId());
		nextScrn = screenPicker();
		System.out.println("Thread finishing");
	}

	public Screen getNextScrn() {
		return nextScrn;
	}

	private Screen screenPicker() {
		switch (currScrn) {
		case CREATE_RACE:
			ui.showScreen(Screen.CREATE_RACE);
			String level = ui.getUserStrng("Ange Klass:");
			ProgLogic.interrupt();
			String time = ui.getUserStrng(". Ange starttid:");
			String time_interval = ui.getUserStrng("Ange startintervall:");
			int first_startnumber  = ui.getUserInt("Ange första startnummer");
			return Screen.INTRO;
			//				ui.setNextScreen(Screen.INTRO);
			// race = new Race(level, time, time_interval); // TODO

		case INTRO:
			ui.showScreen(currScrn);
			return null;

		case PRINT_STRTLIST:
			return null;

		case RGSTR_SKIER:
			ui.showScreen(Screen.RGSTR_SKIER);
			String name = ui.getUserStrng("Ange namn på tävlande.");
			String club = ui.getUserStrng("Ange tävlandes klubb");
			return Screen.INTRO;

		case EXIT:
			System.exit(0);

		case ACPT:
			System.out.println("ACPT pressed");
			return null;

		case BACK:
			return null;

		default:
			return null;

		}
	}
}