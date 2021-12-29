package skiing;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import checkpoint.CompareSkierPlacingCheckpoint;
import common.Utils;

public class GroupList extends SkierList {
	private List<Skier> groupList = new LinkedList<Skier>();
	private String skiingGroup;
	private int[] firstStart = new int[3];
	private int startInterval;
	private int firstPlayerNumber;

	public String getSkiingGroup() {return skiingGroup;}
	public int[] getFirstStart() {return firstStart;}
	public void setFirstStart(int index, int firstStart) {this.firstStart[index] = firstStart;}
	public void setFirstStart(int[] firstStart) {this.firstStart = firstStart;}
	public int getStartInterval() {return startInterval;}
	public void setStartInterval(int startInterval) {this.startInterval = startInterval;}
	public int getFirstPlayerNumber() {return firstPlayerNumber;}
	public void setFirstPlayerNumber(int firstPlayerNumber) {this.firstPlayerNumber = firstPlayerNumber;}
	public void setSkiingGroup (String skiingGroup){this.skiingGroup=skiingGroup;}
	
	public int[] getSkierGoalTimeFromPlayerNumber(int playerNumber) {
		return getSkierFromPlayerNumber(playerNumber).getGoalTime();
	}
	public int[] getSkierCheckpointTimeFromPlayerNumber(int playerNumber) {
		return getSkierFromPlayerNumber(playerNumber).getCheckpointTime();
	}
	
