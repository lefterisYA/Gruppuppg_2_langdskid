package placing;

import java.util.Comparator;

import common.Utils;
import skiing.Skier;

public class CompareSkierPlacingGoal implements Comparator<Skier>{

	@Override
	public int compare(Skier skier1, Skier skier2) {
//		return Integer.compare(Utils.timeConverter(skier1.getGoalTime()), Utils.timeConverter(skier2.getGoalTime()));
		return 0;
	}

}
