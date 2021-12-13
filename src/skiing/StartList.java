package skiing;

import java.util.Arrays;

import common.Utils;

public class StartList {
	public static void main(String[] args) {
		Skier skier = SkierDeclaration.SkierDeclare();
		Skier skier2 = SkierDeclaration.SkierDeclare();
		SkierList skierlist = new SkierList();
		classCheck(skier, skierlist);
		skierlist.addSkiertoList(skier);
		skierlist.addSkiertoList(skier2);
		skierlist.assignAllPlayerNumbersRandom();
		setPlayerNumber(skier, skierlist);
	}

	private static void classCheck(Skier skier, SkierList skierlist) {
		if (skierlist.getSkiingClass(skier)!=skierlist.getSkiingClass()) {
			skierlist.assignSkiingClass(skier);
		}
	}
	 
	private static void setPlayerNumber(Skier skier, SkierList skierlist) {
		skierlist.firstStart[0]=12;
		skierlist.startInterval[2]=30;
		skierlist.firstPlayerNumber=101;

		setPlayerNumber(skier, skierlist);
		for (int i = 0; i < skierlist.skierLinkedList.size(); i++) {
			skierlist.setPlayerNumber(skierlist.firstPlayerNumber+i, i);
			skier.startingTime=Utils.timeConverter(Utils.timeConverter(skierlist.firstStart)
					+(Utils.timeConverter(skierlist.startInterval)*i));
		}
	}

}
