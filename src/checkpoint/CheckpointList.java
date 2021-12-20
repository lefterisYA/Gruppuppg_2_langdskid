package checkpoint;

import UI.Console;
import UI.UI;
import skiing.*;
import java.util.*;


public class CheckpointList {
	
	static UI ui = new Console();
	
	public void test(List<Skier> skierList) {
				
		Collections.sort(skierList, new Compare());
		
		ui.postMsg("MELLANTIDER");
			
		for(int i=0; i<skierList.size(); i++) { 
			
			if(!(skierList.get(i).checkpointTime.equals(0))) {
			ui.postMsg("Placering: " + (i+1));
			ui.postMsg("Åkarnummer: " + skierList.get(i).getPlayerNumber());
			ui.postMsg("Namn: " + skierList.get(i).getName());
			ui.postMsg("Mellantid: " + skierList.get(i).getCheckpointTime());
			}
		} 
}
}
