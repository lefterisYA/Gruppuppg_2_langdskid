package skiing;

import java.util.Scanner;

public class Skier extends Person implements Comparable<Skier> {
	int playerNumber; // Sparar personens nummer, i den ordning dom blivit anmälda i tävlingen, inte
						// om dom kom först eller sist
	double speed; // Räknar speed i meter per sekund
	double position; // Räknar position i meter i banan
	int[] sTime = new int[3]; // Starttiden H,M,S
	boolean[] checkpointCheck; // Används för att se om personen gått igenom en checkpoint
	boolean goal = false; // Används för att se om personen gått i mål
	int goalTime;
	int[] checkpointTime;

	// constructor för skidåkare som just nu inte används
	public Skier(String name, int playernumber, double speed, double position, int[] startingTime) {
		super.name = name;
		this.playerNumber = playernumber;
		this.speed = speed;
		this.position = position;
		this.sTime = startingTime;
	}

	// En constructor som jag använder för att deklarera en array (list) med
	// skidåkare utan några värden.
	public Skier() {
	}

	//Slumpar hastigheten lite grann
	public void speedRandom() {
		double random = Math.random() * 3;
		if (Math.random() < 0.5) {
			random = -random;
		}
		this.speed = this.speed + (Math.round(random * 100.0) / 100.0);
		if (this.speed < 1)
			this.speed = 1;
	}

	//Jämför med tid i mål
	@Override
	public int compareTo(Skier o) {
		if (goalTime > o.goalTime)
			return 1;
		if (goalTime < o.goalTime)
			return -1;
		else
			return 0;
	}

}