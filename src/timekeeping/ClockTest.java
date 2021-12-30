package timekeeping;

import timekeeping.TimeUtils;
import skiing.Skier;
import skiingresult.Finish;

public class ClockTest {
	public static void main(String [] args) {
		
		// Exempel på användning: 
		
		Skier skier = new Skier();
		Timekeeper testTimekeeper = new Timekeeper(); // kan skapas när en skier skapas upp 
		skier.setTimekeeper(testTimekeeper); // sätt timekeepern på åkar-objektet		
		testTimekeeper.setStartTime(System.currentTimeMillis()); // sätts när åkaren startar sitt lopp
		
		System.out.println("Starttid: " + testTimekeeper.getStartTimeString()); 
		
		skier.getTimekeeper().getRunningTimeMillis(); // hur länge har åkaren varit ute o kört
		
		try {
			Thread.sleep(3000); //väntar 3 sekunder
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		testTimekeeper.setCheckPoint(); // sätter en mellantid och får tillbaka skillnaden från startTiden
		System.out.println("Mellantid: " + testTimekeeper.getCheckPointTime());
		
		
		try {
			Thread.sleep(5000); //väntar 5 sekunder
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		Finish finish = new Finish();
		finish.setFinishTime(skier);
		
	}
}
