package skiing;

import java.util.Arrays;

import common.Utils;

	//TODO rensa gamla/oanvända metoder

public class Skier extends Person implements Comparable<Skier> {
	private int playerNumber; // Sparar personens tävlingsnummer
	private int[] startingTime = new int[3]; // Starttiden H,M,S
	private int[] goalTime;
	private int[] checkpointTime;
	private int[] checkpointTimeFinish;
	private int[] goalTimeFinish;
	
	

	public String getSkiingGroup() {
		return Character.toUpperCase(getGender().charAt(0)) + Integer.toString(getAge());}
	public int[] getStartingTime() {return startingTime;}
	public void setStartingTime(int[] startingTime) {this.startingTime = startingTime;}
	public int[] getGoalTimeFinish() {return goalTimeFinish;}
	public int[] getCheckpointTimeFinish() {return checkpointTimeFinish;}
	public int getPlayerNumber() {return playerNumber;}
	public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}
	public int[] getCheckpointTime() {return checkpointTime;}
	/**
	 * Tar in tiden i array HH/MM/SS och set som checkpointTime, sedan jämför den med
	 * startingTime och ger mellanskilladen till checkpointTimeFinishSeconds och checkpointTimeFinish
	 * @param checkpointTime
	 */
	public void setCheckpointTime(int[] checkpointTime) {
		this.checkpointTime = checkpointTime;
		this.checkpointTimeFinish=Utils.timeConverter(Utils.timeConverter(checkpointTime)-Utils.timeConverter(startingTime));
	}
	public int[] getGoalTime() {return goalTime;}
	/**
	 * Tar in tiden i array HH/MM/SS och set som goalTime, sedan jämför den med
	 * startingTime och ger mellanskilladen till goalTimeFinishSeconds och goalTimeFinish
	 * @param checkpointTime
	 */
	public void setGoalTime(int[] goalTime) { 
		this.goalTime = goalTime;
		this.goalTimeFinish = Utils.timeConverter(Utils.timeConverter(goalTime)-Utils.timeConverter(startingTime));
	}
	
	/**
	 * konstruktor för skier
	 * @param String firstName
	 * @param String lastName
	 * @param String gender herr/dam
	 * @param int age
	 */
	public Skier(String firstName, String lastName, String gender, int age) {
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setName(firstName+lastName);
		super.setGender(gender);
		super.setAge(age);
		
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
		if(Utils.timeConverter(checkpointTimeFinish)>0)
			xstring+=", mellantid sekunder: ";
		if(Utils.timeConverter(goalTimeFinish)>0)
			xstring+=", måltid sekunder: ";
		xstring+= ", startnummer: " + getPlayerNumber() + ", kön: " + getGender() + ", ålder :" + getAge() + "]";
		return xstring;
	}
}