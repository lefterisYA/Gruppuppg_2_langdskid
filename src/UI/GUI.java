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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import skiing.Skier;

public class GUI implements UI {
	JFrame frame = new JFrame("My First GUI");
	Panel panel = new Panel(new GridBagLayout(), new GridBagConstraints());

	JLabel label1 = new JLabel("Test");
	JButton btnTest = new JButton("Press");
	JButton btnClose = new JButton("CLOSE");
	JTextField textField = new JTextField("", 5);
	JTextArea textArea = new JTextArea("This is also text");

	String bodyText = "";

//	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private String usrInp;

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//    	   frame.setSize(600,300);
		frame.setMinimumSize(new Dimension(900,800));

		textArea.setEditable(false);
		textArea.setBounds(0, 0, 600, 200);

//		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 44));
		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		//    	   textArea.setBackground(new Color(0,0,0,0));
		textArea.setOpaque(false);
		btnClose.setBounds(0, 0, 50, 50);
		btnTest.setBounds(0, 0, 50, 50);

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { System.exit(0); }
		});

		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { runClock(); }
		});

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { runClock(); }
		});

		textField.addKeyListener((KeyListener) new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){
					usrInp=textField.getText();
				}
			}

			@Override public void keyReleased(KeyEvent arg0) { }
			@Override public void keyTyped(KeyEvent arg0) { }
		});

		panel.add(label1, 0, 0, 2);
		panel.add(textArea, 0, 1, 2);

		panel.add(textField, 0, 2, 2);
		 
		panel.add(btnTest, 0, 1, true);
		panel.add(btnClose, 1, 0, true);

		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
		
        //		runClock(this);
		//    	   frame.getContentPane().add(btnClose);
	}
	
	public void showWelcomeScreen() {
		
	}

	public void titleText(String text) {
		label1.setText(text);
	}

	public void changeTextRz(String text) {
		textArea.setText(text);
		frame.pack();
	}

	public void bodyText(String text) {
		textArea.setText(text);
		//    	   textArea.setBackground(new Color(0,0,0,0));
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
		return Integer.parseInt( getWhenAvail(msg) );
	}

	@Override
	public String getUserStrng(String msg) {
		return getWhenAvail(msg);
	}

	@Override
	public Double getUserDouble(String msg) {
		return Double.parseDouble( getWhenAvail(msg) );
	}
	
	private String getWhenAvail(String msg) {
		postMsg(msg);
		runClock();
		btnTest.removeActionListener(btnTest.getActionListeners()[0] );
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { usrInp=textField.getText(); }
		});
		
		while (usrInp==null)
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) { }
		String val=usrInp;
		usrInp=null;
		return val;
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
