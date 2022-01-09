package timekeeping;

public class TimeHandler {
	private Time startTime = null;
	private Time checkPointTime = null;
	private Time finishTime = null;

	public Time getStartTime() 							{ return startTime; }
	public void setStartTime(Time startTime) 			{ this.startTime = startTime; }

    public boolean hasFinished() 						{ return finishTime != null; }
	public Time getFinishTime() 						{ return finishTime; }
	public void setFinishTime(Time finishTime) 			{ this.finishTime = finishTime; }
	public void setFinishTime() 						{ this.finishTime = new Time(); }

    public boolean hasPassedCheckpont() 				{ return checkPointTime != null; }
	public Time getCheckPointTime() 					{ return checkPointTime; }
	public void setCheckPointTime() 					{ this.checkPointTime = new Time(); }
	public void setCheckPointTime(Time checkPointTime) 	{ this.checkPointTime = checkPointTime; }

	public Time getRunningTimeToFinish() 				{ return finishTime == null ? null : finishTime.diffTo(startTime); }
	public Time getRunningTimeToCheckpoint() 			{ return checkPointTime == null ? null : checkPointTime.diffTo(startTime); }

	public boolean passedCheckpoint() 					{ return checkPointTime != null; }
	public boolean passedFinishline() 					{ return finishTime != null; }

    public void zeroTimes() {
        startTime = null;
        checkPointTime = null;
        finishTime = null;
    }
}
