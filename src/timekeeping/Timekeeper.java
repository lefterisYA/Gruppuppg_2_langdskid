package timekeeping;

public class Timekeeper {
// Här finns metoder för att sätta starttid, mellantid och sluttid för aktuell åkare, använd timeKeeper till gui
	
		private Long startTime;
		private String checkPointTime;
		private String finishTime;
		private int finishTimeMillis;
		
		
		public Timekeeper() {
			super();
			//this.startTime = System.currentTimeMillis();
		}
		
		public Long getStartTime() {
			return startTime;
		}
		
		public void setStartTime(Long startTime) {
			this.startTime = startTime;
		}
		
		public String getFinishTime() {
			return finishTime;
		}

		public void setFinishTime(String finishTime) {
			this.finishTime = finishTime;
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

		public String getStartTimeString() { 
			return TimeUtils.convertSecondsToHMmSs(this.getStartTime()/1000);
		}
		
		public String setCheckPoint() { // denna ska du ropa på Joakim
			long now = System.currentTimeMillis(); // tiden nu i millisekunder		
			int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
			String diffString = TimeUtils.toString(TimeUtils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
			this.setCheckPointTime(diffString); // mellantiden som åkaren fick lagrar vi fast i variablen checkPointTime, varje åkare kommer på sin klocka ha en lagrad mellantid
			return diffString;
		}
		public String setFinish() { // Sätter sluttid
			long now = System.currentTimeMillis(); // tiden nu i millisekunder
			int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
			String diffString = TimeUtils.toString(TimeUtils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
			this.setFinishTime(diffString);
			this.setFinishTimeMillis(difference);
			return diffString;
		}
		

	}
