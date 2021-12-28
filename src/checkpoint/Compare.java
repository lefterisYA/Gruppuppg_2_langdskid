package checkpoint;
import skiing.Skier;

import java.util.Comparator;

import common.Utils;

public class Compare implements Comparator<Skier>{

	@Override
	public int compare(Skier skier1, Skier skier2) {
		return Integer.compare(Utils.timeConverter(skier1.getCheckpointTime()), Utils.timeConverter(skier2.getCheckpointTime()));
	}

}
