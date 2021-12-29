package skiing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GroupListTest {
		private static GroupList g1;
		private static SkierList s1;
		private static int[] arr = {12, 12, 12};
		private static int[] arr2 = {12, 30, 00};
	@BeforeAll
	static void init() {
		s1 = new SkierList();
		s1.addSkiertoList( new Skier( "Left", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Otto", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Jessica", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Joacim", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "namn2", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Namn3", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Namn5", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Namn7", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Namn8", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Namn9", "Laft", "herr", 33 ));
		s1.addSkiertoList( new Skier( "Asa", "Laft", "dam", 23 ));
		s1.addSkiertoList( new Skier( "Åsa", "Laft", "dam", 23 ));
		s1.addSkiertoList( new Skier( "Britt", "Laft", "dam", 23 ));
		g1 = new GroupList();
		g1.generateGroupList(s1, "H33");
		g1.generateGroupListTime(arr, 30, 100);
	}
	
//	@Test
//	public void testToString() {
//		System.out.println(g1.toString());
//	}
	
	@Test
	public void testGetSkierFromPlayerNumber() {
		assertEquals(g1.getSkier(0), g1.getSkierFromPlayerNumber(100));
	}
	@Test
	public void testSetSkierGoalTimeFromPlayerNumber() {
		g1.setSkierGoalTimeFromPlayerNumber(100, arr2);
		assertEquals(g1.getSkierFromPlayerNumber(100).getGoalTime(), arr2);
	}
	@Test
	public void testSetSkierCheckpointTimeFromPlayerNumber() {
		g1.setSkierCheckpointTimeFromPlayerNumber(100, arr2);
		assertEquals(g1.getSkierFromPlayerNumber(100).getCheckpointTime(), arr2);
	}
	@Test
	public void testGetSkierCheckpointTimeFromPlayerNumber() {
		g1.setSkierCheckpointTimeFromPlayerNumber(100, arr2);
		assertEquals(g1.getSkierCheckpointTimeFromPlayerNumber(100), arr2);
	}
	@Test
	public void testGetSkierGoalTimeFromPlayerNumber() {
		g1.setSkierGoalTimeFromPlayerNumber(100, arr2);
		assertEquals(g1.getSkierGoalTimeFromPlayerNumber(100), arr2);
	}
	@Test
	public void testSortList() {
		g1.sortList();
		
	}
	@Test
	public void testSetStartingTimes() {
		g1.setStartingTimes();
		assertEquals(Arrays.toString(g1.getSkier(0).getStartingTime()), "[12, 12, 12]");
		assertEquals(Arrays.toString(g1.getSkier(1).getStartingTime()), "[12, 12, 42]");
		assertEquals(Arrays.toString(g1.getSkier(2).getStartingTime()), "[12, 13, 12]");
	}

}
