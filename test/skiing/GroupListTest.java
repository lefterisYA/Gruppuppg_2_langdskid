package skiing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GroupListTest {
		private static GroupList g1;
		private static SkierList s1;
	@BeforeAll
	void init() {
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
	}
	
	@Test
	public void testToString() {
		System.out.println(g1.toString());
	}

}
