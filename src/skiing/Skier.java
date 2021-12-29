package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import common.Utils;

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
		this.goalTimeFinishSeconds=Utils.timeConverter(goalTime)-Utils.timeConverter(startingTime);
		return goalTimeFinishSeconds;
	}
	public int[] getGoalTimeFinish() {
		goalTimeFinish = Utils.timeConverter(goalTimeFinishSeconds);
		return goalTimeFinish;
	}
	public int getCheckpointTimeFinishSeconds() {
		this.checkpointTimeFinishSeconds=Utils.timeConverter(checkpointTime)-Utils.timeConverter(startingTime);
		return checkpointTimeFinishSeconds;
	}
	public int[] getCheckpointTimeFinish() {
		this.checkpointTimeFinish=Utils.timeConverter(checkpointTimeFinishSeconds);
		return checkpointTimeFinish;
	}
	public int getPlayerNumber() {return playerNumber;}
	public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}
	public int[] getCheckpointTime() {return checkpointTime;}
	/**
	 * @param []{ HH,MM,SS }
	 */
	void setCheckpointTime(int[] checkpointTime) {
		this.checkpointTime = checkpointTime;
		// TODO: REPORT RUNNING TIME.
	}
	public int[] getGoalTime() {return goalTime;}
	public void setGoalTime(int[] goalTime) { this.goalTime = goalTime;}
	
	/**
	 * TODO
	 * @return the difference between the finising time and the checkingpoint time.
	 */
	public int getGoalRunningTimeSecs() { return 0; }
	/**
	 * TODO
	 * @return the difference between the finising time and the starting time.
	 */
	public int getCheckpointRunningTimeSecs() {
		return 0;
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
		return "Skidåkare som heter " + getName()  + ", starttid: " + Arrays.toString(startingTime)
				+ ", klass: " + skiingGroup + ", startnummer: " + getPlayerNumber() + ", kön: " + getGender() + ", ålder :" + getAge() + "]";
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