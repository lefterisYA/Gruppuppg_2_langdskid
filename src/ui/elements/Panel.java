package ui.elements;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

/*
 * An extension of JPanel so that we can add JElements relative to other elements without keeping track of the
 * GridBagConstraints object outside of this class.
 */
public class Panel extends JPanel {
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

	public void addVertSpcr(int height, int x, int y, boolean relative) {
		JPanel spacer = new JPanel();
		spacer.setBorder(BorderFactory.createEmptyBorder(height, 1, 1, 1));
		cnst.gridy++;
		add(spacer, x, y, 1, relative);

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