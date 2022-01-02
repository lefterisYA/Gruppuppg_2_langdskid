package skiing;

import timekeeping.Time;
import java.util.Comparator;

public class CompareSkierPlacingGoal implements Comparator<Skier>{
	@Override
	public int compare(Skier skier1, Skier skier2) {
		Time finishTime1 = skier1.getTimeHandler().getFinishTime();
		Time finishTime2 = skier2.getTimeHandler().getFinishTime();
		return finishTime2.compareTo(finishTime1); // We need to reverse this, as a lower time is "better"
	}
//		return Integer.compare(Utils.timeConverter(skier1.getGoalTime()), Utils.timeConverter(skier2.getGoalTime()));
//		Long finishTime1 = skier1.getTimeHandler().getFinishTime().inMs();
//		Long finishTime2 = skier2.getTimeHandler().getFinishTime().inMs();
//		return Long.compare(finishTime2, finishTime1);
}
