package skiing;

import timekeeping.TimeHandler;

public class Skier extends Person implements Comparable<Skier> {
	private int playerNumber; // Sparar personens tävlingsnummer
	private TimeHandler timeHandler;

	public Skier() {
		timeHandler = new TimeHandler();
	}
	
	/**
	 * konstruktor för skier
	 * @param String firstName
	 * @param String lastName
	 * @param String gender herr/dam
	 * @param int age
	 */
	public Skier(String firstName, String lastName, String gender, int age) {
		this();
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setGender(gender);
		super.setAge(age);
	}

	public TimeHandler getTimeHandler() {
		return timeHandler;
	}

	public String getSkiingGroup() { return Character.toUpperCase(getGender().charAt(0)) + Integer.toString(getAge());}
	public int getPlayerNumber() {return playerNumber;}
	public void setPlayerNumber(int playerNumber) {this.playerNumber = playerNumber;}

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
		String xstring = "Skidåkare som heter " + getName()  + ", starttid: " + timeHandler.getStartTime().toString();
		if( timeHandler.passedCheckpoint() )
			xstring+=", mellantid sekunder: ";
		if( timeHandler.passedFinishline() )
			xstring+=", måltid sekunder: ";
		return xstring;
	}
}