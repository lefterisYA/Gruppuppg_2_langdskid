package skiing;

import timekeeping.Time;
import java.util.Comparator;

public class CompareSkierPlacingCheckpoint implements Comparator<Skier>{
	@Override
	public int compare(Skier skier1, Skier skier2) {
		Time chkPntTime1 = skier1.getTimeHandler().getCheckPointTime();
		Time chkPntTime2 = skier2.getTimeHandler().getCheckPointTime();
		return chkPntTime2.compareTo(chkPntTime1); // We need to reverse this, as a lower time is "better"
	}
//		return Integer.compare(itils.timeConverter(skier1.getCheckpointTime()), Utils.timeConverter(skier2.getCheckpointTime()));
}
