package UI;

import skiing.Skier;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	public void update() {
		panel.updateUI();
	}
	
	public void showScreen(Screen currScreen) {
		boolean block = true;
		if ( block ) {
			System.out.println("DAMN IT LEFTERIS");
			return;
		}
		
//		addToScreenStack(currScreen);
//		String title;
//	
//		String sname = currScreen != null ? currScreen.name() : "null";
//		System.out.println( Thread.currentThread().getStackTrace()[1] + sname+" threadId:"+Thread.currentThread().getId()); 
//		switch (currScreen){
//		case INTRO:
//			clrScrn();
//			title="Välkommen";
//			setTitle(title);
//			panel.add(elemGnrt.getLabel(), 1, 0, 1);
//			panel.add(elemGnrt.getTextArea(), -1, 1, true);
//			panel.add(new Button( "Skapa tävling", 			guiCallback, Screen.CREATE_RACE  	), 0, 1, true);
//			panel.add(new Button( "Lägg till tävlande",		guiCallback, Screen.RGSTR_SKIER   	), 1, 0, true);
//			panel.add(new Button( "Visa slutresultat",		guiCallback, Screen.PRINT_STRTLIST  ), 1, 0, true);
//			panel.add(new Button( "Se tävling", 			guiCallback, Screen.PRINT_STRTLIST  ), -2, 1, true);
//			panel.add(new Button( "Starta klocka",			clockStart 							), 1, 0, true);
//			panel.add(new Button( "WIP",					guiCallback, Screen.PRINT_STRTLIST  ), 1, 0, true);
//			panel.addVertSpcr(400);
//			panel.add(new Button( "OK",						guiCallback, Screen.ACPT 			), -2, 1, true);
//			panel.add(new Button( "Avsluta",				guiCallback, Screen.EXIT 			), 2, 0, true);
//
//			setBodyText("Var god gör ett val:");
//			panel.addVertSpcr(1);
//			panel.updateUI();
//			break;
//
//		case RGSTR_SKIER,CREATE_RACE:
//			panel.removeAll();
//			clrBody();
//
//		case RGSTR_SKIER_REPEAT: // RGSTR_SKIER,CREATE_RACE: fallthrough too:
////			panel.add(elemGnrt.getTextField(), 1, 0, true);
//
//			panel.removeLast();
//			elemGnrt.getTextField().setText("");
//
//			title = currScreen == Screen.CREATE_RACE ? "Ny tävling" : "Registrera tävlande";
//			setTitle(title);
//		
//			setBodyText("");
//			panel.add(elemGnrt.getLabel(), 0, 0, 3);
//
//			panel.add(elemGnrt.getTextArea(), 0, 1);
//			panel.add(elemGnrt.getTextField(), 1, 0, true);
//			panel.addVertSpcr(200);
//
//			panel.add(new Button( "Avbryt",					guiCallback, Screen.BACK 			), 1, 0, true);
//			setBodyText("");
//
//			panel.updateUI();
//			break;
//
//		case RGSTR_SKIER_VERIFY:
//			title="Klart?";
//			setTitle(title);
//			
//			elemGnrt.removeTextFieldCbacks();
//			panel.add(new Button( "Lägg till",				guiCallback, Screen.RGSTR_SKIER_FINISH	), 1, 0, true);
//			panel.add(new Button( "Lägg till fler",			guiCallback, Screen.RGSTR_SKIER_REPEAT	), -2, 0, true);
//			panel.updateUI();
//			break;
//			
//		case PRINT_STRTLIST:
//			break;
//
//		default:
//			System.out.println("Screen not handled!");
//		}
	}

	public void runClock() {
		Clock clk = new Clock();

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


//interface InputFieldVerifier {
//	boolean hasVldVal(String input);
//}
//	private static InputFieldVerifier intVerifier = new InputFieldVerifier() {
//		@Override public boolean hasVldVal(String input) {
//			try {
//				Integer.parseInt(input);
//				return true;
//			} catch (Exception e ) {
//				return false;
//			}
//
//		}
//	};
//
//	private static InputFieldVerifier hasValVerifier = new InputFieldVerifier() {
//		@Override public boolean hasVldVal(String input) {
//			return !input.isEmpty();
//		}
//	};
//	public InputField() { 
//		textField.setMinimumSize(new Dimension(400,20));
//		setPreferredSize(new Dimension(300, 20));
//		this = textField;
//		textField.setBackground(new Color(255,255,255));
//		textField.setSize(200, 30);
//	}
//	public InputField(GuiCallback callback) { this.setTextFieldCback(callback); }

	
//	private void setTextFieldCback(GuiCallback callback) {
//		for ( KeyListener kLsnter : this.getKeyListeners() )
//			this.removeKeyListener(kLsnter);
//
//		if ( callback == null )
//			return;
//
//		EventListener textFieldKeyListner = new KeyListener() {
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode()==KeyEvent.VK_ENTER){
////					callback.onNewUsrInp(textFields.get(idx).getText());
//				} else if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) {
//					callback.onCancel();
//				}
//			}
//
//			@Override public void keyReleased(KeyEvent arg0) { }
//			@Override public void keyTyped(KeyEvent arg0) { }
//		};
//		
//		this.addKeyListener((KeyListener) textFieldKeyListner);
//	}

class ButtonGnrt {
	GuiCallback cBack;
	public ButtonGnrt(GuiCallback cBack) {
		this.cBack = cBack;
	}

	public Button crt(String label, Screen nextScrn) {
		return new Button(label, cBack, nextScrn);
	}
}

/*
 * Addition to the JButton cllass so that action listeners are added at creatinong o fobjects.
 */
class Button extends JButton {
	private static final long serialVersionUID = 1849303325697245859L;
	GUI ui;
	
	public Button(String label, GuiCallback callback, Screen nextScrn) {
		super(label);
		this.setBounds(0, 0, 50, 50);

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { callback.onNewScrn(nextScrn); }
		});
	}

	public Button(String label, GuiCallback callback) {
		super(label);
		this.setBounds(0, 0, 50, 50);

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { callback.onClick(); }
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

//	private Component lastAdded;
//	LinkedList<JComponent> components = new LinkedList<JComponent>();

	public Panel(GridLayout layout) {
		super(layout);
		cnst=null;
	}

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
		if ( cnst != null )
			add(element, cnst);
		else
			add(element);
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
		remove(getComponents().length-1);
		return true;
//		try {
//			remove(lastAdded);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
	}
	
	public LinkedList<InputField> getTextFields() {
		LinkedList<InputField> retVal = new LinkedList<InputField>();
		for ( Component comp : getComponents() ) {
			if ( comp instanceof InputField ) {
				retVal.add((InputField) comp);
			} else if ( comp instanceof Panel ) {
				retVal.addAll( ( (Panel) comp ).getTextFields() );
			}
		}
		return retVal;
	}

	public LinkedList<String> getTextFieldVals() {
		LinkedList<String> retVal = new LinkedList<String>();
		for ( Component comp : getComponents() ) {
			if ( comp instanceof InputField ) {
				retVal.add(((InputField) comp).getText());
			} else if ( comp instanceof Panel ) {
				retVal.addAll( ( (Panel) comp ).getTextFieldVals() );
			}
		}
		return retVal;
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