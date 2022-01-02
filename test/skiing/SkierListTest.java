package skiing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SkierListTest {
	private static SkierHandler skierlist;
	private static Skier skier1;
	private static Skier skier2;
	private static Skier skier3;
	private static Skier skier4;
	private static Skier skier5;
	private static Skier skier6;
	private static Skier skier7;
	
	@BeforeAll
	static void init() {
		skierlist = new SkierHandler();
		skier1 = new Skier("Otto", "Kostmann", "Herr", 24);
		skier2 = new Skier("Otta", "Kastmann", "Herr", 24);
		skier3 = new Skier("Otta", "Kastmann", "Dam", 25);
		skier4 = new Skier("Otta", "Kastmann", "Herr", 27);
		skier5 = new Skier("Otta", "Kastmann", "Herr", 27);
		skier6 = new Skier("Otta", "Kastmann", "Dam", 24);
		skier7 = new Skier("Otta", "Kastmann", "Dam", 24);
		skierlist.addSkiertoList(skier1);
		skierlist.addSkiertoList(skier2);
		skierlist.addSkiertoList(skier3);
		skierlist.addSkiertoList(skier4);
		skierlist.addSkiertoList(skier5);
		skierlist.addSkiertoList(skier6);
		skierlist.addSkiertoList(skier7);
		
	}
	
	@Test
	void testSortList() {
//		System.out.println(skierlist.getUniqueClassesList());
	}

}
