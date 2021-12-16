package UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//import java.awt.Color;
//import java.awt.Label;
//import java.awt.TextArea;

import javax.swing.*;

import main.ProgLogic;
import skiing.Skier;

public class GUI implements UI {
	private volatile boolean newValExists = false;
	private volatile boolean intrptBlock = false;
	JFrame frame = new JFrame("My First GUI");
	Panel panel = new Panel(new GridBagLayout(), new GridBagConstraints());
	ElemGnrt elemGnrt = new ElemGnrt(this);
	volatile String bodyText = "";

	private String usrInp;
	private Screen currScreen = null;
	private LinkedList<Screen> screenStack = new LinkedList<Screen>();

	private static ProgLogic caller;

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(900,800)); // frame.setSize(600,300);
		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
	}
	
	public void registerCallingThread(ProgLogic caller) {
		GUI.caller = 	caller;
	}

	void setUserInp(String txt) {
		usrInp = txt;
		readUsrInp();
	}

	@Override
	public void showScreen(Screen newScreen) {
		currScreen = newScreen;
		addToScreenStack(currScreen);
		showScreen();
	}
	
	private void addToScreenStack(Screen scrn) {
		if ( screenStack.contains(scrn) )
			screenStack.remove(scrn);
		screenStack.addFirst(scrn);
	}
	
	private Screen getLastScreen() {
		screenStack.pop();
		if ( screenStack.size() > 0 )
			return screenStack.getFirst();
		else
			return Screen.INTRO;
	}

	private void showScreen() {
		String sname = currScreen != null ? currScreen.name() : "null";
		System.out.println("GUI: "+sname+Thread.currentThread().getId());
                        
		switch (currScreen){
		case INTRO:
			panel.removeAll();
			titleText("Välkommen");
			panel.add(elemGnrt.getLabel(), 1, 0, 1);
			panel.add(elemGnrt.getTextArea(), -1, 1, true);
			panel.add(new Button(this, "Skapa tävling", 		Screen.CREATE_RACE, 	false), 0, 1, true);
			panel.add(new Button(this, "Lägg till tävlande",	Screen.RGSTR_SKIER,  	true), 1, 0, true);
			panel.add(new Button(this, "WIP", 					Screen.PRINT_STRTLIST, 	true), 1, 0, true);
			panel.addVertSpcr(400);
			panel.add(new Button(this, "OK",  			 		Screen.ACPT, 			false), -2, 1, true);
			panel.add(new Button(this, "Avsluta",     			Screen.EXIT, 			false), 2, 0, true);

			bodyText("Var god gör ett val:");
			panel.addVertSpcr(1);
			panel.updateUI();
			break;

		case RGSTR_SKIER:
			String title = "Registrera tävlande";

		case CREATE_RACE:
			title = "Ny tävling";

			panel.removeAll();
//			String title = currScreen == Screen.RGSTR_SKIER ? "Registrera tävlande" : "Ny tävling";
			titleText(title);
		
			bodyText("");
			panel.add(elemGnrt.getLabel(), 0, 0, 3);
			panel.add(elemGnrt.getTextArea(), 0, 1);
			panel.add(elemGnrt.getTextField(), 1, 1);
			panel.addVertSpcr(200);
			panel.add(new Button(this, "Tillbaka",		 	Screen.BACK, false), 0, 3);
			panel.add(new Button(this, "Avsluta",     		Screen.EXIT, false), 2, 3);
			bodyText("");

			panel.updateUI();
			break;

		case PRINT_STRTLIST:
			break;

		case BACK:
//			Thread.currentThread().interrupt();
//			System.out.println("here in GUI/BACK" + caller.getId());
			caller.interrupt();
//			caller.stop();
//			showScreen(getLastScreen());
			break;

		case EXIT:
			System.exit(0);

		default:
			System.out.println("Screen not handled!");
		}
	}

	public void titleText(String text) {
		elemGnrt.getLabel().setText(text);
	}

	public void changeTextRz(String text) {
		elemGnrt.getTextArea().setText(text);
		frame.pack();
	}

	public void bodyText(String text) {
		elemGnrt.getTextArea().setText(text);
		//    	   textArea.setBackground(new Color(0,0,0,0));
	}

	void readUsrInp() {
		usrInp = elemGnrt.getTextField().getText();
		elemGnrt.getTextField().setText("");
		newValExists=true;
	}

	public void runClock() {
		Clock clk = new Clock();

	    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
	    	@Override
	    	public void run() {
//	    		System.out.println( Clock.getCurrTimeInAscii() );
//	    		ui.bodyText( "\n" + clk.getCurrTimeInAscii() );
	    		titleText( "\n" + clk.getCurrTime() );
	    	}
	    }, 0, 20, TimeUnit.MILLISECONDS);
	}

	@Override
	public void showIntroScreen() {
		panel.removeAll();
	}

	@Override
	public Skier addSkierDialog(int playerNumber) {
		playerNumber++;

		String name = getUserStrng("Vad heter spelare nummer " + playerNumber);

		Double speed = getUserDouble("Hur snabb är " + name + "? (Svara i m/s) ");

		Double position = getUserDouble("Vart börjar " + name + "? (Svara i meter på banan) ");

		int[] sTime = new int[3];
		for (int j = 0; j < sTime.length; j++) {
			sTime[j] = getUserInt("När börjar " + name
				+ "? (Starting time, svara först timme, tryck enter och skriv minut, sen sekund) ");
		}

		return new Skier();
	}

	@Override
	public void postMsg(String msg) {
		bodyText += "\n" + msg;
		bodyText(bodyText);
	}

	@Override
	public void clearMsgScreen() {
		bodyText = "";
		bodyText(bodyText);
	}

	@Override
	public int getUserInt(String msg) {
//		if ( intrptBlock )
//			return -1;
		try {
			return Integer.parseInt( getWhenAvail(msg) );
		} catch ( Exception e ) {
			return getUserInt("Du måste ange tal i siffor!");
		}
	}

	@Override
	public String getUserStrng(String msg) {
//		panel.add(elemGnrt.getTextArea(), 0, 0, false);
//		panel.add(elemGnrt.getTextField(), 0, 1, true);
		return getWhenAvail(msg);
	}

	@Override
	public Double getUserDouble(String msg) {
		try {
			return Double.parseDouble( getWhenAvail(msg) );
		} catch ( Exception e ) {
			return getUserDouble("Du måste ange tal i siffor!");
		}
	}

	@Override
	public Screen getNextScreen() {
		System.out.println("getNextScreen()");
		blockUntilValAvail();
//		return (Screen) usrInput.getValue();
		return currScreen;
	}

	public void setNextScreen(Screen nxtScn) {
			currScreen = nxtScn;
		System.out.println("setNextScreen()");
			caller.interrupt();
	}

	public void setNextScreen(Screen nxtScn, boolean async) {
		if ( async ) {
			currScreen = nxtScn;
			newValExists=true;
		} else {
			currScreen = nxtScn;
			newValExists=true;
		}
	}
	
	private boolean blockUntilValAvail() {
		while (!newValExists || intrptBlock)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) { }

		newValExists=false;

		return true;
