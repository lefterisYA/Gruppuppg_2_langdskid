package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

	//TODO rensa gamla/oanvända metoder

public class SkierList {
	
	List<Skier> skierLinkedList = new LinkedList<Skier>();


	public Skier getSkier(int skierNumber) {return skierLinkedList.get(skierNumber);}
	public void setSkier(Skier skier, int indexnumber) {skierLinkedList.set(indexnumber, skier);}
	public void addSkiertoList(Skier skier) {skierLinkedList.add(skier);}
	public void setSkierList(Skier[] skierlist) {skierLinkedList = Arrays.asList(skierlist);}
	
	public String getSkiingClass(Skier skier) {
		return Character.toUpperCase(skier.gender.charAt(0)) + Integer.toString(skier.age);
	}
	
	public String getSkiingClassAtIndex(int i) {
		Skier skier = skierLinkedList.get(i);
		return getSkiingClass(skier);
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
		skier.playerNumber = playerNumber;
		skierLinkedList.set(skierLinkedList.indexOf(skier), skier);
	}
	
	public void assignAllPlayerNumbersRandom() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		double x = 0;
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			for (int j = 0; j < skierList.length; j++) {
				if ((int)x == skierList[j].playerNumber || (int)x==0) {
					x = (Math.random() * 10000);
				}
			}
			skierList[i].playerNumber = (int) x;
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
			skierString = skierString+"Skier number " + i+1 +" - Name: "+skierLinkedList.get(i).name+", age: "+skierLinkedList.get(i).age+", gender: "+skierLinkedList.get(i).gender+", playernumber: "+skierLinkedList.get(i).playerNumber+"\n";
		}
		return "This list contains: "+skierString;
	}
	
}
