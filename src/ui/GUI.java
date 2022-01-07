package ui;

import ui.elements.*;
import ui.interfaces.*;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import timekeeping.Time;

import java.util.LinkedList;
import java.util.List;

public class GUI {
	private JFrame frame = new JFrame("");
	private Panel panel = new Panel(new GridBagLayout(), new GridBagConstraints());

	private final LinkedList<Screen> screenStack = new LinkedList<Screen>();
	private final LinkedList<Dropdown> dropDowns = new LinkedList<>();
	private final InputFieldHandler inpFldHandler;
	private JLabel title;
	private JTextArea body;
	private TextTable textTable;
	private ButtonTable buttonTable;

	private GuiCallback<Screen> newScrnCallback;
	
	private Font bodyFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
	private Font titleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 44);

	public GUI(GuiCallback<Screen> newScrnCallback) {
		this.newScrnCallback = newScrnCallback;

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(900,800));
		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
		
		title = new Label(titleFont);
		body = new TextArea(bodyFont);
		
		inpFldHandler = new InputFieldHandler(this);
	}
	
	// Handles remembering the screen navigation so we can go back to the previous Screen.
	public void addToScreenStack(Screen scrn) {
		if ( scrn.isVirt )
			return;
		if ( screenStack.contains(scrn) )
			screenStack.remove(scrn);
		screenStack.addFirst(scrn);
	}
	
	public Screen getLastScreen() {
		screenStack.pop();
		if ( screenStack.size() > 0 )
			return screenStack.getFirst();
		else
			return Screen.INTRO;
	}
	
	public void clrScrn() {
		panel.removeAll();
		setBodyText("");
		panel.add(title, 0, 0, 3);
		panel.add(body, 0, 1, 3);
		clrUsrInpField();
		dropDowns.clear();
	}

	public void clrUsrInpField() {
		inpFldHandler.clrInpFlds();
	}

	public void update() {
		panel.updateUI();
	}

	public void setTitle(String text) {
		title.setText(text);
	}

	public void setBodyText(String text) {
		body.setText(text);
	}

	public void remove(JComponent comp) {
		panel.remove(comp);
	}

	public Button makeButton(String label, GuiCallback<String> cBack) {
		return new Button( label, cBack );	
	}

	public <T> Button makeButton(String label, Screen nextScrn) {
		return new Button( label, newScrnCallback, nextScrn);
	}

	public <T> void addButton(Button newButton, ElmntPos pos) {
		panel.add(newButton, pos );
	}
	
	public void addButton(String label, Screen nextScrn, ElmntPos pos) {
		Button newButton = new Button( label, newScrnCallback, nextScrn);
		panel.add(newButton, pos );
	}

	public void addButton(String label, GuiCallback<String> cBack, ElmntPos pos) {
		Button newButton = new Button(label, cBack);
		panel.add(newButton, pos);
	}

	public Component getLastComp() {
		return panel.getComponent(panel.getComponentCount()-1);
	}

	public void addVertSpcr(int hght) {
		panel.addVertSpcr(hght);
	}

	public void addVertSpcr(int hght, int x, int y, boolean absPos) {
		panel.addVertSpcr(hght, x, y, absPos);
	}

	public void addInpField ( String title, FieldValidator validityCback, ElmntPos pos ) {
		InputField inpFld = inpFldHandler.gnrt(title, validityCback);
		panel.add(inpFld, pos);
	}

	public void addDropdown(String title, String[][] usrChoices, ElmntPos pos, int sizeX, int sizeY) {
		dropDowns.add(new Dropdown( usrChoices, title, ":", sizeX, sizeY));
		panel.add(dropDowns.getLast(), pos);
	}

	public String[][] getUsrInp() {
		String[][] ret = new String[dropDowns.size()+1][];
		int i=0;
		for ( Dropdown dropDown : dropDowns ) {
			ret[i++] = dropDown.getUsrInp();
		}
		ret[i] = inpFldHandler.getInpFldVals(); 
		return ret;
	}
	
	public void addTable(String[] colTitles, int x, int y, boolean absPos ) {
		textTable = new TextTable(colTitles);
		panel.add(textTable, x, y, 3, absPos);
		update();
	}

	public TextTable getTextTable() {
		System.out.println("ffs");
		return textTable;
	}

	public void addButtonTable(int height, int x, int y, boolean absPos ) {
		buttonTable = new ButtonTable(height);
		panel.add( buttonTable, x, y, absPos );
	}

	public ButtonTable getButtonTable() {
		return buttonTable;
	}

	
	// ********************************************
	// TODO: flytte ut.
	private Time timeKeeper;
	public void runClock() {
		timeKeeper = new Time();
	    ActionListener taskPerformer = new ActionListener() {
	    	public void actionPerformed(ActionEvent evt) {
	    		timeKeeper.setToNow();
	    		setTitle( "\n" + timeKeeper.toString(true) );
	    	}
	    };
	    new Timer(0, taskPerformer).start();
	}

	// ********************************************
	// TODO: RADERA:
	public void postMsg(String msg) { System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"); }

	// ***************************************

}