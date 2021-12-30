package timekeeping;

import java.util.Calendar;

import common.Utils;

public class Clock {
	private long startTime;
	private String checkPointTime;
	private String finishTime;
	private int finishTimeMillis;
	
	
	public Clock() {
		super();
		this.startTime = System.currentTimeMillis();
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
		return startTime;
	}
	private void setStartTime(int startTime) {
		this.startTime = startTime;
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
	
	private Calendar cdr; // = Calendar.getInstance(); // creates a new calendar instance
	private final StringBuilder sb = new StringBuilder();

//	public Clock() {
//		cdr = Calendar.getInstance(); // creates a new calendar instance
//	}

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

	
	
	static private int[] getSprtDgts(int num) {
		int dgtsLen;
		if ( num < 10 ) // Pad all digits so they are length 2:
			dgtsLen = 2;
		else
			dgtsLen = (int) ( Math.log10(num) + 1 );

		int[] sprtDgts = new int[dgtsLen];
		for ( int i=0; i<dgtsLen; i++ ) {
			sprtDgts[dgtsLen-i-1] = (num % 10);
			num=num/10;
		}
		return sprtDgts;
	}

}
