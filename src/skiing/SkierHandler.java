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
	/**
	 * Skriv in skidåkarklassen (group) så returneras skidåkarklassen i ett Group objekt.
	 * @param group (skidåkarkklass)
	 * @return den skidåkarklassen i ett Group objekt.
	 */
	public Group getGroup(String group){
		Group groups = new Group(group);
		for (int i = 0; i < allGroups.size(); i++) {
			if(allGroups.get(i).getSkiingGroup().equals(group)) {
				groups=allGroups.get(i);
			}
		}
		return groups;
		
		
	}
/**
 * När detta objekt har skidåkare i sig kan man använda denna metod för att generera alla skidklasser (groups)
 * som möjligtvis kan finnas baserat på de skidåkare i objektet.
 * 
 * Finns en risk att denna metod är lite överkomplicerad... Se getUniqueGroupList om du vill göra om den
 * men just nu funkar den i alla fall.
 */
	public void generateAllGroups() {
		if(allGroups.isEmpty())
		allGroups.add(new Group(allSkiers.get(0), getSkiingGroup(allSkiers.get(0))));
		for (int i = 0; i < allSkiers.size(); i++) {
			boolean newgroupcheck = false;
			int groupatj = 0;
			for (int j = 0; j < allGroups.size(); j++) {
				if (!(getSkiingGroup(allSkiers.get(i)).equals(allGroups.get(j).getSkiingGroup()))) {
					newgroupcheck=true;
				} else {
					newgroupcheck=false;
					groupatj = j;
					break;
				}
				}
				
			if(newgroupcheck==true && !(allGroups.contains(allSkiers.get(i)))) {
				allGroups.add(new Group(allSkiers.get(i), getSkiingGroup(allSkiers.get(i))));
			}
			else if (!(allGroups.contains(allSkiers.get(i)))){
				allGroups.get(groupatj).addToGroup(allSkiers.get(i));
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

	@Override
	public int hashCode() {
		return Objects.hash(allGroups, allSkiers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkierHandler other = (SkierHandler) obj;
		return Objects.equals(allGroups, other.allGroups) && Objects.equals(allSkiers, other.allSkiers);
	}
	
}
