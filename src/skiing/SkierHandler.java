package skiing;

import java.util.LinkedList;
import java.util.List;

public class SkierHandler {
	private List<Group> allGroups = new LinkedList<Group>();
	public void createSkier() {
		Skier skier = new Skier("Otto", "Kostmann", "Herr", 24);
		if (!(allGroups.contains(H24))) {
			generateGroupList(H24);
			addSkierToGroupList(skier);
		}
		else
			addskiertogrouplist(skier);
	}
	
}
