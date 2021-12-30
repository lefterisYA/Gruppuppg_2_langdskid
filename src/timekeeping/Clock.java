package timekeeping;

import java.util.Calendar;

import common.Utils;

public class Clock {
	private long startTime;
	private long checkPointTime;
	private long finishTime;
	
	public static void main(String[] args) {
		
	}
	
	private Calendar cdr; // = Calendar.getInstance(); // creates a new calendar instance

	public Clock() {
		cdr = Calendar.getInstance(); // creates a new calendar instance
	}
	
	/**
	 * Private, maybe usefull for methods in this class?
	 * @param uTime a time given in unix time format, a big long
	 * @return a time given in int[] { hh, mm, ss } format
	 */
	private int[] unixToHumanTime(long uTime) {
		return null;
	}

	/**
	 * 
	 * @param time a time given in int[] { hh, mm, ss } format
	 */
	public void setStartTime(int[] time) {
//		this.startTime = startTime;
	}

	public long getStartTime() {
		return startTime;
	
	}

	/**
	 * 
	 * @param startTime a starting time.
	 * @return the time difference between a start time and now.
	 */
	public int[] getRunningTime(int[] startTime) {
		return new int[] { 00, 00, 00 };
	}
	


	
	/**
	 * Sets the clock time
	 * @newTime the new time 
	 */
	public void setCurrActualTime(int[] newTime) {
		// TODO
	}
	private String getFinishTime() {
		return finishTime;
	}

	private void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	private long getStartTime() {
	}
	private String getCheckPointTime() {
		return checkPointTime;
	}
	private void setCheckPointTime(String checkPointTime) {
		this.checkPointTime = checkPointTime;
	}
	private int getFinishTimeMillis() {
		return finishTimeMillis;
	}
	private void setFinishTimeMillis(int finishTimeMillis) {
		this.finishTimeMillis = finishTimeMillis;
	}
	
	private int getRunningTimeMillis() { // metod som utifrån starttid räknar hur lång tid det gått i millisekunder
		long now = System.currentTimeMillis();
		int difference = (int) (now-this.getStartTime())/1000; 
		return difference;
	}

	private String setCheckPoint() { // denna ska du ropa på Joakim
		long now = System.currentTimeMillis(); // tiden nu i millisekunder		
		int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
		String diffString = Utils.toString(Utils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
		this.setCheckPointTime(diffString); // mellantiden som åkaren fick lagrar vi fast i variablen checkPointTime, varje åkare kommer på sin klocka ha en lagrad mellantid
		return diffString;
	}
	private String setFinish() { // Sätter sluttid
		long now = System.currentTimeMillis(); // tiden nu i millisekunder
		int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
		String diffString = Utils.toString(Utils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
		this.setFinishTime(diffString);
		this.setFinishTimeMillis(difference);
		return diffString;
	}

	public String getCurrTime() {
		cdr.setTimeInMillis(System.currentTimeMillis());
	
		String time = String.format( 
					"%02d:%02d:%02d:%02d", 
					cdr.get(Calendar.HOUR_OF_DAY), 
					cdr.get(Calendar.MINUTE),
					cdr.get(Calendar.SECOND),
					cdr.get(Calendar.MILLISECOND) / 10
				);

		return time;
	}
	
	public int[] getCurrTimeInts() {
		cdr.setTimeInMillis(System.currentTimeMillis());
		return new int[] { 
				cdr.get(Calendar.HOUR_OF_DAY), 
				cdr.get(Calendar.MINUTE), 
				cdr.get(Calendar.SECOND), 
		};
	}
	
}
