package UI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	JFrame frame = new JFrame("My First GUI");
	Panel panel = new Panel(new GridBagLayout(), new GridBagConstraints());
	JElemGenerator elemGnrt = new JElemGenerator(this);

	private volatile boolean newValExists = false;

	String bodyText = "";

//	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private String usrInp;

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//    	   frame.setSize(600,300);
		frame.setMinimumSize(new Dimension(900,800));

		addListeners();
		elemGnrt.gnrtLabel("Hello");
		elemGnrt.gnrtTextArea();
		elemGnrt.gnrtTextField();

		panel.add(elemGnrt.getLabel(), 0, 0, 2);
		panel.add(elemGnrt.getTextArea(), 0, 1, 2);

		panel.add(elemGnrt.getTextField(), 0, 2, 2);
		 
		panel.add(elemGnrt.getButton(JElemGenerator.Buttons.ACCPT), 0, 1, true);
		panel.add(elemGnrt.getButton(JElemGenerator.Buttons.EXIT), 1, 0, true);

		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
		
        //		runClock(this);
		//    	   frame.getContentPane().add(btnClose);
	}
	
	private void addListeners() {
		ActionListener actnLsnrExit = new ActionListener() {
			public void actionPerformed(ActionEvent e) { System.exit(0); }
		};
		ActionListener actnLsnrNewVal = new ActionListener() {
			public void actionPerformed(ActionEvent e) { readUsrInp(); }
		};

		EventListener actnLsnrEnterKey = new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					readUsrInp();
				}
			}

			@Override public void keyReleased(KeyEvent arg0) { }
			@Override public void keyTyped(KeyEvent arg0) { }
		};

		elemGnrt.addLnsr(JElemGenerator.ActnLnrs.EXIT,  	actnLsnrExit);
		elemGnrt.addLnsr(JElemGenerator.ActnLnrs.NEWVAL, 	actnLsnrNewVal);
		elemGnrt.addLnsr(JElemGenerator.ActnLnrs.ENTER, 	actnLsnrEnterKey);

		elemGnrt.gnrtButton(JElemGenerator.Buttons.ACCPT, JElemGenerator.ActnLnrs.NEWVAL, "OK");
		elemGnrt.gnrtButton(JElemGenerator.Buttons.EXIT, JElemGenerator.ActnLnrs.EXIT, "EXIT");

		elemGnrt.getButton(JElemGenerator.Buttons.ACCPT).setBounds(0, 0, 50, 50);
		elemGnrt.getButton(JElemGenerator.Buttons.EXIT).setBounds(0, 0, 50, 50);
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

	@Override
	public void showIntroScreen() {
		
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
	
	private String getWhenAvail(String msg) {
		postMsg(msg);
		runClock();
		
		while (!newValExists)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) { }
		String val=usrInp;
		newValExists=false;
		usrInp=null;
		return val;
	}
	
	void readUsrInp() {
		usrInp=elemGnrt.getTextField().getText();	
		elemGnrt.getTextField().setText("");
		newValExists=true;
	}

	private void runClock() {
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
}

class Panel extends JPanel {
	private static final long serialVersionUID = -4040485876947807582L;
	public final GridBagConstraints cnst;

	public Panel(GridBagLayout layout, GridBagConstraints cnst) {
		super(layout);
		this.cnst = cnst;
		cnst.weightx = 1;
		cnst.weighty = 1;
		cnst.insets = new Insets(10, 10, 10, 10);
		cnst.anchor = GridBagConstraints.NORTH;
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
}

class JElemGenerator {
	enum Buttons {
			ENTER,
			ACCPT,
			EXIT,
			NEWVAL,
			CLOCK
	};
	enum ActnLnrs {
			ENTER,
			EXIT,
			NEWVAL,
			CLOCK
	};

	JLabel label;
	JTextField textField;
	JTextArea textArea;

	JButton btnAcpt;
	JButton btnClse;
	GUI ui;
	
	HashMap<ActnLnrs, EventListener> actnLnrs = new HashMap<ActnLnrs, EventListener>();
	HashMap<Buttons, JButton> buttons = new HashMap<Buttons, JButton>();
	
	public JElemGenerator(GUI ui) {
		this.ui=ui;
	}

	JTextField getTextField() {
//		if ( textField == null )
//			gnrtTextField();
		return textField;
	}

	JTextArea getTextArea() {
//		if ( textArea == null )
//			gnrtTextArea();
		return textArea;
	}

	JLabel getLabel() {
		return label;
	}
	
	public JComponent getButton(Buttons bttnKey) {
		return buttons.get(bttnKey);
	}

	public void setButtonActnLsnr(Buttons bttnKey, ActnLnrs actnLsnrKey) {
		buttons.get(bttnKey).removeActionListener(buttons.get(bttnKey).getActionListeners()[0] );
		buttons.get(bttnKey).addActionListener((ActionListener) actnLnrs.get(actnLsnrKey));
	}

	EventListener getActionListener(ActnLnrs key) {
		return actnLnrs.get(key);
	}
	
	void addLnsr(ActnLnrs key, EventListener actnLsnr) {
//		if ( actnLnrs.containsKey(key) )
//			actnLnrs.remove(key);
		actnLnrs.put(key, actnLsnr);
	}
	
	void gnrtButton(Buttons bttnKey, ActnLnrs actnLsnrKey, String bttnText) {
		JButton newBttn = new JButton(bttnText);
		buttons.put(bttnKey, newBttn);
		buttons.get(bttnKey).addActionListener( (ActionListener) actnLnrs.get(actnLsnrKey) );
	}
	
	void gnrtTextField() {
		textField = new JTextField("", 5);
//		textField.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) { readUsrInp(); }
//		});

		textField.addKeyListener((KeyListener) new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					ui.readUsrInp();
				}
			}

			@Override public void keyReleased(KeyEvent arg0) { }
			@Override public void keyTyped(KeyEvent arg0) { }
		});

	}

	void gnrtTextArea() {
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 600, 200);

//		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 44));
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		//    	   textArea.setBackground(new Color(0,0,0,0));
		textArea.setOpaque(false);

	}
	
	void gnrtLabel(String txt) {
		label = new JLabel("Test");
	}
	
	void gnrtCloseButton() {
		
		
	}
}
