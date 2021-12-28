package checkpoint;

import java.util.Scanner;

import UI.Console;
import UI.UI;
import skiing.*;

public class CheckpointTime{
	
	static UI ui = new Console();
	Scanner scan = new Scanner(System.in);
	
	public Skier getSkier(SkierList list) {
		
		ui.postMsg("Mata in åkarnummer för att registrera mellantid");
		int playerNumber = scan.nextInt();
		
		Skier[] skiers = list.getSkierList();
		for(int i=0; i<skiers.length; i++) { 
			if(skiers[i].playerNumber == playerNumber && skiers[i].checkpointTime.equals(0)) { 
				setCheckpointTime(skiers[i]); 
			}
		}return null;		
	}
	public void setCheckpointTime(Skier skier) {
		Clock clock = skier.getClock();
		clock.setCheckPoint();
	}
}
