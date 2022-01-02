package timekeeping;

// Här finns metoder för att sätta starttid, mellantid och sluttid för aktuell åkare, använd timeKeeper till gui
public class Timekeeper {
		private Time startTime;
		private Time checkPointTime;
		private Time finishTime;
		
		public Timekeeper() {
			//this.startTime = System.currentTimeMillis();
		}
		
		public Time getStartTime() {
			return startTime;
		}
		
		public void setStartTime(Time startTime) {
			this.startTime = startTime;
		}
		
		public Time getFinishTime() {
			return finishTime;
		}

		public void setFinishTime(Time finishTime) {
			this.finishTime = finishTime;
		}
		
		public Time getCheckPointTime() {
			return checkPointTime;
		}
		public void setCheckPointTime(Time checkPointTime) {
			this.checkPointTime = checkPointTime;
		}
		
		public Time getRunningTime() {
			long now = System.currentTimeMillis();
//			int difference = (int) (now-this.getStartTime())/1000; 
			return null;
		}

//		public void setCheckPointTime() { // denna ska du ropa på Joakim
//			long now = System.currentTimeMillis(); // tiden nu i millisekunder		
//			int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
//			String diffString = TimeUtils.toString(TimeUtils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
//			this.setCheckPointTime(diffString); // mellantiden som åkaren fick lagrar vi fast i variablen checkPointTime, varje åkare kommer på sin klocka ha en lagrad mellantid
//			return diffString;
//		}
//		public void setFinishTime() { // Sätter sluttid
//			long now = System.currentTimeMillis(); // tiden nu i millisekunder
//			int difference = (int) (now-this.getStartTime())/1000; // tar skillnaden mellan aktuell start tid och nu tid
//			String diffString = TimeUtils.toString(TimeUtils.getInstance().timeConverter(difference)); // gör om det till timmar minuter sekunder
//			this.setFinishTime(diffString);
//			this.setFinishTimeMillis(difference);
//			return diffString;
//		}
		

	}
