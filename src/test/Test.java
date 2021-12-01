package test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import UI.*;

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
		UI ui = new UI();
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
}
