package skiing;

import timekeeping.Time;
import java.util.Comparator;

public class CompareSkierPlacingStarttime implements Comparator<Skier>{
	@Override
	public int compare(Skier skier1, Skier skier2) {
		Time st1 = skier1.getTimeHandler().getStartTime();
		Time st2 = skier2.getTimeHandler().getStartTime();
		return st1.compareTo(st2); // We need to reverse this, as a lower time is "better"
	}
}