//		if (intrptBlock) {
//			System.out.println("blockUntilValAvail: interrupted");
//			return false;
//		} else {
//			System.out.println("xit");
//			return true;
//		}
	}

	private String getWhenAvail(String msg) {
		postMsg(msg);
		if ( blockUntilValAvail() ) {
			String val = usrInp;
			return val;
		} else {
			System.out.println("interrupted");
			return null;
		}
	}
	
	public void setIntrptBlock(boolean val) {
		intrptBlock = val;
	}

	@Override
	public void registerCallingThread(Thread thread) {
		// TODO Auto-generated method stub
		
	}
}

/*
 * Addition to the JButton cllass so that action listeners are added at creatinong o fobjects.
 */
class Button extends JButton {
	private static final long serialVersionUID = 1849303325697245859L;
	GUI ui;
	
	public Button(GUI ui, String label, Screen actn, boolean sync) {
		super(label);
		this.ui 	= ui;
		this.setBounds(0, 0, 50, 50);

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ( sync ) {
					ui.setNextScreen(actn, false);
					System.out.println("Button.addActionListener. Button pressed. Scrn: " + actn);
				} else {
					Thread thread = new Thread(){
						public void run(){
							System.out.println("Button.addActionListener: Thread Running");
							ui.showScreen(actn);
						}
					};
					thread.start();
				}
				Thread.currentThread().interrupt();
			}
		});
	}
}

/*
 * An extension of JPanel so that we can add JElements relative to other elements without keeping track of the
 * GridBagConstraints object outside of this class.
 */
class Panel extends JPanel {
	private static final long serialVersionUID = -4040485876947807582L;
	public final GridBagConstraints cnst;

	public Panel(GridBagLayout layout, GridBagConstraints cnst) {
		super(layout);
		this.cnst = cnst;
//		cnst.weightx = 1;
//		cnst.weighty = 1;
		cnst.insets = new Insets(10, 10, 10, 10);
		cnst.anchor = GridBagConstraints.SOUTH;
	}

	private void setCnst(int x, int y, int width) {
		cnst.gridx = x;
		cnst.gridy = y;
		cnst.gridwidth = width;
	}

	public void add(JComponent element, int x, int y, int width, boolean relative) {
		int newX = relative ? cnst.gridx+x : x;
		int newY = relative ? cnst.gridy+y : y;

		setCnst(newX, newY, width);
		add(element, cnst);
	}

	public void add(JComponent element, int x, int y, boolean relative) {
		add(element, x, y, 1, relative);
	}

	public void add(JComponent element, int x, int y, int width) {
		add(element, x, y, width, false);
	}

	public void add(JComponent element, int x, int y) {
		add(element, x, y, 1, false);
	}

	public void addVertSpcr(int height) {
		JPanel spacer = new JPanel();
		spacer.setBorder(BorderFactory.createEmptyBorder(height, 1, 1, 1));
//		spacer.setBackground(new Color(0,0,0));
		cnst.gridy++;
		add(spacer, cnst);
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
	JTextField textField;
	JTextArea textArea;

	JButton btnAcpt;
	JButton btnClse;
	GUI ui;

	HashMap<ActnLnrs, EventListener> actnLnrs = new HashMap<ActnLnrs, EventListener>();

	public ElemGnrt(GUI ui) {
		this.ui=ui;
		gnrtTextArea();
		gnrtTextField();
		gnrtLabel("");
	}

	JTextField getTextField() {
		return textField;
	}

	JTextArea getTextArea() {
		return textArea;
	}

	JLabel getLabel() {
		return label;
	}

	EventListener getActionListener(ActnLnrs key) {
		return actnLnrs.get(key);
	}

	void addLnsr(ActnLnrs key, EventListener actnLsnr) {
		actnLnrs.put(key, actnLsnr);
	}

	private void gnrtTextField() {
		textField = new JTextField("", 20);
		textField.setMinimumSize(new Dimension(400,20));

		EventListener actnLsnrEnterKey = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					ui.readUsrInp();
				}
			}

			@Override public void keyReleased(KeyEvent arg0) { }
			@Override public void keyTyped(KeyEvent arg0) { }
		};

		textField.addKeyListener((KeyListener) actnLsnrEnterKey);
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
}

