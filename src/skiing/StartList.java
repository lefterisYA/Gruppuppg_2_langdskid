package skiing;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import common.Utils;
import ui.UI;

public class StartList {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SkierHandler skierlist = skierConfig(scan);
		
//		ClassList classlist = classConfig(skierlist, "H24");
		scan.close();
	}

	private static SkierHandler skierConfig(Scanner scan) {
		//anmälning individer
		SkierHandler skierlist = new SkierHandler();
//		Scanner scan = new Scanner(System.in);
		System.out.println("Hur många skidåkare vill du registrera?");
		int amountOfSkiers = scan.nextInt();
		for (int i = 0; i < amountOfSkiers; i++) {
			skierlist.addSkiertoList(SkierDeclaration.SkierDeclare());
		}
		return skierlist;
	}

//	public static ClassList classConfig(SkierList skierlist, String chosenClass) {
//		//anmälan klass
//		System.out.println("Vilken klass gäller det? t.ex H21 eller D24");
////		String chosenClass = scan.nextLine();
//		ClassList classlist = new ClassList();
//		classlist.setSkiingClass(chosenClass);
//		int x = 0;
//		for (int i = 0; i < skierlist.getSkierLinkedListSize(); i++) {
//			if (chosenClass.equals(skierlist.getSkiingClassAtIndex(i))) {
//				while (x <= 0) {
////					classlist.timeConfigInput(classlist);
//					x++;
//				}
//				classlist.addToClassList(skierlist.getSkier(i));
//				
//				//lottning
//				classlist.assignAllPlayerNumbersRandom();
//				classlist.sortList();
//				classlist.setPlayerNumber(classlist);
//			}
//		}
//		return classlist;
//	}
}
