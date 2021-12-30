package main;

import common.Utils;
import skiing.SkierList;
import ui.GUI;

public class Main {
	SkierList skierList = new SkierList();

	public static void main(String[] args) {
		Utils utils = Utils.getInstance(); // get singelton instance of class Utils
		ProgLogic gLogic = new ProgLogic();
		gLogic.run();
	}

}
