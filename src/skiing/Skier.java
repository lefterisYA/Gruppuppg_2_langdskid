package skiing;

import java.util.Scanner;

public class Skier extends Person implements Comparable<Skier> {
	int playerNumber; // Sparar personens nummer, i den ordning dom blivit anmälda i tävlingen, inte
						// om dom kom först eller sist
	double speed; // Räknar speed i meter per sekund
	double position; // Räknar position i meter i banan
	int[] sTime = new int[3]; // Starttiden H,M,S
	boolean[] checkpointCheck; // Används för att se om personen gått igenom en checkpoint
	boolean goal = false; // Används för att se om personen gått i mål
	int goalTime;
	int[] checkpointTime;

	// constructor för skidåkare som just nu inte används
	public Skier(String name, int playernumber, double speed, double position, int[] startingTime) {
		super.name = name;
		this.playerNumber = playernumber;
		this.speed = speed;
		this.position = position;
		this.sTime = startingTime;
	}

	// En constructor som jag använder för att deklarera en array (list) med
	// skidåkare utan några värden.
	public Skier() {
	}

	// Används för att anmäla sig som skidåkare med scanner
	public void SkierDeclaration(int i) {
		this.playerNumber = i + 1;
		Scanner scan = new Scanner(System.in);

		System.out.println("Vad heter spelare nummer " + playerNumber);
		super.name = scan.next();

		System.out.println("Hur snabb är " + name + "? (Svara i m/s) ");
		this.speed = scan.nextDouble();

		System.out.println("Vart börjar " + name + "? (Svara i meter på banan) ");
		this.position = scan.nextDouble();

		System.out.println("När börjar " + name
				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");
		for (int j = 0; j < sTime.length; j++) {
			sTime[j] = scan.nextInt();
			scan.close();
		}
	}

//	Test om jag inte orkar skriva i alla individuella värden
	public void SkierDeclarationtest(int i) {
		this.playerNumber = i + 1;
		System.out.println("Vad heter spelare nummer " + playerNumber);
		this.name = ("Kalle" + this.playerNumber);
		System.out.println("Hur snabb är " + name + "? (Svara i m/s) ");
		this.speed = this.playerNumber * 0.5;
		System.out.println("Vart börjar " + name + "? (Svara i meter på banan) ");
		this.position = 0;
		System.out.println("När börjar " + name
				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");
		for (int j = 0; j < sTime.length; j++) {
			sTime[j] = 10;
		}
	}
	
	//Slumpar hastigheten lite grann
	public void speedRandom() {
		double random = Math.random() * 3;
		if (Math.random() < 0.5) {
			random = -random;
		}
		this.speed = this.speed + (Math.round(random * 100.0) / 100.0);
		if (this.speed < 1)
			this.speed = 1;
	}

	//Jämför med tid i mål
	@Override
	public int compareTo(Skier o) {
		if (goalTime > o.goalTime)
			return 1;
		if (goalTime < o.goalTime)
			return -1;
		else
			return 0;
	}

}