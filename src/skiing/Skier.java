package skiing;

import java.util.Scanner;

public class Skier {
	String name;
	int playerNumber;
	double speed; // Räkna speed i meter per sekund
	double position; // Räkna position i meter i banan
	int[] sTime = new int[3];
	boolean[] checkpointCheck;
	boolean goal;
	
	public Skier(String name, int playernumber, double speed, double position, int[] startingTime) {
		this.name = name;
		this.playerNumber = playernumber;
		this.speed = speed;
		this.position = position;
		this.sTime = startingTime;
	}

	public Skier() {
	}

	public void SkierDeclaration(int i) {
		this.playerNumber = i + 1;
		Scanner scan = new Scanner(System.in);
		System.out.println("Vad heter spelare nummer " + playerNumber);
		this.name = scan.next();
		System.out.println("Hur snabb är " + name + "? (Svara i m/s) ");
		this.speed = scan.nextDouble();
		System.out.println("Vart börjar " + name + "? (Svara i meter på banan) ");
		this.position = scan.nextDouble();
		System.out.println("När börjar " + name
				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");
		for (int j = 0; j < sTime.length; j++) {
			sTime[j] = scan.nextInt();
		}
	}
	public void SkierDeclarationtest(int i) {
		this.playerNumber = i + 1;
		System.out.println("Vad heter spelare nummer " + playerNumber);
		this.name = "Kalle";
		System.out.println("Hur snabb är " + name + "? (Svara i m/s) ");
		this.speed = 5;
		System.out.println("Vart börjar " + name + "? (Svara i meter på banan) ");
		this.position = 0;
		System.out.println("När börjar " + name
				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");
		for (int j = 0; j < sTime.length; j++) {
			sTime[j] = 10;
		}
	}

}