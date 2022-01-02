package skiing;

import timekeeping.Time;
import java.util.Comparator;

public class CompareSkierPlacingGoal implements Comparator<Skier>{
	@Override
	public int compare(Skier skier1, Skier skier2) {
		Time finishTime1 = skier1.getTimeHandler().getFinishTime();
		Time finishTime2 = skier2.getTimeHandler().getFinishTime();
		return finishTime1.compareTo(finishTime2);
	}
//		return Integer.compare(Utils.timeConverter(skier1.getGoalTime()), Utils.timeConverter(skier2.getGoalTime()));
}
