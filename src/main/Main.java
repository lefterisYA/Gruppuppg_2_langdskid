package main;

import common.Utils;
import skiing.SkierHandler;
import ui.GUI;

public class Main {
	SkierHandler skierList = new SkierHandler();

	public static void main(String[] args) {
		Utils utils = Utils.getInstance(); // get singelton instance of class Utils
		ProgLogic gLogic = new ProgLogic(new GUI());
		gLogic.run();
	}

}
