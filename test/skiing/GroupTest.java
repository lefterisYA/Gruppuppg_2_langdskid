package skiing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class GroupTest {
	
	@Test
	void testGetSkierFromPlayerNumber() {
		SkierHandler skiers = new SkierHandler();
		skiers.addSkiertoList(new Skier("Bjørn", "Dæhlie", "herr", 50));
		skiers.addSkiertoList(new Skier("Therese", "Johaug", "dam", 30));

		skiers.assignAllPlayerNumbersRandom();
		int testPlayerNumber = skiers.getSkier(0).getPlayerNumber();

		assertEquals(skiers.getSkier(0).getPlayerNumber(), testPlayerNumber);
	}

}
