package skiing;

import java.util.LinkedList;

public class SkierListHandler {
	static LinkedList<Skier> skierLinkedList = new LinkedList<Skier>();
	
	public Skier[] getSkierList() {
		Skier[] skierList = new Skier[skierLinkedList.size()];
		for (int i = 0; i < SkierListHandler.skierLinkedList.size(); i++) {
			skierList[i] = skierLinkedList.get(i);
		}
		return skierList;
	}
	
	public void addSkierList(Skier skiboi){
		skierLinkedList.add(skiboi);
		if (skiboi.playerNumberExists()==false) {
		setPlayerNumber(skiboi);	
		}
		skierLinkedList.removeLast();
		skierLinkedList.add(skiboi);
	}
	
	public void setPlayerNumber(Skier skiboi) {
		skiboi.playerNumber=(skierLinkedList.indexOf(skiboi)+1);
	}

}
