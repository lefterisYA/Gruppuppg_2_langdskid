package timekeeping;

import java.util.Calendar;

// Här finns metoder för att sätta starttid, mellantid och sluttid för aktuell åkare, använd timeKeeper till gui
public class Timekeeper {
		private Time startTime;
		private Time checkPointTime;
		private Time finishTime;
		
		public static void main(String[] args) {
			Time time0 = new Time(16,40,0);
			Time time1 = new Time(16,40,0);
			Time extra = new Time(1, Time.Unit.SECONDS);
			Time res = time1.addTo(extra);

			System.out.println(time1.toString());
			System.out.println(res.toString());
//			System.out.println(time1.diffTo(time0));
//			System.out.println(time1.diffTo(time0).cdr.get(Calendar.YEAR));

			
		}

		public Timekeeper() {
			this.startTime = new Time();
		}

		public Timekeeper(Time startTime) {
			this.startTime = startTime;
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
		
		public Time getRunningTimeToFinish() {
			return finishTime.diffTo(startTime);
		}

		public Time getRunningTimeToCheckpoint() {
			return checkPointTime.diffTo(startTime);
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
