package ui.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dropdown extends JPanel {
	private JComboBox<String> jcombo;
	private JComboBox<String> jcombo2;
	private JComboBox<String> jcombo3;

	private static final long serialVersionUID = 8944226329210546780L;

	public Dropdown(String[] options, String title, int x, int y) {
		super(new GridLayout(1,2));
		jcombo = new JComboBox<>(options);
		this.setPreferredSize(new Dimension(600, y));
//		this.setBackground(new Color(0,255,0));
		add(new JLabel(title));
		add(jcombo);
	}
	public Dropdown(String[] options, String[] options2, String title, String title2, int x, int y) {
		jcombo = new JComboBox<>(options);
		this.setPreferredSize(new Dimension(600, y));
//		this.setBackground(new Color(0,255,0));
		add(new JLabel(title));
		add(jcombo);
		jcombo2 = new JComboBox<>(options2);
		add(new JLabel(title2));
		add(jcombo2);
		jcombo3 = new JComboBox<>(options2);
		add(new JLabel(title2));
		add(jcombo3);
	}
	public String getChoice() {
		return jcombo.getItemAt(jcombo.getSelectedIndex());
	}
	public String getTimeChoice() {
		return jcombo.getItemAt(jcombo.getSelectedIndex())+":"+jcombo2.getItemAt(jcombo2.getSelectedIndex())+":"+jcombo3.getItemAt(jcombo3.getSelectedIndex());
	}
}
