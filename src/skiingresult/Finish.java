package skiingresult;

import skiing.*;
import timekeeping.TimeUtils;
import timekeeping.Timekeeper;

public class Finish {

	public Skier getSkier(SkierList list, int playerNumber) {
		//används till att från guit frågar finish metoden som ska fråga om åkarnummret
		Skier[] skiers = list.getSkierList();// får en lista med alla skidåkare
		for(int i=0; i<skiers.length; i++) { // loopar igenom skierList
			if(skiers[i].getPlayerNumber() == playerNumber) { // om aktuell skidåkare har samma startnumber som man skickat in
				setFinishTime(skiers[i]); // ropar på metod som sätter sluttiden
			}
		}
		return null;
	}

	public void setFinishTime(Skier skier) {
		Timekeeper timekeeper = skier.getTimekeeper(); // TODO Skier object should have a running clock from starting point
//		timekeeper.setFinish();
		
//		skier.setGoalTime(TimeUtils.timeConverter(timekeeper.getFinishTimeMillis()/1000)); // skriver målgångstiden i millisekunder till åkaren
		System.out.println("Sluttid: " + skier.getTimekeeper().getFinishTime());
		
	}
}
