package ui;

import ui.elements.Button;
import ui.elements.InputField;
import ui.elements.InputFieldHandler;
import ui.elements.Panel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GUI {
	private JFrame frame = new JFrame("My First GUI");
	private Panel panel = new Panel(new GridBagLayout(), new GridBagConstraints());
	private GuiCallback guiCallback;
	private LinkedList<Screen> screenStack = new LinkedList<Screen>();
	private InputFieldHandler inpFldHandler;

	private JLabel title;
	private JTextArea body;
	private String bodyText = "";

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(900,800)); // frame.setSize(600,300);
		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
		
		title = new Label(new Font(Font.SANS_SERIF, Font.PLAIN, 44));
		body = new TextArea(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		
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
	
	// Callback methods:
	public void rgsrCallback( GuiCallback guiCallback ) {
		this.guiCallback = guiCallback;
	}
	
	public final GuiCallback clockStart = new GuiCallback() {
		public void onClick() { runClock(); }
	};

	
	/*
	 * Screen-design methods:
	 */
	public void clrScrn() {
		panel.removeAll();
		clrBody();
		panel.add(title, 1, 0, 1);
		panel.add(body, -1, 1, true);
	}
	public void clrBody() {
		bodyText = "";
		setBodyText(bodyText);
	}
	public void clrUsrInpField() {
		inpFldHandler.clrInpFlds();
//		elemGnrt.getTextField().setText("");
	}
	public void removeLast() {
		panel.removeLast();
	}

	public void setTitle(String text) {
		title.setText(text);
	}
	public void setBodyText(String text) {
		body.setText(text); //    	   textArea.setBackground(new Color(0,0,0,0));
	}

	public void addButton(String label, Screen nextScrn, int x, int y, boolean absPos) {
		panel.add(new Button( label, guiCallback, nextScrn), x, y, absPos);
	}
	public void addButton(String label, GuiCallback cBack, int x, int y, boolean absPos) {
		panel.add(new Button( label, cBack), x, y, absPos);
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

	public void addInpField(
			String title, 
			InputField.Type type, 
			int x, 
			int y, 
			boolean absPos) 
	{
		Panel iPanel = new Panel(new GridLayout(1,2));
		iPanel.add(new JLabel(title));
		iPanel.add(inpFldHandler.gnrt("title", type));

		panel.add(iPanel, x, y, absPos);
	}
	public void addInpField(
			String title, 
			InputField.Type type, 
			boolean emptyAllowed, 
			GuiCallback validityCback, 
			int x, 
			int y, 
			boolean absPos) 
	{
		Panel iPanel = new Panel(new GridLayout(1,2));
		iPanel.add(new JLabel(title));
		iPanel.add(inpFldHandler.gnrt("title", type, emptyAllowed, validityCback));

		panel.add(iPanel, x, y, absPos);
	}

	public String[] getInpFieldVals() {
		return inpFldHandler.getInpFldVals(); 
	}
	
	HashMap<Integer, Button[]> skiersTable = new HashMap<Integer, Button[]>();
	public void addSeeRaceTableRow(
			String skierName, int skierNum, GuiCallback chckPntCback, GuiCallback fnshCback, int x, int y, boolean absPos
			) {
		Button checkpointButton = new Button("Checkpoint", chckPntCback, skierNum);
		Button finishlineButton = new Button("Slutlinje", fnshCback, skierNum);

		Panel iPanel = new Panel(new GridLayout(1,4));
		iPanel.add(new JLabel(skierName));
		iPanel.add(checkpointButton);
		iPanel.add(new JLabel(""));
		iPanel.add(finishlineButton);

		skiersTable.put(skierNum, new Button[] {checkpointButton, finishlineButton});

		panel.add( iPanel, x, y, absPos );
	}
	
	public enum Linetype { CHECKPOINT, FINISHLINE };
	public void updateSkierLinepass(int skierNum, GUI.Linetype linetype) {
		System.out.println("wtfmate" + clk.getCurrTime() + " " + skierNum );
		int bttnIndx =  linetype == Linetype.CHECKPOINT ? 0 : 1;
		skiersTable.get(skierNum)[bttnIndx].setEnabled(false);
		skiersTable.get(skierNum)[bttnIndx].setText(clk.getCurrTime());
	}
	
	public void update() {
		panel.updateUI();
	}
	
	Clock clk;
	public void runClock() {
		clk = new Clock();

	    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
	    	@Override
	    	public void run() {
//	    		System.out.println( Clock.getCurrTimeInAscii() );
//	    		ui.bodyText( "\n" + clk.getCurrTimeInAscii() );
	    		setTitle( "\n" + clk.getCurrTime() );
	    	}
	    }, 0, 20, TimeUnit.MILLISECONDS);
	}

	public void postMsg(String msg) {
		bodyText += "\n" + msg;
		setBodyText(bodyText);
	}

	public void focusInpFldAtRelativeIdx(int relIdx) {
		LinkedList<InputField> inpFlds = panel.getTextFields();
		for ( int i=0; i<inpFlds.size(); i++ ) {
			if ( ! inpFlds.get(i).isFocusOwner() )
				continue;
			if ( i + relIdx >= inpFlds.size() )
				inpFlds.getFirst().requestFocus();
			else if ( i + relIdx < 0 )
				inpFlds.getLast().requestFocus();
			else
				inpFlds.get(i+relIdx).requestFocus();
			return;
		}
	}
}

class Label extends JLabel {
	public Label(Font font) {
		setFont(font);	
	}
}

class TextArea extends JTextArea {
	public TextArea(Font font) {
		setEditable(false);
		setBounds(0, 0, 600, 200);
		setFont(font);
		setOpaque(false);
	}
}