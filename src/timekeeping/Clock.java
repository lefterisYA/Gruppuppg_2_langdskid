package timekeeping;

import java.util.Calendar;

public class Clock {
	private long startTime;
	private long checkPointTime;
	private long finishTime;
	
	public static void main(String[] args) {
		
		Clock testClock = new Clock();
		testClock.setStartTime(new Time(new int[] {10,10,10} ));
		testClock.setStartTime(new Time( 15 ));
	}
	

	/**
	 * 
	 * @param time a time given in int[] { hh, mm, ss } format
	 */
	public void setStartTime(Time time) {
		// TODO
	}

	public Time getStartTime() {
		return null;
	}

	public void setCheckpointTime(Time time) {
		// TODO
	}

	public Time getCheckpointTime() {
		return null; // TODO
	
	}

	public void setFinishTime(Time time) {
		// TODO
	}

	public Time getFinishTime() {
		return null; // TODO
	}

	/**
	 * 
	 * @param startTime a starting time.
	 * @return the time difference between a start time and now.
	 */
	public Time getRunningTime(Time startTime) {
		return null; //TODO
	}
	


	
//	private String getFinishTime() {
//		return finishTime;
//	}
//
//	private void setFinishTime(String finishTime) {
//		this.finishTime = finishTime;
//	}
//
//	private String getCheckPointTime() {
//		return checkPointTime;
//	}
//	private void setCheckPointTime(String checkPointTime) {
//		this.checkPointTime = checkPointTime;
//	}
//	private int getFinishTimeMillis() {
//		return finishTimeMillis;
//	}
//	private void setFinishTimeMillis(int finishTimeMillis) {
//		this.finishTimeMillis = finishTimeMillis;
//	}
	

	private String setCheckPoint() { // denna ska du ropa på Joakim
		long now = System.currentTimeMillis(); // tiden nu i millisekunder		
//		int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
//		String diffString = Utils.toString(Utils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
//		this.setCheckPointTime(diffString); // mellantiden som åkaren fick lagrar vi fast i variablen checkPointTime, varje åkare kommer på sin klocka ha en lagrad mellantid
//		return diffString;
		return null;
	}
	private String setFinish() { // Sätter sluttid
		long now = System.currentTimeMillis(); // tiden nu i millisekunder
//		int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
//		String diffString = Utils.toString(Utils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
//		this.setFinishTime(diffString);
//		this.setFinishTimeMillis(difference);
//		return diffString;
		return null;
	}

	
}

