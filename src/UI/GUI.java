package UI;

import skiing.Skier;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.util.EventListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GUI {
	JFrame frame = new JFrame("My First GUI");
	Panel panel = new Panel(new GridBagLayout(), new GridBagConstraints());
	ElemGnrt elemGnrt = new ElemGnrt(this);
	volatile String bodyText = "";

	public Screen currScreen = null;
	public Screen nextScreen = null;
	private LinkedList<Screen> screenStack = new LinkedList<Screen>();

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(900,800)); // frame.setSize(600,300);
		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
	}
	
	private void addToScreenStack(Screen scrn) {
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

	GuiCallback guiCallback;
	public void rgsrCallback( GuiCallback guiCallback ) {
		this.guiCallback = guiCallback;
	}
	
	GuiCallback clockStart = new GuiCallback() {
		public void onSuccess() {
			runClock();
		}
		public void onCancel() {
		}
		public void onNewScrn(Screen newScrn) {
		}
		public void onNewUsrInp(String val) {
		}
	};

	
	public void showScreen(Screen newScreen) {
		currScreen = newScreen;
		addToScreenStack(currScreen);
		String title;
	
		String sname = currScreen != null ? currScreen.name() : "null";
		System.out.println( Thread.currentThread().getStackTrace()[1] + sname+" threadId:"+Thread.currentThread().getId()); 
		switch (currScreen){
		case INTRO:
			panel.removeAll();
			clearMsgScreen();
			title="Välkommen";
			titleText(title);
			panel.add(elemGnrt.getLabel(), 1, 0, 1);
			panel.add(elemGnrt.getTextArea(), -1, 1, true);
			panel.add(new Button( "Skapa tävling", 			guiCallback, Screen.CREATE_RACE  	), 0, 1, true);
			panel.add(new Button( "Lägg till tävlande",		guiCallback, Screen.RGSTR_SKIER   	), 1, 0, true);
			panel.add(new Button( "Visa slutresultat",		guiCallback, Screen.PRINT_STRTLIST  ), 1, 0, true);
			panel.add(new Button( "Se tävling", 			guiCallback, Screen.PRINT_STRTLIST  ), -2, 1, true);
			panel.add(new Button( "Starta klocka",			clockStart 							), 1, 0, true);
			panel.add(new Button( "WIP",					guiCallback, Screen.PRINT_STRTLIST  ), 1, 0, true);
			panel.addVertSpcr(400);
			panel.add(new Button( "OK",						guiCallback, Screen.ACPT 			), -2, 1, true);
			panel.add(new Button( "Avsluta",				guiCallback, Screen.EXIT 			), 2, 0, true);

			bodyText("Var god gör ett val:");
			panel.addVertSpcr(1);
			panel.updateUI();
			break;

		case RGSTR_SKIER,CREATE_RACE:
			panel.removeAll();
			clearMsgScreen();
			elemGnrt.getTextField().setText("");

			title = currScreen == Screen.CREATE_RACE ? "Ny tävling" : "Registrera tävlande";
			titleText(title);
		
			bodyText("");
			panel.add(elemGnrt.getLabel(), 0, 0, 3);

			panel.add(elemGnrt.getTextArea(), 0, 1);
			panel.add(elemGnrt.getTextField(), 1, 0, true);
			panel.addVertSpcr(200);

			panel.add(new Button( "Avbryt",					guiCallback, Screen.BACK 			), 1, 0, true);
			bodyText("");

			panel.updateUI();
			break;

		case RGSTR_SKIER_VERIFY:
			title="Klart?";
			titleText(title);
			panel.add(new Button( "Lägg till",				guiCallback, Screen.BACK 			), 1, 0, true);
			panel.add(new Button( "Lägg till fler",			guiCallback, Screen.EXIT 			), -2, 0, true);
			panel.updateUI();
			break;

		case PRINT_STRTLIST:
			break;

		default:
			System.out.println("Screen not handled!");
		}
	}

	public void addInputField() {
		
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

	public Skier addSkierDialog(int playerNumber) {
		playerNumber++;

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

		return new Skier();
	}

	public void postMsg(String msg) {
		bodyText += "\n" + msg;
		bodyText(bodyText);
	}

	public void clearMsgScreen() {
		bodyText = "";
		bodyText(bodyText);
	}

	public String getUserStrng(String msg) {

		bodyText(elemGnrt.getTextArea().getText()+"\t"+elemGnrt.getTextField().getText()+"\n"+msg);
//		bodyText(msg);
//		postMsg(msg);
		elemGnrt.setTextFieldCback(guiCallback);
		elemGnrt.getTextField().setText("");
		
		return null;
	}
}

/*
 * Addition to the JButton cllass so that action listeners are added at creatinong o fobjects.
 */
class Button extends JButton {
	private static final long serialVersionUID = 1849303325697245859L;
	GUI ui;
	static Thread caller;
	
	public Button(String label, GuiCallback callback, Screen nextScrn) {
		super(label);
//		this.ui 	= ui;
		this.setBounds(0, 0, 50, 50);

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				callback.onNewScrn(nextScrn); 
				}
		});
	}

	public Button(String label, GuiCallback callback) {
		super(label);
//		this.ui 	= ui;
		this.setBounds(0, 0, 50, 50);

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				callback.onSuccess(); 
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
	private Component lastAdded;

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
		
		lastAdded = element;
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
	
	public boolean removeLast() {
		try {
			remove(lastAdded);
			return true;
		} catch (Exception e) {
			return false;
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

		for ( KeyListener kLsnter : textFields.get(idx).getKeyListeners() )
			textFields.get(idx).removeKeyListener(kLsnter);
		textFields.get(idx).addKeyListener((KeyListener) textFieldKeyListner);
	}
	
	//
	public JTextField addTextField() {
		gnrtTextField();
		return textFields.getLast();
	}

}

