package test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import UI.*;
import skiing.Skier;

public class Test {
	public static void main(String[] args) {
//		testConsoleClock();
		run();
		
//		System.exit(0);
		
	}

	private static void testConsoleClock() {
//		System.out.println("här kan man göra massa tester");
		
//		System.out.println( Clock.getInAscii(1, 55, 1) );
		
//		System.out.println( Clock.getCurrTimeInAscii() );
		
//		Clock clck = new Clock();
//		clck.run();
	}

	public static void run() {
		GUI ui = new GUI();
		ui.titleText("hello world");
		Clock clk = new Clock();

	    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
	    	@Override
	    	public void run() {
//	    		System.out.println( Clock.getCurrTimeInAscii() );
//	    		ui.bodyText( "\n" + clk.getCurrTimeInAscii() );
	    		ui.bodyText( "\n" + clk.getCurrTime() );
	    	}
	    }, 0, 20, TimeUnit.MILLISECONDS);
	}

//	Test om jag inte orkar skriva i alla individuella värden
	public static Skier skierDeclarationtest(int i) {
		int playerNumber = i + 1;
		System.out.println("Vad heter spelare nummer " + playerNumber);
		String name = ("Kalle" + playerNumber);
		System.out.println("Hur snabb är " + name + "? (Svara i m/s) ");
		Double speed = playerNumber * 0.5;
		System.out.println("Vart börjar " + name + "? (Svara i meter på banan) ");
		Double position = 0.0;
		System.out.println("När börjar " + name
				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");
		int[] sTime = new int[3];
		for (int j = 0; j < sTime.length; j++) {
			sTime[j] = 10;
		}
		return new Skier(name, playerNumber, speed, position, sTime);
	}
}
