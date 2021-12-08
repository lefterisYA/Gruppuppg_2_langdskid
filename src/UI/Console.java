package UI;

import java.util.Scanner;

import skiing.Skier;

public class Console implements UI {
	private Scanner sc;
	
	public Console() {
			sc = new Scanner(System.in);
	}

	public void end() {
		sc.close();
	}
	
	public int getUserInt(String msg) {
		System.out.println(msg);
		return sc.nextInt();
	}
	
	public String getUserStrng(String msg) {
		System.out.println(msg);
		return sc.next();
	}

	public Double getUserDouble(String msg) {
		System.out.println(msg);
		return sc.nextDouble();
	}
	
	public void postMsg(String msg) {
		System.out.println(msg);
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

	@Override
	public void showIntroScreen() {
		// TODO Auto-generated method stub
	}

	@Override
	public void clearMsgScreen() {
		// Console doesn't support clearing inside of Eclipse. 
		System.out.println("\n".repeat(10));
	}

	/*
	@Override
	public void showScreen(Screen newScreen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Screen getNextScreen() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}
