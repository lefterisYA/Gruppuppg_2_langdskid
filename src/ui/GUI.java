package ui;

import ui.elements.Button;
import ui.elements.ButtonTable;
import ui.elements.InputField;
import ui.elements.InputFieldHandler;
import ui.elements.Panel;
import ui.elements.TextTable;
import ui.interfaces.FieldValidator;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;

import java.util.EventListener;
import java.util.LinkedList;
import java.util.Vector;
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
	
	private Font bodyFont = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
	private Font titleFont = new Font(Font.SANS_SERIF, Font.PLAIN, 44);

	public GUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setMinimumSize(new Dimension(900,800)); // frame.setSize(600,300);
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
		panel.add(title, 0, 0, 3);
		panel.add(body, 0, 1, 3);
		clrUsrInpField();
	}
	public void clrBody() {
		bodyText = "";
		setBodyText(bodyText);
	}
	public void clrUsrInpField() {
		inpFldHandler.clrInpFlds();
//		elemGnrt.getTextField().setText("");
	}

	public void update() {
		panel.updateUI();
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

	
	
	
	
	
	
	
	// ********************************************
//	public void addInpField( String title, InputField.Type type, int x, int y, boolean absPos) {
//		panel.add(inpFldHandler.gnrt(title, type), x, y, absPos);
//	}

	public void addInpField ( String title, FieldValidator validityCback, ElmntPos pos ) {
		panel.add(inpFldHandler.gnrt(title, validityCback), pos);

//		Panel iPanel = new Panel(new GridLayout(1,2));
//		iPanel.add(new JLabel(title));
//		iPanel.add(inpFldHandler.gnrt("title", type, emptyAllowed, validityCback));
//		panel.add(iPanel, x, y, absPos);

	}

	public String[] getInpFieldVals() {
		return inpFldHandler.getInpFldVals(); 
	}
	// ********************************************

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ********************************************
	// TODO: flytte ut.
	private Clock clk;
	public void runClock() {
		clk = new Clock();

	    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
	    exec.scheduleAtFixedRate(new Runnable() {
	    	@Override
	    	public void run() {
	    		setTitle( "\n" + clk.getCurrTime() );
	    	}
	    }, 0, 20, TimeUnit.MILLISECONDS);
	}
	public String getCurrTime() {
		return clk.getCurrTime();
	}
	public int[] getCurrTimeInts() {
		return clk.getCurrTimeInts();
	}

	


	// ********************************************
	// TODO: RADERA:
	public void postMsg(String msg) {
		System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
	}

	// ***************************************

	TextTable textTable;
	public void addTable(String[] colTitles, int x, int y, boolean absPos ) {
		textTable = new TextTable(colTitles);
		panel.add(textTable, x, y, 3, absPos);
		update();
	}
	public TextTable getTextTable() {
		System.out.println("ffs");
		return textTable;
	}

	ButtonTable buttonTable;
	public void addButtonTable(int height, int x, int y, boolean absPos ) {
		buttonTable = new ButtonTable(height);
		panel.add( buttonTable, x, y, absPos );
	}
	public ButtonTable getButtonTable() {
		return buttonTable;
	}

}
class Label extends JLabel {
	private static final long serialVersionUID = 7737583840566915828L;
	public Label(String text, Font font) {
		super(text);
		setFont(font);	
	}
	public Label(Font font) {
		setFont(font);	
	}
}

class TextArea extends JTextArea {
	private static final long serialVersionUID = -3995422772846981975L;

	public TextArea(Font font) {
		setEditable(false);
		setBounds(0, 0, 600, 200);
		setFont(font);
		setOpaque(false);
	}
}