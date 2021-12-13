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
		ui.postMsg("Ange efternamn:");
		skier.lastName=scan.nextLine();
		ui.postMsg("Ange kön (Dam eller Herr):");
		skier.gender=scan.nextLine();
		ui.postMsg("Ange ålder:");
		skier.age=scan.nextInt();
		scan.close();
		return skier;
	}
}
