package skiing;

public class SkiSlope {
	public double trackLength;
	public double[] checkpoints;
	public SkiSlope() {

	}

	public void RaceTrack() {
		this.trackLength = 500.0;
		checkpoints = new double[4];
		checkpoints[0] = 100.0;
		checkpoints[1] = 200.0;
		checkpoints[2] = 300.0;
		checkpoints[3] = 400.0;
	}

	public static Skier[] RaceTrackPlayer(Skier[] list, int amountOfCheckpoints) {

		for (int i = 0; i < list.length; i++) {
			list[i].checkpointCheckList.clear();
//			list[i].checkpointTimeList.clear();
			for (int j = 0; j < amountOfCheckpoints; j++) {
				list[i].checkpointCheckList.add("false");
//				list[i].checkpointTimeList.add("0");
			}
				list[i].goal = false;
				list[i].checkpointTime = new int[4];
		}
		return list;
	}
}