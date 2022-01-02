package timekeeping;

// Här finns metoder för att sätta starttid, mellantid och sluttid för aktuell åkare, använd timeKeeper till gui
public class TimeHandler {
		private Time startTime = null;
		private Time checkPointTime = null;
		private Time finishTime = null;
		
		public static void main(String[] args) {
			Time time0 = new Time(16,40,0);
			Time time1 = new Time(16,40,0);
			Time extra = new Time(1, Time.Unit.SECONDS);
			Time res = time1.addTo(extra);

			System.out.println(time1.toString());
			System.out.println(res.toString());
		}

		public TimeHandler() {
//			this.startTime = new Time();
		}

		public TimeHandler(Time startTime) {
			this.startTime = startTime;
		}
		
		public Time getStartTime() 							{ return startTime; }
		public void setStartTime(Time startTime) 			{ this.startTime = startTime; }
		
		public Time getFinishTime() 						{ return finishTime; }
		public void setFinishTime(Time finishTime) 			{ this.finishTime = finishTime; }
		public void setFinishTime() 						{ this.finishTime = new Time(); }
		
		public Time getCheckPointTime() 					{ return checkPointTime; }
		public void setCheckPointTime() 					{ this.checkPointTime = new Time(); }
		public void setCheckPointTime(Time checkPointTime) 	{ this.checkPointTime = checkPointTime; }
		
		public Time getRunningTimeToFinish() 				{ return finishTime.diffTo(startTime); }
		public Time getRunningTimeToCheckpoint() 			{ return checkPointTime.diffTo(startTime); }
		
		public boolean passedCheckpoint() 					{ return checkPointTime != null; }
		public boolean passedFinishline() 					{ return finishTime != null; }

	}
