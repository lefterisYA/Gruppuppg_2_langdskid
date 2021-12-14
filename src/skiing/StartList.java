package skiing;

import java.util.Arrays;

import common.Utils;

public class StartList {
	
	//Exempel i main på hur startlistan kan användas
	//skierlist.firstStart och skierlist.startInterval lär jag
	//göra om efter att jag vet mer hur timer kommer att fungera
	
	
//	public static void main(String[] args) {
//		int firstStart=12;
//		int startInterval=30;
//		int firstPlayerNumber=101;
//		Skier skier = SkierDeclaration.SkierDeclare();
//		Skier skier2 = SkierDeclaration.SkierDeclare();
//		SkierList skierlist = new SkierList();
//		classCheck(skier, skierlist);
//		skierlist.addSkiertoList(skier);
//		skierlist.addSkiertoList(skier2);
//		skierlist.assignAllPlayerNumbersRandom();
//		skierlist.sortList();
//		setPlayerNumber(skierlist);
//	}

	public static void classCheck(Skier skier, SkierList skierlist) {
		if (skierlist.getSkiingClass(skier)!=skierlist.getSkiingClass()) {
			skierlist.assignSkiingClass(skier);
		}
	}
	 
	public static void setPlayerNumber(SkierList skierlist, 
			int firstStart, int startInterval, int firstPlayerNumber) {
		skierlist.firstStart[0]=firstStart;
		skierlist.startInterval[2]=startInterval;
		skierlist.firstPlayerNumber=firstPlayerNumber;
		for (int i = 0; i < skierlist.skierLinkedList.size(); i++) {
			Skier skiski = skierlist.getSkier(i);
			skiski.playerNumber=skierlist.firstPlayerNumber+i;
			skiski.startingTime=Utils.timeConverter(Utils.timeConverter(skierlist.firstStart)
					+(Utils.timeConverter(skierlist.startInterval)*i));
			skierlist.setSkier(skiski, i);
		}
	}

}
