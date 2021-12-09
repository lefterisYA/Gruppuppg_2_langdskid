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

			/* 1. DATA OM ÅKAREN */ //namn, åkarnummer, placering, sluttid och klubb
			skier = skierList[i]; // tilldela variabeln skier den aktuella åkaren
			ui.postMsg("Namn: " + skier.getPlayerNumber());
			ui.postMsg("Namn: " + skier.getName());
			ui.postMsg("Namn: " + skier.getGender());
			ui.postMsg("Placering: " + (i+1));

			String goalTime = Utils.toString(Utils.getInstance().timeConverter(skier.getGoalTime()));
			ui.postMsg("Sluttid: " + goalTime);

			ui.postMsg("===============================");
		}
	}



}
