package skiing;

import java.util.Scanner;

import UI.Console;
import UI.UI;

public class SkierDeclaration {
	public static Skier SkierDeclare() {
		Scanner scan = new Scanner(System.in);
		UI ui = new Console();
		Skier skier = new Skier();
		ui.postMsg("Ange förnamn:");
//		skier.firstName=scan.nextLine();
		skier.setFirstName("Olle");
		ui.postMsg("Ange efternamn:");
//		skier.lastName=scan.nextLine();
		skier.setLastName("Karlsson");
		ui.postMsg("Ange kön (Dam eller Herr):");
//		skier.gender=scan.nextLine();
		skier.setGender("Herr");
		ui.postMsg("Ange ålder:");
		skier.setAge(scan.nextInt());
//		skier.age=24;
		Skier newskier = new Skier(skier.getFirstName(), skier.getLastName(), skier.getGender(), skier.getAge());
		return newskier;
	}
}
