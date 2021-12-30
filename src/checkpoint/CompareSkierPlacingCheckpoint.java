package checkpoint;
import skiing.Skier;
import timekeeping.TimeUtils;

import java.util.Comparator;

public class CompareSkierPlacingCheckpoint implements Comparator<Skier>{

	@Override
	public int compare(Skier skier1, Skier skier2) {
		return Integer.compare(TimeUtils.timeConverter(skier1.getCheckpointTime()), TimeUtils.timeConverter(skier2.getCheckpointTime()));
	}

}
