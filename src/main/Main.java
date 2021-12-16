package main;

import UI.GUI;
import UI.Screen;
import UI.UI;
import common.Utils;
import skiing.SkierList;

public class Main {
	SkierList skierList = new SkierList();

	public static void main(String[] args) {
		Utils utils = Utils.getInstance(); // get singelton instance of class Utils
		ProgLogic gLogic = new ProgLogic(new GUI());
		gLogic.run();
	}

}
