package skiing;

import java.util.LinkedList;

public class test {

	public static void main(String[] args) throws InterruptedException {
		Skier skibro = new Skier("Olle", 5, 5, 0);
		Skier skiboy = new Skier("Kalle", 5, 5, 0);
		SkierList skiHandler = new SkierList();
		skiHandler.addSkierList(skibro);
		skiHandler.addSkierList(skiboy);
		Skier[] skiboiz = skiHandler.getSkierList();
		boolean x = skiHandler.playerNumberExists(skiboy);
		boolean y = skiHandler.playerNumberUnique(skiboy);
		boolean z = skiHandler.allPlayerNumbersUnique();
		System.out.println("hi");
		skiHandler.assignAllPlayerNumbersUnique();
		System.out.println();
				
}
}