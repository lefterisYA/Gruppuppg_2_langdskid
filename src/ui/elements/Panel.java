package ui.elements;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.ElmntPos;

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
	
	public void add(JComponent element, ElmntPos pos) {
		int newX = pos.xIsRelative ? cnst.gridx+pos.x : pos.x;
		int newY = pos.yIsRelative ? cnst.gridy+pos.y : pos.y;

		setCnst(newX, newY, pos.width);
		add(element, cnst);
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
		cnst.gridy++;
		add(spacer, cnst);
	}
	
	public boolean removeLast() {
		remove(getComponents().length-1);
		return true;
	}
	
}