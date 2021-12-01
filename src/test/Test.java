package test;

import UI.*;

public class Test {
	public static void main(String[] args) {
		UI ui = new UI();
		ui.titleText("hello world");
	}

	private static void testConsoleClock() {
		System.out.println("här kan man göra massa tester");
		
		Clock.print(1, 55, 1);
		Clock.print(15, 5, 1);
		Clock.print(15, 45, 60);
		
		Clock.prntCurrTime();
		
		Clock clck = new Clock();
		clck.run();
	}
}