	public void setSkierGoalTimeFromPlayerNumber(int playerNumber, int[] goalTime) {
		getSkierFromPlayerNumber(playerNumber).setGoalTime(goalTime);
	}
	public void setSkierCheckpointTimeFromPlayerNumber(int playerNumber, int[] checkpointTime) {
		getSkierFromPlayerNumber(playerNumber).setCheckpointTime(checkpointTime);
	}
	public Skier getSkierFromPlayerNumber(int playerNumber) {
		Skier[] skierlist = getSkierList();
		for (int i = 0; i < skierlist.length; i++) {
			if (skierlist[i].getPlayerNumber()==playerNumber) {
				return skierlist[i];
			}
		}
		return null;
	}
	public void sortSkierListCheckpointTime() {
		CompareSkierPlacingCheckpoint compare = new CompareSkierPlacingCheckpoint();
		Collections.sort(groupList, compare);
	}
	/**
	 * @param indexnumret på den skidåkaren du vill hämta
	 * @return skidåkaren som är på det indexnumret
	 */
	public Skier getSkier(int skierNumber) {return groupList.get(skierNumber);}
	/**
	 * @param skidåkaren du vill setta
	 * @param indexnumret på den skidåkaren du vill setta
	 */
	public void setSkier(Skier skier, int indexnumber) {groupList.set(indexnumber, skier);}
	public void setSkierList(Skier[] skierlist) {groupList = Arrays.asList(skierlist);}
	
	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[groupList.size()];
		for (int i = 0; i < groupList.size(); i++) {
			skierList[i] = groupList.get(i);
		}
		return skierList;
	}
	
	public void addToGroupList(Skier skier) {
		if(skier.getSkiingGroup().equals(this.skiingGroup)) {
			groupList.add(skier);
		}
		else
			System.out.println("Wrong group");
	}
	
	public void addAllToGroupList(SkierList grouplist) {
		Skier[] skiers = grouplist.getSkierList();
		for (int i = 0; i < skiers.length; i++) {
			if(skiers[i].getSkiingGroup()==this.skiingGroup)
				groupList.add(skiers[i]);
		}
	}
	public void setPlayerNumber(GroupList grouplist) {
		for (int i = 0; i < groupList.size(); i++) {
			Skier skiski = groupList.get(i);
			skiski.setPlayerNumber(grouplist.firstPlayerNumber + i);
			skiski.setStartingTime(Utils.timeAdder(grouplist.firstStart, Utils.timeConverter(grouplist.startInterval * i)));
			groupList.set(i, skiski);
		}
	}
	@Override
	public String toString() {
		String skierString = "";
		for (int i = 0; i < groupList.size(); i++) {
			skierString = skierString+"Skier number " + i+1 +" - Name: "+groupList.get(i).getName()+", age: "+groupList.get(i).getAge()+", gender: "+groupList.get(i).getGender()+", playernumber: "+groupList.get(i).getPlayerNumber()+"\n";
		}
		return "This grouplist contains: "+skierString+"And their first start is "+Arrays.toString(firstStart)+", their startinterval is "+startInterval+" and their starting number is "+firstPlayerNumber;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(firstStart);
		result = prime * result + Objects.hash(groupList, firstPlayerNumber, skiingGroup, startInterval);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		GroupList other = (GroupList) obj;
		return Objects.equals(groupList, other.groupList) && firstPlayerNumber == other.firstPlayerNumber
				&& Arrays.equals(firstStart, other.firstStart) && Objects.equals(skiingGroup, other.skiingGroup)
				&& startInterval == other.startInterval;
	}
	
	public void timeConfigInput(GroupList grouplist, int[] firstStart, int startInterval, int firstSkier) {
		System.out.println("När ska den första starttiden vara? input timme: ");
				setFirstStart(0, firstStart[0]);
		System.out.println("input minut: ");
				setFirstStart(1, firstStart[1]);
		System.out.println("input sekund: ");
				setFirstStart(2, firstStart[2]);
		System.out.println("Ange startinteravll i sekunder: ");
				setStartInterval(startInterval);
		System.out.println("Ange första spelarens nummer, resterande spelares nummer kommer baseras på det"
				+ "här. Så om du väljer t.ex 101, kommer spelare två vara 102, tre 103, osv.");
				setFirstPlayerNumber(firstSkier);
	}
	public void generateGroupList(SkierList skierlist, String chosenGroup) {
		setSkiingGroup(chosenGroup);
		for (int i = 0; i < skierlist.getSkierLinkedListSize(); i++) {
			if (chosenGroup.equals(skierlist.getSkiingGroupAtIndex(i))) {
				addToGroupList(skierlist.getSkier(i));
			}
		}
	}
	public void generateGroupListTime(int[] firstStartTime, int startInterval, int firstPlayerNumber) {
		setFirstStart(firstStartTime);
		setStartInterval(startInterval);
		setFirstPlayerNumber(firstPlayerNumber);
		assignAllPlayerNumbersRandom();
		sortList();
		for (int i = 0; i < groupList.size(); i++) {
			setPlayerNumber(firstPlayerNumber+i, i);
		}
	}
	@Override
	public void setPlayerNumber(int playerNumber, int skierNumber) {
		Skier skier = getSkier(skierNumber);
		skier.setPlayerNumber(playerNumber);
		groupList.set(groupList.indexOf(skier), skier);
	}
	@Override
	/**
	 * Sorterar listan beroende på deras playernumber
	 */
	public void sortList() {
		Skier[] skierList = getSkierList();
		Arrays.sort(skierList);
		setSkierList(skierList);
	}
	/**
	 * Sorterar listan beroende på deras placering vid checkpoint
	 */
	public void sortListPlacingCheckpoint() {
		CompareSkierPlacingCheckpoint compare = new CompareSkierPlacingCheckpoint();
		Collections.sort(groupList, compare);
	}
	@Override
	public void assignAllPlayerNumbersRandom() {
		Skier[] skierList = new Skier[groupList.size()];
		double x = 0;
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			for (int j = 0; j < skierList.length; j++) {
				if ((int)x == skierList[j].getPlayerNumber() || (int)x==0) {
					x = (Math.random() * 10000);
				}
			}
			skierList[i].setPlayerNumber((int) x);
			groupList.set(i, skierList[i]);
		}
	}
	
}