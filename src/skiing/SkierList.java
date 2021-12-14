package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

	//TODO rensa gamla/oanvända metoder

public class SkierList {
	public String skiingClass;
	int[] firstStart = new int[3];
	int[] startInterval = new int[3];
	int firstPlayerNumber;
	List<Skier> skierLinkedList = new LinkedList<Skier>();

	public int[] getFirstStart() {
		return firstStart;
	}

	public void setFirstStart(int[] firstStart) {
		this.firstStart = firstStart;
	}

	public int[] getStartInterval() {
		return startInterval;
	}

	public void setStartInterval(int[] startInterval) {
		this.startInterval = startInterval;
	}

	public int getFirstPlayerNumber() {
		return firstPlayerNumber;
	}

	public void setFirstPlayerNumber(int firstPlayerNumber) {
		this.firstPlayerNumber = firstPlayerNumber;
	}

	public void assignSkiingClass(Skier skier) {
		skiingClass = Character.toUpperCase(skier.gender.charAt(0)) + Integer.toString(skier.age);
	}

	public String getSkiingClass(Skier skier) {
		return Character.toUpperCase(skier.gender.charAt(0)) + Integer.toString(skier.age);
	}

	public String getSkiingClass() {
		return skiingClass;
	}

	public void setSkierList(Skier[] skierlist) {
		skierLinkedList = Arrays.asList(skierlist);
//		for (int i = 0; i < skierlist.length; i++) {
//			skierLinkedList.add(skierlist[i]);
//		}
	}

	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		for (int i = 0; i < skierLinkedList.size(); i++) {
			skierList[i] = skierLinkedList.get(i);
		}
		return skierList;
	}

	public Skier getSkier(int skierNumber) {
		return skierLinkedList.get(skierNumber);
	}
	public void setSkier(Skier skier, int indexnumber) {
		skierLinkedList.set(indexnumber, skier);
	}

	public void addSkiertoList(Skier skier) {
		skierLinkedList.add(skier);
	}

	public boolean playerNumberExists(Skier skier) {
		if (skier.playerNumber > 0)
			return true;
		else
			return false;
	}

	public boolean playerNumberUnique(Skier skier) {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			if (skier.playerNumber == skierList[i].playerNumber) {
				return false;
			}
		}
		return true;
	}

	public boolean allPlayerNumbersUnique() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			for (int j = 0; j < skierList.length; j++) {
				if (skierList[i].playerNumber == skierList[j].playerNumber)
					return false;
			}
		}
		return true;
	}

	public void setPlayerNumberUnique(Skier skier) {
		skier.playerNumber = (skierLinkedList.indexOf(skier) + 1);
		skierLinkedList.set(skierLinkedList.indexOf(skier), skier);
	}

	public void setPlayerNumber(int playerNumber, int skierNumber) {
		Skier skier = getSkier(skierNumber);
		skier.playerNumber = playerNumber;
		skierLinkedList.set(skierLinkedList.indexOf(skier), skier);
	}

	public void assignAllPlayerNumbersUnique() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			skierList[i].playerNumber = skierLinkedList.indexOf(skierList[i]);
			skierLinkedList.set(i, skierList[i]);
		}
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

}
