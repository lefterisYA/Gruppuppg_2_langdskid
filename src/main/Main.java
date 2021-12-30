package main;

import skiing.SkierList;
import timekeeping.TimeUtils;
import ui.GUI;

public class Main {
	SkierList skierList = new SkierList();

	public static void main(String[] args) {
		TimeUtils utils = TimeUtils.getInstance(); // get singelton instance of class Utils
		ProgLogic gLogic = new ProgLogic(new GUI());
		gLogic.run();
	}

}
