package ui;

import skiing.Skier;
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
import java.awt.TextArea;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
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
	private ElemGnrt elemGnrt = new ElemGnrt(this);
	private String bodyText = "";
	private GuiCallback guiCallback;
	private LinkedList<Screen> screenStack = new LinkedList<Screen>();
	private InputFieldHandler inpFldHandler;

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(900,800)); // frame.setSize(600,300);
		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
		
		
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
	
	public void goBack() {
		
	}

	// Callback methods:
	public void rgsrCallback( GuiCallback guiCallback ) {
		this.guiCallback = guiCallback;
	}
	
	public void txtFldCbck() {
		
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
		panel.add(elemGnrt.getLabel(), 1, 0, 1);
		panel.add(elemGnrt.getTextArea(), -1, 1, true);
	}
	public void clrBody() {
		bodyText = "";
		setBodyText(bodyText);
	}
	public void clrUsrInpField() {
		inpFldHandler.clrInpFlds();
		elemGnrt.getTextField().setText("");
	}
	public void removeLast() {
		panel.removeLast();
	}

	public void setTitle(String text) {
		elemGnrt.getLabel().setText(text);
	}
	public void setBodyText(String text) {
		elemGnrt.getTextArea().setText(text); //    	   textArea.setBackground(new Color(0,0,0,0));
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

	public String getUserStrng(String msg) {
		setBodyText(elemGnrt.getTextArea().getText()+"\t"+elemGnrt.getTextField().getText()+"\n"+msg);
//		bodyText(msg);
//		postMsg(msg);
		elemGnrt.setTextFieldCback(guiCallback);
		elemGnrt.getTextField().setText("");

		
		return null;
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

/*
 * Each Window will likely have just:
 *
 * ┌──────────────────────────┐  ┌──────────────────────────┐   ┌──────────────────────────┐
 * │        PROG TITLE        │  │        PROG TITLE        │   │        PROG TITLE        │
 * │‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾│  │‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾│   │‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾│
 * │        MENU TITLE        │  │        MENU TITLE        │   │        MENU TITLE        │
 * │  ┌───────────────────┐   │  │                          │   │  ┌───────────────────┐   │
 * │  │                   │   │  │    [ BUTTON SEL 1 ]      │   │  │ QUESTION FR PROG  │   │
 * │  │     QUESTION      │   │  │    [ BUTTON SEL 2 ]      │   │  └───────────────────┘   │
 * │  │   FROM PROGRAM    │   │  │    [ BUTTON SEL 3 ]      │   │  ┌───────────────────┐   │
 * │  │                   │   │  │    [ BUTTON SEL 4 ]      │   │  │    USER INPUT     │   │
 * │  └───────────────────┘   │  │    [ ...          ]      │   │  └───────────────────┘   │
 * │                          │  │                          │   │                          │
 * │  [EXIT]          [OK]    │  │  [EXIT]          [BACK]  │   │  [BACK]          [OK]    │
 * │                          │  │                          │   │                          │
 * └──────────────────────────┘  └──────────────────────────┘   └──────────────────────────┘
 *
 * So we only need:
 *
 * 2 TextArea [ Menu title and info]
 * 1 TextField
 * 3 Buttons, Exit, ok, back +
 * 5 buttons each menu item.
 *
 * This class generates, keeps and updates all the JElement objects.
 */
class ElemGnrt {
	enum ActnLnrs {
			ENTR_KEY,
			EXIT,
			NEWVAL,
	};

	JLabel label;
	LinkedList<JTextField> textFields = new LinkedList<JTextField>();
	JTextArea board;
	JTextArea textArea;

	GUI ui;

	HashMap<ActnLnrs, EventListener> actnLnrs = new HashMap<ActnLnrs, EventListener>();

	public ElemGnrt(GUI ui) {
		this.ui=ui;
		gnrtTextArea();
		gnrtTextField();
		gnrtLabel("");
	}

	// Getters:
	JTextArea getTextArea() 		{ return textArea; }
	JLabel getLabel() 				{ return label; }
	JTextField getTextField() 		{ return textFields.getFirst(); }
	JTextField getTextField(int i) 	{ return textFields.get(i); }

	// Generators:
	private void gnrtTextField() {
		JTextField textField = new JTextField("", 20);
		textField.setMinimumSize(new Dimension(400,20));
		textFields.add(textField);
	}

	private void gnrtTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 600, 200);
//		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 44));
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		//    	   textArea.setBackground(new Color(0,0,0,0));
		textArea.setOpaque(false);

	}

	private void gnrtLabel(String txt) {
		label = new JLabel(txt);
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 44));
	}
	
	// Setters:
	public void setTextFieldCback(GuiCallback callback) {
		setTextFieldCback(callback, 0);
	}

	public void setTextFieldCback(GuiCallback callback, int idx) {
		if ( callback == null )
			return;

		EventListener textFieldKeyListner = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					callback.onNewUsrInp(textFields.get(idx).getText());
				} else if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
					callback.onCancel();
				}
			}

			@Override public void keyReleased(KeyEvent arg0) { }
			@Override public void keyTyped(KeyEvent arg0) { }
		};

		removeTextFieldCbacks(idx);
		textFields.get(idx).addKeyListener((KeyListener) textFieldKeyListner);
	}
	
	public void removeTextFieldCbacks(){ removeTextFieldCbacks(0); }
	public void removeTextFieldCbacks(int idx) {
		for ( KeyListener kLsnter : textFields.get(idx).getKeyListeners() )
			textFields.get(idx).removeKeyListener(kLsnter);
	}
	
	//
	public JTextField addTextField() {
		gnrtTextField();
		return textFields.getLast();
	}

}



//	public Skier addSkierDialog(int playerNumber) {
//		playerNumber++;

//		String name = getUserStrng("Vad heter spelare nummer " + playerNumber);

//		Double speed = getUserDouble("Hur snabb är " + name + "? (Svara i m/s) ");
//
//		Double position = getUserDouble("Vart börjar " + name + "? (Svara i meter på banan) ");
//
//		int[] sTime = new int[3];
//		for (int j = 0; j < sTime.length; j++) {
//			sTime[j] = getUserInt("När börjar " + name
//				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");
//		}

//		return new Skier();
//	}