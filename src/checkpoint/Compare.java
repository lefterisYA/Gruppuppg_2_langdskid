package checkpoint;
import skiing.Skier;

import java.util.Comparator;

public class Compare implements Comparator<Skier>{

	@Override
	public int compare(Skier skier1, Skier skier2) {
		return skier1.getCheckpointTime().compareTo(skier2.getCheckpointTime());
	}

}
