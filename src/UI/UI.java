package UI;

import java.util.Scanner;

import skiing.Skier;

public class UI {
	private final Scanner sc;
	
	public UI() {
		sc = new Scanner(System.in);
	}

	// Används för att anmäla sig som skidåkare med scanner
	public Skier addSkierDialog(int playerNumber) {
		playerNumber++;

		System.out.println("Vad heter spelare nummer " + playerNumber);
		String name = sc.next();

		System.out.println("Hur snabb är " + name + "? (Svara i m/s) ");
		Double speed = sc.nextDouble();

		System.out.println("Vart börjar " + name + "? (Svara i meter på banan) ");
		Double position = sc.nextDouble();

		System.out.println("När börjar " + name
				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");

		int[] sTime = new int[3];
		for (int j = 0; j < sTime.length; j++) {
			sTime[j] = sc.nextInt();
		}
		
		return new Skier(name, playerNumber, speed, position, sTime);
	}

}
