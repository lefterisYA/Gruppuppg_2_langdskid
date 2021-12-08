package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import skiing.Skier;

public class GUI implements UI {
	private volatile boolean newValExists = false;
	JFrame frame = new JFrame("My First GUI");
	Panel panel = new Panel(new GridBagLayout(), new GridBagConstraints());
	ElemGnrt elemGnrt = new ElemGnrt(this);
	BttnHnlr bttnHnlr = new BttnHnlr(this);
	String bodyText = "";

	private String usrInp;

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(900,800)); // frame.setSize(600,300);

		gnrtElems();
//		addElms();

		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
	}
	
	public void setUserInp(String txt) {
		usrInp=txt;
		readUsrInp();
	}

	@Override
	public void showScreen(Screen newScreen) {
//		spacer.setText("F");
//		spacer.setMinimumSize(new Dimension(1,800));
//		spacer.setSize(800, 800);
		switch (newScreen){
		case INTRO:
			panel.removeAll();
			titleText("Välkommen");
			panel.add(elemGnrt.getLabel(), 1, 0, 1);
			panel.add(elemGnrt.getTextArea(), -1, 1, true);
			panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.SEL_1), 0, 1, true);
			panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.SEL_2), 1, 0, true);
			panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.SEL_3), 1, 0, true);
			panel.addVertSpcr(400);
			panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.ACCPT), -2, 1, true);
			panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.EXIT), 2, 0, true);

			bodyText("Var god gör ett val:");
			panel.addVertSpcr(1);
			panel.updateUI();
			break;

		case CREATE_RACE:
			panel.removeAll();
			titleText("Ny tävling:");

			panel.add(elemGnrt.getLabel(), 0, 0, 3);
			panel.add(elemGnrt.getTextArea(), 0, 1);
			panel.add(elemGnrt.getTextField(), 1, 1);
			panel.addVertSpcr(200);
			panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.ACCPT), 0, 3);
			panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.EXIT), 2, 3);
			
			panel.updateUI();
			break;
		case PRINT_STRTLIST:
			break;
		case RGSTR_SKIER:
			break;
		default:
			System.out.println("Screen not handled!");
		}
		
	}
	
	private void clearWin() {
		
	}

	private void addElms() {
		panel.add(elemGnrt.getLabel(), 0, 0, 2);
		panel.add(elemGnrt.getTextArea(), 0, 1, 2);
		panel.add(elemGnrt.getTextField(), 0, 2, 2);
		panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.ACCPT), 0, 1, true);
		panel.add(bttnHnlr.getButton(BttnHnlr.Buttons.EXIT), 1, 0, true);
	}
	
	private void gnrtElems() {
		elemGnrt.gnrtLabel("Hello");
		elemGnrt.gnrtTextArea();
		elemGnrt.gnrtTextField();

//		ActionListener actnLsnrExit = new ActionListener() {
//			public void actionPerformed(ActionEvent e) { System.exit(0); }
//		};
//		ActionListener actnLsnrNewVal = new ActionListener() {
//			public void actionPerformed(ActionEvent e) { readUsrInp(); }
//		};
		EventListener actnLsnrEnterKey = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					readUsrInp();
				}
			}

			@Override public void keyReleased(KeyEvent arg0) { }
			@Override public void keyTyped(KeyEvent arg0) { }
		};
//
//		elemGnrt.addLnsr(ElemGnrt.ActnLnrs.EXIT,  	actnLsnrExit);
//		elemGnrt.addLnsr(ElemGnrt.ActnLnrs.NEWVAL, 	actnLsnrNewVal);
//		elemGnrt.addLnsr(ElemGnrt.ActnLnrs.ENTR_KEY, 	actnLsnrEnterKey);

		elemGnrt.getTextField().addKeyListener((KeyListener) actnLsnrEnterKey);
		
		bttnHnlr.gnrtButton(BttnHnlr.Buttons.ACCPT, "OK");
		bttnHnlr.gnrtButton(BttnHnlr.Buttons.EXIT, "EXIT");

		bttnHnlr.gnrtButton(BttnHnlr.Buttons.SEL_1, "Skapa tävling");
		bttnHnlr.gnrtButton(BttnHnlr.Buttons.SEL_2, "Anmäl tävlande");
		bttnHnlr.gnrtButton(BttnHnlr.Buttons.SEL_3, "Skriv ut startlista");
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
	
	private String getWhenAvail(String msg) {
		postMsg(msg);
		
		while (!newValExists)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) { }
		String val=usrInp;
		newValExists=false;
		usrInp=null;
		return val;
	}
	
	private void readUsrInp() {
		usrInp=elemGnrt.getTextField().getText();	
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


//		int numOfSkiers = getUserInt("Hur många skidåkare?");

//		addSkierDialog(numOfSkiers);	
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
		
		return new Skier(name, playerNumber, speed, position, sTime);
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
		try {
			return Integer.parseInt( getWhenAvail(msg) );
		} catch ( Exception e ) {
			return getUserInt("Du måste ange tal i siffor!");
		}
	}

	@Override
	public String getUserStrng(String msg) {
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

	/*
	@Override
	public Screen getNextScreen() {
		switch ( getWhenAvail("") ) {
		case "1":
			return Screen.CREATE_RACE;
		case "2":
			return Screen.CREATE_RACE;
		case "3":
			return Screen.CREATE_RACE;
		default:
			return Screen.CREATE_RACE;
		}
	}
	 */
	
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

	void gnrtTextField() {
		textField = new JTextField("", 20);
		textField.setMinimumSize(new Dimension(400,20));
	}

	void gnrtTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 600, 200);
//		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 44));
		textArea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		//    	   textArea.setBackground(new Color(0,0,0,0));
		textArea.setOpaque(false);

	}
	
	void gnrtLabel(String txt) {
		label = new JLabel(txt);
		label.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 44));
	}
}

class BttnHnlr {
	enum Buttons {
			ACCPT,
			EXIT,
			BACK,
			SEL_1,
			SEL_2,
			SEL_3,
			SEL_4,
			SEL_5,
			SEL_6,
			SEL_7
	};

	GUI ui;
	HashMap<Buttons, JButton> buttons = new HashMap<Buttons, JButton>();

	public BttnHnlr(GUI ui) {
		this.ui = ui;
	}
	
	private void fireBttnPrst(Buttons bttn) {
		switch (bttn) {
		case EXIT:
			System.exit(0);
		case ACCPT:
			ui.runClock();
			break;
		case BACK:
			ui.runClock();
			break;
		case SEL_1:
			ui.setUserInp("1");
			break;
		case SEL_2:
			ui.setUserInp("2");
			break;
		case SEL_3:
			ui.setUserInp("3");
			break;
		default:
			ui.postMsg("BUtton not handled!!!!!!!!");
		}
	}

	public void gnrtButton(Buttons bttnKey, String bttnText) {
		JButton newBttn = new JButton(bttnText);
		newBttn.setBounds(0, 0, 50, 50);
		newBttn.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				fireBttnPrst(bttnKey);
			}
		});
		buttons.put(bttnKey, newBttn);
//		newBttn.addActionListener( (ActionListener) actnLnrs.get(actnLsnrKey) );
	}

	public JButton getButton(Buttons bttnKey) {
		return buttons.get(bttnKey);
	}
	
//	public void setButtonActnLsnr(Buttons bttnKey, ActnLnrs actnLsnrKey) {
//		buttons.get(bttnKey).removeActionListener(buttons.get(bttnKey).getActionListeners()[0] );
//		buttons.get(bttnKey).addActionListener((ActionListener) actnLnrs.get(actnLsnrKey));
//	}
//	
}
