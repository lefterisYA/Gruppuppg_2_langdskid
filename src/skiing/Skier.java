package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

	//TODO rensa gamla/oanvända metoder

public class Skier extends Person implements Comparable<Skier> {
	public int playerNumber; // Sparar personens tävlingsnummer
	public int[] startingTime = new int[3]; // Starttiden H,M,S
	public int goalTime;
	public int[] checkpointTime;
	private Clock clock;
	public String skiingClass;
	
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
	public int getGoalTime() {return goalTime;}
	public void setGoalTime(int val) { goalTime = val;}
	
	public Skier(String firstName, String lastName, String gender, int age) {
		super.firstName = firstName;
		super.lastName = lastName;
		super.name = firstName+lastName;
		super.gender = gender;
		super.age = age;
		this.skiingClass = Character.toUpperCase(super.gender.charAt(0)) + Integer.toString(super.age);
		
	}

	// En constructor som jag använder för att deklarera en array (list) med
	// skidåkare utan några värden.
	public Skier() {
	}

	@Override
	public int compareTo(Skier o) {
		if (playerNumber > o.playerNumber)
			return 1;
		if (playerNumber < o.playerNumber)
			return -1;
		else
			return 0;
	}
	@Override
	public String toString() {
		return "Skidåkare som heter " + name  + ", starttid: " + Arrays.toString(startingTime)
				+ ", klass: " + skiingClass + ", startnummer: " + playerNumber + ", kön: " + gender + ", ålder :" + age + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(checkpointTime);
		result = prime * result + Arrays.hashCode(startingTime);
		result = prime * result + Objects.hash(playerNumber, skiingClass);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skier other = (Skier) obj;
		return Arrays.equals(checkpointTime, other.checkpointTime) && playerNumber == other.playerNumber
				&& Objects.equals(skiingClass, other.skiingClass) && Arrays.equals(startingTime, other.startingTime);
	}

}