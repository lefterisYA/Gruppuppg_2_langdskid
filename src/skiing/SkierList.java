package skiing;

import java.util.LinkedList;

public class SkierList {
	static LinkedList<Skier> skierLinkedList = new LinkedList<Skier>();
	
	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		for (int i = 0; i < SkierList.skierLinkedList.size(); i++) {
			skierList[i] = skierLinkedList.get(i);
		}
		return skierList;
	}
	
	public void addSkierList(Skier skiboi){
		skierLinkedList.add(skiboi);
		if (playerNumberExists(skiboi)==false)
		setPlayerNumberUnique(skiboi);	
	}
	
	public boolean playerNumberExists(Skier skiboi) {
		if (skiboi.playerNumber > 0)
			return true;
		else
			return false;
	}
	public boolean playerNumberUnique(Skier skiboi) {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			if (skiboi.playerNumber==skierList[i].playerNumber) {
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
				if (skierList[i].playerNumber==skierList[j].playerNumber)
					return false;
			}
		}
		return true;
	}
	
	public void setPlayerNumberUnique(Skier skiboi) {
		skiboi.playerNumber=(skierLinkedList.indexOf(skiboi)+1);
		skierLinkedList.set(skierLinkedList.indexOf(skiboi), skiboi);
	}
	
	public void assignAllPlayerNumbersUnique() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		skierList = getSkierList();
		for (int i = 0; i < skierList.length; i++) {
			skierList[i].playerNumber = skierLinkedList.indexOf(skierList[i]);
			skierLinkedList.set(i, skierList[i]);
		}
	}
	

}
