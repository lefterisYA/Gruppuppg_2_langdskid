package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

	//TODO rensa gamla/oanvända metoder

public class SkierList {
	
	private List<Skier> skierLinkedList = new LinkedList<Skier>();


	public Skier getSkier(int skierNumber) {return skierLinkedList.get(skierNumber);}
	public void setSkier(Skier skier, int indexnumber) {skierLinkedList.set(indexnumber, skier);}
	public void addSkiertoList(Skier skier) {skierLinkedList.add(skier);}
	public void setSkierList(Skier[] skierlist) {skierLinkedList = Arrays.asList(skierlist);}
	
	public String getSkiingGroup(Skier skier) {
		return Character.toUpperCase(skier.getGender().charAt(0)) + Integer.toString(skier.getAge());
	}
	public int getSkierLinkedListSize() {
		return skierLinkedList.size();
	}
	
	public String getSkiingGroupAtIndex(int i) {
		Skier skier = skierLinkedList.get(i);
		return getSkiingGroup(skier);
	}
	
	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		for (int i = 0; i < skierLinkedList.size(); i++) {
			skierList[i] = skierLinkedList.get(i);
		}
		return skierList;
	}
	
	public void setPlayerNumber(int playerNumber, int skierNumber) {
		Skier skier = getSkier(skierNumber);
		skier.setPlayerNumber(playerNumber);
		skierLinkedList.set(skierLinkedList.indexOf(skier), skier);
	}
	
	public void assignAllPlayerNumbersRandom() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		double x = 0;
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			for (int j = 0; j < skierList.length; j++) {
				if ((int)x == skierList[j].getPlayerNumber() || (int)x==0) {
					x = (Math.random() * 10000);
				}
			}
			skierList[i].setPlayerNumber((int) x);
			skierLinkedList.set(i, skierList[i]);
		}
	}

	public void sortList() {
		Skier[] skierList = getSkierList();
		Arrays.sort(skierList);
		setSkierList(skierList);
	}
	@Override
	public int hashCode() {
		return Objects.hash(skierLinkedList);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SkierList other = (SkierList) obj;
		return Objects.equals(skierLinkedList, other.skierLinkedList);
	}
	@Override
	public String toString() {
		String skierString = "";
		for (int i = 0; i < skierLinkedList.size(); i++) {
			skierString = skierString+"Skier number " + i+1 +" - Name: "+skierLinkedList.get(i).getName()+", age: "+skierLinkedList.get(i).getAge()+", gender: "+skierLinkedList.get(i).getGender()+", playernumber: "+skierLinkedList.get(i).getPlayerNumber()+"\n";
		}
		return "This list contains: "+skierString;
	}
	
	public List<String> getUniqueGroupesList() {
		String currentGroup;
		List<String> uniqueGroupsList = new LinkedList<String>(); 
		for (int i = 0; i < skierLinkedList.size(); i++) {
			currentGroup = getSkiingGroupAtIndex(i);
			if(uniqueGroupsList.contains(currentGroup)==false) {
				uniqueGroupsList.add(currentGroup);
			}
		}
		return uniqueGroupsList;
	}
	
}
