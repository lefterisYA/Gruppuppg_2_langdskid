package main;

import UI.GUI;
import UI.UI;
import common.Utils;
import skiing.SkiSlope;
import skiing.Skier;

public class Main {
	public static void main(String[] args) {
		Utils utils = Utils.getInstance(); // get singelton instance of class Utils
		GameLogic gLogic = new GameLogic(new GUI());
		gLogic.run();
	}
	
}

class GameLogic {
	private final UI ui;
	
	public GameLogic(UI ui) {
		this.ui=ui;
	}
	
	public void run() {
		ui.showIntroScreen();
		ui.addSkierDialog(2);
		
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