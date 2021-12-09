package skiing;

import java.util.LinkedList;
import java.util.List;

public class SkierList {
	static List<Skier> skierLinkedList = new LinkedList<Skier>();
	
	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		for (int i = 0; i < SkierList.skierLinkedList.size(); i++) {
			skierList[i] = skierLinkedList.get(i);
		}
		return skierList;
	}
	
	public void addSkierList(Skier skier){
		skierLinkedList.add(skier);
		if (playerNumberExists(skier)==false)
		setPlayerNumberUnique(skier);	
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
			if (skier.playerNumber==skierList[i].playerNumber) {
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
	
	public void setPlayerNumberUnique(Skier skier) {
		skier.playerNumber=(skierLinkedList.indexOf(skier)+1);
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
	

}
