package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

	//TODO rensa gamla/oanvända metoder

public class Skier extends Person implements Comparable<Skier> {
	private int playerNumber; // Sparar personens tävlingsnummer
	private int[] startingTime = new int[3]; // Starttiden H,M,S
	private int goalTime;
	private int[] checkpointTime;
	private Clock clock;
	private String skiingClass;
	
	public void setClock(Clock clock) {
		this.clock = clock;
	}
	public Clock getClock() {
		return clock;
	}
	
	
	public String getSkiingClass() {return skiingClass;}
	public void setSkiingClass(String skiingClass) {this.skiingClass = skiingClass;}
	public int[] getStartingTime() {return startingTime;}
	public void setStartingTime(int[] startingTime) {this.startingTime = startingTime;}
	public void setStartingTime(int index, int startingTime) {this.startingTime[index] = startingTime;}
	public int getPlayerNumber() {return playerNumber;}
	public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}
	public int[] getCheckpointTime() {return checkpointTime;}
	public void setCheckpointTime(int[] checkpointTime) {this.checkpointTime = checkpointTime;}
	public int getGoalTime() {return goalTime;}
	public void setGoalTime(int val) { goalTime = val;}
	
	public Skier(String firstName, String lastName, String gender, int age) {
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setName(firstName+lastName);
		super.setGender(gender);
		super.setAge(age);
		this.skiingClass = Character.toUpperCase(super.getGender().charAt(0)) + Integer.toString(super.getAge());
		
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
		return "Skidåkare som heter " + getName()  + ", starttid: " + Arrays.toString(startingTime)
				+ ", klass: " + skiingClass + ", startnummer: " + getPlayerNumber() + ", kön: " + getGender() + ", ålder :" + getAge() + "]";
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