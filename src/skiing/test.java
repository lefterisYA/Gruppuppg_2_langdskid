package skiing;

public class test {

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.nanoTime();
		for(int i = 0;i<10;i++) {
			Thread.sleep(100);
			System.out.println("helo");
		}
		long endTime = System.nanoTime();

		double duration = ((endTime - startTime)/1000000000)+((endTime - startTime)%1000000000);  //divide by 1000000 to get milliseconds.
		System.out.println(duration);
	}

}
