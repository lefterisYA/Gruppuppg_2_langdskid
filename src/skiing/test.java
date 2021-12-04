package skiing;

import java.util.LinkedList;

public class test {

	public static void main(String[] args) throws InterruptedException {
		Skier skibro = new Skier("Olle", 5, 0);
		Skier skiboy = new Skier("Kalle", 5, 0);
		SkierListHandler skiHandler = new SkierListHandler();
		skiHandler.addSkierList(skibro);
		skiHandler.addSkierList(skiboy);
		Skier[] skiboiz = skiHandler.getSkierList();
		System.out.println("hi");
				
}
}