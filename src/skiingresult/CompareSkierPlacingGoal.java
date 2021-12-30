package skiingresult;

import java.util.Comparator;

import skiing.Skier;
import timekeeping.TimeUtils;

public class CompareSkierPlacingGoal implements Comparator<Skier>{

	@Override
	public int compare(Skier skier1, Skier skier2) {
		return Integer.compare(TimeUtils.timeConverter(skier1.getGoalTime()), TimeUtils.timeConverter(skier2.getGoalTime()));
	}

}
