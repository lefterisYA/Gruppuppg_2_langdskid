package main;

import UI.GUI;
import UI.Screen;
import UI.UI;
import common.Utils;
import skiing.SkiSlope;
import skiing.Skier;
import skiing.SkierList;

public class Main {
	SkierList skierList = new SkierList();

	public static void main(String[] args) {
		Utils utils = Utils.getInstance(); // get singelton instance of class Utils
		GameLogic gLogic = new GameLogic(new GUI());
		gLogic.run();
	}

}

class GameLogic {
	private final UI ui;

	// Race race;

	public GameLogic(UI ui) {
		this.ui=ui;
	}

	public void run() {
        Screen currScreen = Screen.INTRO;
//		System.out.println(ui.getUserStrng("Ange Klass:"));
//		System.out.println("done");
		while ( true ) {
			switch (currScreen) {
			case CREATE_RACE:
				ui.showScreen(Screen.CREATE_RACE);
				String level = ui.getUserStrng("Ange Klass:");
				String time = ui.getUserStrng("Ange starttid:");
				String time_interval = ui.getUserStrng("Ange startintervall:");
				int first_startnumber  = ui.getUserInt("Ange första startnummer");
				// race = new Race(level, time, time_interval); // TODO

			case INTRO:
				ui.showScreen(currScreen);
				break;

			case PRINT_STRTLIST:
				break;

			case RGSTR_SKIER:
				ui.showScreen(Screen.RGSTR_SKIER);
				String name = ui.getUserStrng("Ange namn på tävlande.");
				String club = ui.getUserStrng("Ange tävlandes klubb");
				break;

			}

			currScreen = ui.getNextScreen();
		}
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

	private static void skiResults(Skier[] list) {

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
