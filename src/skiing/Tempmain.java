package skiing;

import java.util.Scanner;

public class Tempmain {

	public static void main(String[] args) {
		System.out.println("Hur många tävlare?");
		Scanner scan = new Scanner(System.in);
		int amountOfPlayers = scan.nextInt();
		Skier[] list = new Skier[amountOfPlayers];
		list = playerDeclaration(amountOfPlayers);

		SkiSlope skiSlope = new SkiSlope();
		skiSlope.RaceTrack();
		list = SkiSlope.RaceTrackPlayer(list);

		String[][] checkpointTime = new String[list.length][skiSlope.checkpoints.length];
		String[] goalTime = new String[list.length];
		String currentTotalTime = "";

		double secondsPassed = 0;
		double minutesPassed = 0;
		for (double i = 0; i <= skiSlope.trackLength+10; i++) {
			for (int j = 0; j < list.length; j++) {
				list[j].position = list[j].position + list[j].speed;
				for (int k = 0; k < skiSlope.checkpoints.length; k++) {
					if (list[j].position >= skiSlope.checkpoints[k] && list[j].checkpointCheck[k] == false) {
						checkpointTime[j][k] = currentTotalTime;
						list[j].checkpointCheck[k] = true;
					}
				}
				for (int k = 0; k <= skiSlope.trackLength; k++) {
					if (list[j].position >= skiSlope.trackLength && list[j].goal == false)
					goalTime[j] = currentTotalTime;
				}
			}
			secondsPassed++;
			if (secondsPassed == 60) {
				minutesPassed++;
				secondsPassed = 0;
			}
			currentTotalTime = (minutesPassed + " minuter och " + secondsPassed + " sekunder");
		}
		for (int i = 0; i < list.length; i++) {
			System.out.println(list[i].name + " kom i mål vid " + goalTime[i]+ " och hen satte checkpoint ");
				for (int j = 0; j < checkpointTime[0].length; j++) {
				System.out.println(j+1 + " vid " + checkpointTime[i][j]+ ".");
			}
		}

//		int[] startTime = {9,30,30,30};
//		Skier ski1 = new Skier("Johan", 1, 15.0, 0.0, startTime);
//		
	}

	public static Skier[] playerDeclaration(int amountOfPlayers) {
		Skier[] list = new Skier[amountOfPlayers];
		for (int i = 0; i < list.length; i++) {
			System.out.println("Beskriv spelare nummer " + (i + 1));
			Skier skier = new Skier();
			skier.SkierDeclaration(i);
			list[i] = skier;
		}
		return list;
	}
}