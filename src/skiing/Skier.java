package skiing;

import java.util.LinkedList;
import java.util.List;

	//TODO rensa gamla/oanvända metoder

public class Skier extends Person implements Comparable<Skier> {
	public int playerNumber; // Sparar personens tävlingsnummer
	public double speed; // Räknar speed i meter per sekund
	public double position; // Räknar position i meter i banan
	public int[] startingTime = new int[3]; // Starttiden H,M,S
	public List<String> checkpointCheckList = new LinkedList<>(); // Används för att se om personen gått igenom en checkpoint
	public boolean goal = false; // Används för att se om personen gått i mål
	public int goalTime;
	public int[] checkpointTime;
	private Clock clock;
	
	public void setClock(Clock clock) {
		this.clock = clock;
	}
	public Clock getClock() {
		return clock;
	}

	public int getPlayerNumber() {return playerNumber;}
	public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}
	public int[] getCheckpointTime() {return checkpointTime;}
	public void setCheckpointTime(int[] checkpointTime) {this.checkpointTime = checkpointTime;}
	public List<String> getCheckpointCheckList() {return checkpointCheckList;}
	public void setCheckpointCheckList(int numberOfCheckpoints) {
	for (int i = 0; i < numberOfCheckpoints; i++) {
		checkpointCheckList.add("false");
		}
	}
	public int getGoalTime() {return goalTime;}
	public void setGoalTime(int val) { goalTime = val;}
	
	
	public boolean isGoal() {
		return goal;
	}
	public void setGoal(boolean goal) {
		this.goal = goal;
	}
	public Skier(String firstName, String lastName, String gender, int age) {
		super.firstName = firstName;
		super.lastName = lastName;
		super.gender = gender;
		super.age = age;
		
	}

	// En constructor som jag använder för att deklarera en array (list) med
	// skidåkare utan några värden.
	public Skier() {
	}

//	// Slumpar hastigheten lite grann
//	public void speedRandom() {
//		double random = Math.random() * 3;
//		if (Math.random() < 0.5) {
//			random = -random;
//		}
//		this.speed = this.speed + (Math.round(random * 100.0) / 100.0);
//		if (this.speed < 1)
//			this.speed = 1;
//	}

	@Override
	public int compareTo(Skier o) {
		if (playerNumber > o.playerNumber)
			return 1;
		if (playerNumber < o.playerNumber)
			return -1;
		else
			return 0;
	}

}