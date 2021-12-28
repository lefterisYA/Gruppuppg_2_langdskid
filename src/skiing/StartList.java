package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import UI.UI;
import common.Utils;

public class StartList {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SkierList skierlist = skierConfig(scan);
		
		ClassList classlist = classConfig(skierlist, "H24");
		scan.close();
	}

	private static SkierList skierConfig(Scanner scan) {
		//anmälning individer
		SkierList skierlist = new SkierList();
//		Scanner scan = new Scanner(System.in);
		System.out.println("Hur många skidåkare vill du registrera?");
		int amountOfSkiers = scan.nextInt();
		for (int i = 0; i < amountOfSkiers; i++) {
			skierlist.addSkiertoList(SkierDeclaration.SkierDeclare());
		}
		return skierlist;
	}

	public static ClassList classConfig(SkierList skierlist, String chosenClass) {
		//anmälan klass
		System.out.println("Vilken klass gäller det? t.ex H21 eller D24");
//		String chosenClass = scan.nextLine();
		ClassList classlist = new ClassList();
		classlist.setSkiingClass(chosenClass);
		int x = 0;
		for (int i = 0; i < skierlist.getSkierLinkedListSize(); i++) {
			if (chosenClass.equals(skierlist.getSkiingClassAtIndex(i))) {
				while (x <= 0) {
					timeConfigInput(classlist);
					x++;
				}
				classlist.addToClassList(skierlist.getSkier(i));
				
				//lottning
				classlist.assignAllPlayerNumbersRandom();
				classlist.sortList();
				classlist.setPlayerNumber(classlist);
			}
		}
		return classlist;
	}

	private static void timeConfigInput(ClassList classlist) {
		Scanner scan1 = new Scanner(System.in);
		System.out.println("När ska den första starttiden vara? input timme: ");
//				firstStart[0] = scan1.nextInt();
		classlist.setFirstStart(0, 12);
		System.out.println("input minut: ");
		classlist.setFirstStart(1, 30);
//				firstStart[1] =scan1.nextInt();
		System.out.println("input sekund: ");
		classlist.setFirstStart(2, 0);
//				firstStart[2] = scan1.nextInt();
		System.out.println("Ange startinteravll i sekunder: ");
//				int startInterval = scan1.nextInt();
		classlist.setStartInterval(30);
		System.out.println("Ange första spelarens nummer, resterande spelares nummer kommer baseras på det"
				+ "här. Så om du väljer t.ex 101, kommer spelare två vara 102, tre 103, osv.");
//				int firstPlayerNumber = scan1.nextInt();
		classlist.setFirstPlayerNumber(101);
	}

}
