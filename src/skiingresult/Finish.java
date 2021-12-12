package skiingresult;

import UI.Clock;
import skiing.*;

public class Finish {

	public Skier getSkier(SkierList list, int playerNumber) {
		//används till att från guit frågar finish metoden som ska fråga om åkarnummret
		Skier[] skiers = list.getSkierList();// får en lista med alla skidåkare
		for(int i=0; i<skiers.length; i++) { // loopar igenom skierList
			if(skiers[i].playerNumber == playerNumber) { // om aktuell skidåkare har samma startnumber som man skickat in
				setFinishTime(skiers[i]); // ropar på metod som sätter sluttiden
			}
		}
		return null;
	}
	
	public void setFinishTime(Skier skier) {
		Clock clock = new Clock();
//		int goalTime = skier.setGoalTime(clock.getCurrTime()); // TODO get correct method to set gaolTime to current computer time
		// sätt aktuell datortid i "goalTime" på åkaren
	}
}
