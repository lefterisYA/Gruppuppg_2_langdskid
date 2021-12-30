package skiing;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import checkpoint.CompareSkierPlacingCheckpoint;
import common.Utils;
import skiingresult.CompareSkierPlacingGoal;

public class Group {
	private List<Skier> group = new LinkedList<Skier>();
	private String skiingGroup;
	private int[] firstStart = new int[3];
	private int startInterval;
	private int firstPlayerNumber;

	public String getSkiingGroup() {
		return skiingGroup;
	}

	public int[] getFirstStart() {
		return firstStart;
	}

	public int getStartInterval() {
		return startInterval;
	}

	public void setStartInterval(int startInterval) {
		this.startInterval = startInterval;
	}

	public int getFirstPlayerNumber() {
		return firstPlayerNumber;
	}

	public List<Skier> getGroupList() {
		return group;
	}

	public void setGroupList(List<Skier> group) {
		this.group = group;
	}

	public Skier getSkierFromPlayerNumber(int playerNumber) {
		Skier[] skierlist = getSkierList();
		for (int i = 0; i < skierlist.length; i++) {
			if (skierlist[i].getPlayerNumber() == playerNumber) {
				return skierlist[i];
			}
		}
		return null;
	}

	public void sortSkierListCheckpointTime() {
		CompareSkierPlacingCheckpoint compare = new CompareSkierPlacingCheckpoint();
		Collections.sort(group, compare);
	}

	public void sortSkierListGoalTime() {
		CompareSkierPlacingGoal compare2 = new CompareSkierPlacingGoal();
		Collections.sort(group, compare2);
	}

	/**
	 * @param indexnumret på den skidåkaren du vill hämta
	 * @return skidåkaren som är på det indexnumret
	 */
	public Skier getSkier(int skierNumber) {
		return group.get(skierNumber);
	}

	/**
	 * @param skidåkaren  du vill setta
	 * @param indexnumret på den skidåkaren du vill setta
	 */
	public void setSkier(Skier skier, int indexnumber) {
		group.set(indexnumber, skier);
	}

	public void setSkierList(Skier[] skierlist) {
		group = Arrays.asList(skierlist);
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

	public void setPlayerNumber() {
		for (int i = 0; i < group.size(); i++) {
			group.get(i).setPlayerNumber(this.firstPlayerNumber + i);
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

	public void generateGroupListTime(int[] firstStartTime, int startInterval, int firstPlayerNumber) {
		this.firstStart = firstStartTime;
		this.startInterval = startInterval;
		this.firstPlayerNumber = firstPlayerNumber;
		assignAllPlayerNumbersRandom();
		sortList();
		for (int i = 0; i < group.size(); i++) {
			group.get(i).setStartingTime(Utils.timeAdder(this.firstStart, Utils.timeConverter(this.startInterval * i)));
			group.get(i).setPlayerNumber(firstPlayerNumber + i);
		}
	}

	/**
	 * Sorterar listan beroende på deras playernumber
	 */
	public void sortList() {
		Collections.sort(group);
	}

	/**
	 * Sorterar listan beroende på deras placering vid checkpoint
	 */
	public void sortListPlacingCheckpoint() {
		CompareSkierPlacingCheckpoint compare = new CompareSkierPlacingCheckpoint();
		Collections.sort(group, compare);
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
		return "This grouplist contains: " + skierString + "And their first start is " + Arrays.toString(firstStart)
				+ ", their startinterval is " + startInterval + " and their starting number is " + firstPlayerNumber;
	}

}