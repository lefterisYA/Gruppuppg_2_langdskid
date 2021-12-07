package skiingresult;
import UI.Console;
import UI.UI;
import common.Utils;
import skiing.*;

public class ResultBoard {

	static UI ui = new Console();
	
	public ResultBoard() {
	}

	public void test(Skier[] skierList) {
		Skier skier;
		ui.postMsg("RESULTAT-LISTA");
		ui.postMsg("===============================");
		ui.postMsg("");
		
		for(int i=0; i<skierList.length; i++) { // loopa alla deltagare
			
			/* 1. DATA OM ÅKAREN */
			skier = skierList[i]; // tilldela variabeln skier den aktuella åkaren
			ui.postMsg("Namn: " + skier.name);
			ui.postMsg("Placering: " + (i+1));
			
			/* 2. MELLANTIDER */
			int[] checkPointTimes = skier.checkpointTime; // alla mellantider för en åkare
			for(int j=0; j<checkPointTimes.length; j++) { // loopar alla mellantider
				ui.postMsg("Mellantid " + (j+1) + ": " + Utils.toString(Utils.getInstance().timeConverter(checkPointTimes[j])));
			}
			
			/* 3. MEDELHASTIGHET */
			// hämtar medelhastigheten som är en double, gör om den till sträng
			Double speed = skier.speed;
			ui.postMsg("Medelhastighet: " + String.format("%.2f", speed) + " m/s"); // formatera double-strängen med 2 decimaler
			
			/* 4. SLUTTID */
			String goalTime = Utils.toString(Utils.getInstance().timeConverter(skier.getGoalTime()));
			ui.postMsg("Sluttid: " + goalTime);
			
			ui.postMsg("===============================");
		}
	}
	
	
	
}
