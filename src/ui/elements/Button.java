package ui.elements;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import ui.GUI;
import ui.GuiCallback;
import ui.Screen;

/*
 * Addition to the JButton cllass so that action listeners are added at creatinong o fobjects.
 */
public class Button extends JButton {
	private static final long serialVersionUID = 1849303325697245859L;
	GUI ui;
	
	public Button(String label, GuiCallback callback, Screen nextScrn) {
		super(label);
		setCommonProps();

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { callback.onNewScrn(nextScrn); }
		});
	}

	public Button(String label, GuiCallback callback) {
		super(label);
		setCommonProps();

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { callback.onClick(label); }
		});
	}

	public Button(String label, GuiCallback callback, boolean returnLabel) {
		super(label);
		setCommonProps();

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { callback.onClick(label); }
		});
	}

	public Button(String label, GuiCallback callback, int cBackVal) {
		super(label);
		setCommonProps();

		addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				callback.onClick(cBackVal); 

				}
		});
	}
	
	private void setCommonProps() {
		this.setBounds(0, 0, 50, 50);
		this.setBackground(new Color(220,220,220));
		this.setBorder(new EmptyBorder(4, 20, 4, 20));
	}
}