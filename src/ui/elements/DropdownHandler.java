package ui.elements;

import java.util.LinkedList;

import ui.GUI;

class DropdownHandler2 {
	private final LinkedList<Dropdown> dropdowns;
	private final GUI ui;
	
	public DropdownHandler2(GUI ui) {
		dropdowns = new LinkedList<>();
		this.ui = ui;
	}
//	public Dropdown gnrt(String label, String[] options, int sizeX, int sizeY) {
////		Dropdown newDropdown = new Dropdown(options, label, sizeX, sizeY);
//		dropdowns.add(newDropdown);
//		
//		return newDropdown;
//	}
	public Dropdown gnrttriple(String label, String[][] usrChoices, int sizeX, int sizeY) {
//		Dropdown newDropdown = new Dropdown(options1, options2, label, ":", sizeX, sizeY);
		Dropdown newDropdown = new Dropdown( usrChoices, label, ":", sizeX, sizeY);
		dropdowns.add(newDropdown);
		
		return newDropdown;
	}
//	public LinkedList<String> getDropdownVal(){
//		LinkedList<String> retVal = new LinkedList<String>();
//		for (Dropdown dropdown : dropdowns) {
//			retVal.add(dropdown.getChoice());
//		}
//		return retVal;
//	}
//	public String[] getDropdownVals() {
//		String[] ret = new String[dropdowns.size()];
//		int i=0;
//		for(Dropdown dropdown : dropdowns) {
//			ret[i++] = dropdown.getChoice();
//		}
//		return ret;
//	}
//	public String[] getDropdownValsTime() {
//		String[] ret = new String[dropdowns.size()];
//		int i=0;
//		for(Dropdown dropdown : dropdowns) {
//			ret[i++] = dropdown.getTimeChoice();
//		}
//		return ret;
//	}
}