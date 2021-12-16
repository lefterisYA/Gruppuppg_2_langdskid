package skiing;

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
	
	
	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public String getCheckPointTime() {
		return checkPointTime;
	}
	public void setCheckPointTime(String checkPointTime) {
		this.checkPointTime = checkPointTime;
	}
	public int getFinishTimeMillis() {
		return finishTimeMillis;
	}
	public void setFinishTimeMillis(int finishTimeMillis) {
		this.finishTimeMillis = finishTimeMillis;
	}
	
	public int getRunningTimeMillis() { // metod som utifrån starttid räknar hur lång tid det gått i millisekunder
		long now = System.currentTimeMillis();
		int difference = (int) (now-this.getStartTime())/1000; 
		return difference;
	}

	public String setCheckPoint() { // denna ska du ropa på Joakim
		long now = System.currentTimeMillis(); // tiden nu i millisekunder		
		int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
		String diffString = Utils.toString(Utils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
		this.setCheckPointTime(diffString); // mellantiden som åkaren fick lagrar vi fast i variablen checkPointTime, varje åkare kommer på sin klocka ha en lagrad mellantid
		return diffString;
	}
	public String setFinish() { // Sätter sluttid
		long now = System.currentTimeMillis(); // tiden nu i millisekunder
		int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
		String diffString = Utils.toString(Utils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
		this.setFinishTime(diffString);
		this.setFinishTimeMillis(difference);
		return diffString;
	}
	

}
