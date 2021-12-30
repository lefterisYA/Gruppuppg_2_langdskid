package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

//TODO rensa gamla/oanvända metoder

public class SkierHandler {

	private List<Group> allGroups = new LinkedList<Group>();
	private List<Skier> allSkiers = new LinkedList<Skier>();

	public Skier getSkier(int skierNumber) {
		return allSkiers.get(skierNumber);
	}

	public void addSkiertoList(Skier skier) {
		allSkiers.add(skier);
	}

	public String getSkiingGroup(Skier skier) {
		return Character.toUpperCase(skier.getGender().charAt(0)) + Integer.toString(skier.getAge());
	}

	public int getSkierLinkedListSize() {
		return allSkiers.size();
	}

	public String getSkiingGroupAtIndex(int i) {
		return getSkiingGroup(allSkiers.get(i));
	}

	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[allSkiers.size()];
		for (int i = 0; i < allSkiers.size(); i++) {
			skierList[i] = allSkiers.get(i);
		}
		return skierList;
	}

	public void generateAllGroups() { //jobbar på denna
		allGroups.add(new Group(allSkiers.get(0), getSkiingGroup(allSkiers.get(0))));
		for (int i = 0; i < allSkiers.size(); i++) {
			for (int j = 0; j < allGroups.size(); j++) {
				System.out.println(getSkiingGroup(allSkiers.get(i)));
				System.out.println(allGroups.get(j).getSkiingGroup());
				if (getSkiingGroup(allSkiers.get(i)).equals(allGroups.get(j).getSkiingGroup())) {
					allGroups.get(j).addToGroup(allSkiers.get(i));
//						allGroups.add(new Group(allSkiers.get(i), getSkiingGroup(allSkiers.get(i))));
				} else {
					if(getSkiingGroup(allSkiers.get(i))(allGroups.c))
					allGroups.add(new Group(allSkiers.get(i), getSkiingGroup(allSkiers.get(i))));
				}
				
			}
		}

	}

	public void assignAllPlayerNumbersRandom() {
		Skier[] skierList = new Skier[allSkiers.size()];
		double x = 0;
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			for (int j = 0; j < skierList.length; j++) {
				if ((int) x == skierList[j].getPlayerNumber() || (int) x == 0) {
					x = (Math.random() * 10000);
				}
			}
			skierList[i].setPlayerNumber((int) x);
			allSkiers.set(i, skierList[i]);
		}
	}

	@Override
	public String toString() {
		String skierString = "";
		for (int i = 0; i < allSkiers.size(); i++) {
			skierString = skierString + "Skier number " + i + 1 + " - Name: " + allSkiers.get(i).getName() + ", age: "
					+ allSkiers.get(i).getAge() + ", gender: " + allSkiers.get(i).getGender() + ", playernumber: "
					+ allSkiers.get(i).getPlayerNumber() + "\n";
		}
		return "This list contains: " + skierString;
	}

//	public List<String> getUniqueGroupsList() {
//		List<String> uniqueGroupsList = new LinkedList<String>(); 
//		for (int i = 0; i < allSkiers.size(); i++) {
//			if(uniqueGroupsList.contains(getSkiingGroupAtIndex(i))==false) {
//				uniqueGroupsList.add(getSkiingGroupAtIndex(i));
//			}
//		}
//		return uniqueGroupsList;
//	}
	public List<String> getUniqueGroupsList() {
		List<String> uniqueGroupsList = new LinkedList<String>();
		for (int i = 0; i < allSkiers.size(); i++) {
			if (uniqueGroupsList.contains(getSkiingGroupAtIndex(i)) == false) {
				uniqueGroupsList.add(getSkiingGroupAtIndex(i));
			}
		}
		return uniqueGroupsList;
	}
}
