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
		skier.firstName=scan.nextLine();
//		skier.firstName="Olle";
		ui.postMsg("Ange efternamn:");
		skier.lastName=scan.nextLine();
//		skier.lastName="Karlsson";
		ui.postMsg("Ange kön (Dam eller Herr):");
		skier.gender=scan.nextLine();
//		skier.gender="Herr";
		ui.postMsg("Ange ålder:");
		skier.age=scan.nextInt();
//		skier.age=24;
		return skier;
	}
}
