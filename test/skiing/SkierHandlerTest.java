package skiing;

import skiing.SkierHandler;
import skiing.Group;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SkierHandlerTest {

	@Test
	void testGenerateAllGroupsAndGetGroup() {
		
		Group group;
		SkierHandler skiers = new SkierHandler();
		skiers.addSkiertoList(new Skier("Bjørn", "Dæhlie", "herr", 50));
		skiers.addSkiertoList(new Skier("Therese", "Johaug", "dam", 30));
		skiers.addSkiertoList(new Skier("Vegard", "Ullvang", "herr", 50));
		skiers.addSkiertoList(new Skier("Maiken", "Falla", "dam", 30));
		skiers.generateAllGroups();
		
		group = skiers.getGroup("H50");

		assertEquals(group, skiers.getGroup("H50"));
	}
	@Test
	void testAssignAllPlayerNumbersRandom() {

		SkierHandler skiers = new SkierHandler();
		skiers.addSkiertoList(new Skier("Bjørn", "Dæhlie", "herr", 50));
		skiers.addSkiertoList(new Skier("Therese", "Johaug", "dam", 30));

		skiers.assignAllPlayerNumbersRandom();

		assertNotEquals(skiers.getSkier(0).getPlayerNumber(), 0);
		assertNotEquals(skiers.getSkier(1).getPlayerNumber(), 0);
	}
	@Test
	void testGetSkierLinkedListSize() {
		
		SkierHandler skiers = new SkierHandler();
		skiers.addSkiertoList(new Skier("Bjørn", "Dæhlie", "herr", 50));
		skiers.addSkiertoList(new Skier("Therese", "Johaug", "dam", 30));

		assertEquals(2, skiers.getSkierLinkedListSize());
	}

}
