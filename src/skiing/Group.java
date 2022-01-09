package skiing;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import timekeeping.Time;

public class Group {
	private List<Skier> group = new LinkedList<Skier>();
	private String skiingGroup;
	private Time firstStart;
	private Time startInterval;
	private int firstPlayerNumber;

	public String getSkiingGroup() {
		return skiingGroup;
	}
	public Skier getSkierFromPlayerNumber(int playerNumber) {
		Skier skier = new Skier();
		for (int i = 0; i < group.size(); i++) {
			if (group.get(i).getPlayerNumber() == playerNumber) {
				skier= group.get(i);
			}
		}
		return skier;
	}

	public void sortSkierListCheckpointTime() {
		Collections.sort(group, new CompareSkierPlacingCheckpoint());
	}

	public void sortSkierListGoalTime() {
		Collections.sort(group, new CompareSkierPlacingGoal());
	}

    public void sortSkierListStartTime() {
        Collections.sort(group, new CompareSkierPlacingStarttime());
    }

	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[group.size()];
		for (int i = 0; i < group.size(); i++) {
			skierList[i] = group.get(i);
		}
		return skierList;
	}

	public void addToGroup(Skier skier) {
		if (skier.getSkiingGroup().equals(this.skiingGroup) && (!(group.contains(skier)))) {
			group.add(skier);
		}
	}

	public Group(String skiingGroup) {
		this.skiingGroup = skiingGroup;
	}

	public Group(Skier skier, String skiingGroup) {
		group.add(skier);
		this.skiingGroup = skiingGroup;
	}

	public void generateGroupList(SkierHandler skierlist, String chosenGroup) {
		this.skiingGroup = chosenGroup;
		for (int i = 0; i < skierlist.getSkierLinkedListSize(); i++) {
			if (chosenGroup.equals(skierlist.getSkiingGroupAtIndex(i))) {
				group.add(skierlist.getSkier(i));
			}
		}
	}

	public void generateGroupListTime(Time firstStartTime, Time startInterval, int firstPlayerNumber) {
		this.firstStart = firstStartTime;
		this.startInterval = startInterval;
		this.firstPlayerNumber = firstPlayerNumber;
		assignAllPlayerNumbersRandom();
		sortList();
		for (int i = 0; i < group.size(); i++) {
//			group.get(i).setStartingTime(Utils.timeAdder(this.firstStart, Utils.timeConverter(this.startInterval * i)));
			group.get(i).getTimeHandler().setStartTime( firstStartTime.addTo( startInterval.productOf(i) ) );
			group.get(i).setPlayerNumber(firstPlayerNumber + i);
		}
	}

	/**
	 * Sorterar listan beroende på deras playernumber
	 */
	public void sortList() {
		Collections.sort(group);
	}

	public void assignAllPlayerNumbersRandom() {
		Skier[] skierList = new Skier[group.size()];
		double x = 0;
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			for (int j = 0; j < skierList.length; j++) {
				if ((int) x == skierList[j].getPlayerNumber() || (int) x == 0) {
					x = (Math.random() * 10000);
				}
			}
			skierList[i].setPlayerNumber((int) x);
			group.set(i, skierList[i]);
		}
	}

	@Override
	public String toString() {
		String skierString = "";
		for (int i = 0; i < group.size(); i++) {
			skierString = skierString + "Skier number " + i + 1 + " - Name: " + group.get(i).getName() + ", age: "
					+ group.get(i).getAge() + ", gender: " + group.get(i).getGender() + ", playernumber: "
					+ group.get(i).getPlayerNumber() + "\n";
		}
//		return "This grouplist contains: " + skierString + "And their first start is " + Arrays.toString(firstStart)
		return "This grouplist contains: " + skierString + "And their first start is " + firstStart.toString()
				+ ", their startinterval is " + startInterval + " and their starting number is " + firstPlayerNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(firstStart.asHumanTime());
		result = prime * result + Objects.hash(firstPlayerNumber, group, skiingGroup, startInterval);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		return firstPlayerNumber == other.firstPlayerNumber && Arrays.equals(firstStart.asHumanTime(), other.firstStart.asHumanTime())
				&& Objects.equals(group, other.group) && Objects.equals(skiingGroup, other.skiingGroup)
				&& startInterval == other.startInterval;
	}
}
