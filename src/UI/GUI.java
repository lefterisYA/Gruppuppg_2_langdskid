package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	JTextField textField = new JTextField("This is a text");
	JTextArea textArea = new JTextArea("This is also text");

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//    	   frame.setSize(600,300);
		frame.setMinimumSize(new Dimension(600,300));

		textArea.setEditable(false);
		textArea.setBounds(0, 0, 600, 200);

		textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 44));
		//    	   textArea.setBackground(new Color(0,0,0,0));
		textArea.setOpaque(false);
		btnClose.setBounds(0, 0, 50, 50);
		btnTest.setBounds(0, 0, 50, 50);

		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { System.exit(0); }
		});

		btnTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { bodyText("Hello world".repeat(10)+"!\nfoo bar"); }
		});

		panel.add(label1, 0, 0, 2);
		panel.add(textArea, 0, 1, 2);

		panel.add(btnTest, 0, 1, true);
		panel.add(btnClose, 1, 0, true);

		frame.getContentPane().add(panel);
		frame.pack(); // resize frame to panel
		frame.setVisible(true);
		
		runClock(this);
		//    	   frame.getContentPane().add(btnClose);
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postMsg(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getUserInt(String msg) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserStrng(String msg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getUserDouble(String msg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void runClock(GUI ui) {
		Clock clk = new Clock();

	    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
	    	@Override
	    	public void run() {
//	    		System.out.println( Clock.getCurrTimeInAscii() );
//	    		ui.bodyText( "\n" + clk.getCurrTimeInAscii() );
	    		ui.bodyText( "\n" + clk.getCurrTime() );
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
