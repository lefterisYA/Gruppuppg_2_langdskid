package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import common.Utils;
import timekeeping.Clock;

	//TODO rensa gamla/oanvända metoder

public class Skier extends Person implements Comparable<Skier> {
	private int playerNumber; // Sparar personens tävlingsnummer
	private int[] startingTime = new int[3]; // Starttiden H,M,S
	private int[] goalTime;
	private int[] checkpointTime;
	private int[] checkpointTimeFinish;
	private int checkpointTimeFinishSeconds;
	private int[] goalTimeFinish;
	private int goalTimeFinishSeconds;
	private Clock clock;
	private String skiingGroup;
	
//	public void setClock(Clock clock) {
//		this.clock = clock;
//	}
//	public Clock getClock() {
//		return clock;
//	}
	
	
	String getSkiingGroup() {return skiingGroup;}
	public int[] getStartingTime() {return startingTime;}
	public void setStartingTime(int[] startingTime) {this.startingTime = startingTime;}
	public void setStartingTime(int index, int startingTime) {this.startingTime[index] = startingTime;}
	public int getGoalTimeFinishSeconds() {
		return goalTimeFinishSeconds;
	}
	public int[] getGoalTimeFinish() {
		return goalTimeFinish;
	}
	public int getCheckpointTimeFinishSeconds() {
		return checkpointTimeFinishSeconds;
	}
	public int[] getCheckpointTimeFinish() {
		return checkpointTimeFinish;
	}
	public int getPlayerNumber() {return playerNumber;}
	public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}
	public int[] getCheckpointTime() {return checkpointTime;}
	public void setCheckpointTime(int[] checkpointTime) {
		this.checkpointTime = checkpointTime;
		this.checkpointTimeFinishSeconds=Utils.timeConverter(checkpointTime)-Utils.timeConverter(startingTime);
		this.checkpointTimeFinish=Utils.timeConverter(checkpointTimeFinishSeconds);
	}
	public int[] getGoalTime() {return goalTime;}
	public void setGoalTime(int[] goalTime) { 
		this.goalTime = goalTime;
		this.goalTimeFinishSeconds=Utils.timeConverter(goalTime)-Utils.timeConverter(startingTime);
		this.goalTimeFinish = Utils.timeConverter(goalTimeFinishSeconds);
	}
	
	
	public Skier(String firstName, String lastName, String gender, int age) {
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setName(firstName+lastName);
		super.setGender(gender);
		super.setAge(age);
		this.skiingGroup = Character.toUpperCase(super.getGender().charAt(0)) + Integer.toString(super.getAge());
		
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
		String xstring = "Skidåkare som heter " + getName()  + ", starttid: " + Arrays.toString(startingTime);
		if(checkpointTimeFinishSeconds>0)
			xstring+=", mellantid sekunder: ";
		if(goalTimeFinishSeconds>0)
			xstring+=", måltid sekunder: ";
		xstring+= ", klass: " + skiingGroup + ", startnummer: " + getPlayerNumber() + ", kön: " + getGender() + ", ålder :" + getAge() + "]";
		return xstring;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(checkpointTime);
		result = prime * result + Arrays.hashCode(startingTime);
		result = prime * result + Objects.hash(clock, goalTime, playerNumber, skiingGroup);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skier other = (Skier) obj;
		return Arrays.equals(checkpointTime, other.checkpointTime) && Objects.equals(clock, other.clock)
				&& goalTime == other.goalTime && playerNumber == other.playerNumber
				&& Objects.equals(skiingGroup, other.skiingGroup) && Arrays.equals(startingTime, other.startingTime);
	}
}