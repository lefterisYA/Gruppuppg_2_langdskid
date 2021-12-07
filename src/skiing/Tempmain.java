package skiing;

import java.util.Arrays;

import UI.UI;
import test.Test;

public class Tempmain {
	static UI ui;

	public static void main(String[] args) {
		ui = new UI();
		// Gör en array med skidåkare utan några värden
		int amountOfPlayers = ui.getUserInt("Hur många tävlare?");
		Skier[] list = new Skier[amountOfPlayers];

		// Deklarerade skiers sätts in i listan
		list = playerDeclaration(amountOfPlayers);

		// Slumpar hastigheten lite för alla skidåkare
		for (int i = 0; i < list.length; i++) {
			ui.postMsg(list[i].speed + "m/s");
			list[i].speedRandom();
			ui.postMsg(list[i].speed + "m/s");
		}

		// Skidbanan konstrueras
		SkiSlope skiSlope = new SkiSlope();
		skiSlope.RaceTrack();

		// Skidåkare modifieras till banan
		list = SkiSlope.RaceTrackPlayer(list, 4);

		// Här är racet
		Race(list, skiSlope);

		// Printar ut resultaten
		skiResults(list);
	}

	private static void skiResults(Skier[] list) {
		// Sorterar vinnare och förlorare
		Arrays.sort(list);

		// går igenom alla spelare och printar ut resultaten
		for (int i = 0; i < list.length; i++) {
			if (list[0] == list[i])
				ui.postMsg("En riktig vinnare! Första plats går till " + list[i].name + " som");
			if (list[list.length - 1] == list[i])
				ui.postMsg("En riktig torsk! Sista plats går till " + list[i].name + " som");
			else
				ui.postMsg(list[i].name);
			ui.postMsg(
					" kom " + (i + 1) + "a i mål vid " + toString(timeConverter(list[i].goalTime)) + " och hen satte ");
			for (int j = 0; j < list[i].checkpointTime.length; j++) {
				ui.postMsg(
						"Checkpoint " + (j + 1) + " vid " + toString(timeConverter(list[i].checkpointTime[j])) + ". ");
			}
			ui.postMsg(list[i].speed + "m/s");
			;
			ui.postMsg("\n\n");
		}
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
					list[j].goalTime = secondsPassedTotal; // Den här if-satsen kollar ifall de har gått i mål.
					list[j].goal = true;
				}
			}

			// Räknar tid
			secondsPassedTotal++;
		}
	}

	// Deklarerar skidåkare
	public static Skier[] playerDeclaration(int amountOfPlayers) {
		Skier[] list = new Skier[amountOfPlayers];
		for (int i = 0; i < list.length; i++) { // Går igenom så många skidåkare som valts och ger dom ett nummer
			ui.postMsg("Beskriv spelare nummer " + (i + 1));
			Skier skier = Test.skierDeclarationtest(i);
			list[i] = skier;
		}
		return list;
	}

	// Konverterar totala sekunder till timmar/minuter/sekunder
	public static int[] timeConverter(int secondsTotal) {
		int hours = 0;
		int minutes = 0;
		int seconds = 0;

		if (secondsTotal >= 60) {
			minutes = secondsTotal / 60;
			seconds = secondsTotal % 60;
			if (minutes >= 60) {
				hours = minutes / 60;
				minutes = minutes % 60;
			}
		} else
			seconds = secondsTotal;
		if (hours >= 1) {
			int[] hms = { hours, minutes, seconds };
			return hms;
		}
		if (minutes >= 1) {
			int[] ms = { 0, minutes, seconds };
			return ms;
		} else {
			int[] s = { 0, 0, seconds };
			return s;
		}

	}

	public static String toString(int[] hms) {
		return (hms[0] + ":" + hms[1] + ":" + hms[2]);
//		if (hms[0]>0)
//			return (hms[0]+":"+hms[1]+":"+hms[2]);
//		if (hms[1]>0)
//			return (hms[1]+":"+hms[2]);
//		if (hms[2]>0)
//			return (hms[2]+"");
//		else
//			return ("Error");
	}
}