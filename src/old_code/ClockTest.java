package old_code;


public class ClockTest {
	public static void main(String [] args) {
		Clock testClock = new Clock(); // skapar upp klockan. 
		
//		Skier skier = new Skier();
//		skier.setClock(testClock);
		
//		skier.getClock().getRunningTimeMillis(); // hur länge har åkaren varit ute o kört
		
		try {
			Thread.sleep(3000); //väntar 3 sekunder
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		testClock.setCheckPoint(); // sätter en mellantid och får tillbaka skillnaden från startTiden
//		System.out.println("Mellantid: " + testClock.getCheckPointTime());
		
		
		try {
			Thread.sleep(5000); //väntar 5 sekunder
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		
		Finish finish = new Finish();
//		finish.setFinishTime(skier);
		
	}
}
