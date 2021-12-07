package skiing;

import java.util.ArrayList;

public class Skier extends Person implements Comparable<Skier> {
	public int playerNumber; // Sparar personens nummer, deras tävlingsnummer, inte
						// om dom är först eller sist
	public double speed; // Räknar speed i meter per sekund
	public double position; // Räknar position i meter i banan
	public int[] sTime = new int[3]; // Starttiden H,M,S
	public ArrayList<String> checkpointCheckList = new ArrayList<String>(); // Används för att se om personen gått igenom en checkpoint
	public boolean goal = false; // Används för att se om personen gått i mål
	public int goalTime;
//	ArrayList<String> checkpointTimeList = new ArrayList<String>();
	public int[] checkpointTime;
	// constructor för skidåkare som just nu inte används
	public Skier(String name, int playernumber, double speed, double position, int[] startingTime) {
		super.name = name;
		this.playerNumber = playernumber;
		this.speed = speed;
		this.position = position;
		this.sTime = startingTime;
	}

	public Skier(String name, int playernumber, double speed, double position) {
		super.name = name;
		this.playerNumber = playernumber;
		this.speed = speed;
		this.position = position;
	}

	public Skier(String name, double speed, double position) {
		super.name = name;
		this.speed = speed;
		this.position = position;
	}

	// En constructor som jag använder för att deklarera en array (list) med
	// skidåkare utan några värden.
	public Skier() {
	}

	// Slumpar hastigheten lite grann
	public void speedRandom() {
		double random = Math.random() * 3;
		if (Math.random() < 0.5) {
			random = -random;
		}
		this.speed = this.speed + (Math.round(random * 100.0) / 100.0);
		if (this.speed < 1)
			this.speed = 1;
	}

	// Jämför med tid i mål
	@Override
	public int compareTo(Skier o) {
		if (goalTime > o.goalTime)
			return 1;
		if (goalTime < o.goalTime)
			return -1;
		else
			return 0;
	}
	public void setCheckpointCheckList(int numberOfCheckpoints) {
		for (int i = 0; i < numberOfCheckpoints; i++) {
			checkpointCheckList.add("false");
		}
	}

	public void getCheckpointCheckList() {
	}
	// TODO: skiva klart alla setters and getters.
	public void setGoalTime(int val) { goalTime = val; }
	public int getGoalTime() {
		return goalTime;
	}

}