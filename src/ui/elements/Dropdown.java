package ui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

import ui.interfaces.UsrInp;

public class Dropdown extends JPanel implements UsrInp {
	private DdItem[] dropDowns;

	private static final long serialVersionUID = 8944226329210546780L;

	public Dropdown(String[][] usrChoices, String title, String seperator, int x, int y) {
		super(new GridLayout(1,2));
		JPanel titleP = new JPanel(new BorderLayout());
		JPanel valueP = new JPanel();
		valueP.setLayout(new BoxLayout(valueP, BoxLayout.X_AXIS));

		dropDowns = new DdItem[usrChoices.length];
		for ( int i=0; i<usrChoices.length; i++) {
			dropDowns[i] = new DdItem(usrChoices[i]);
		}

		this.setPreferredSize(new Dimension(600, 20));
//		titleP.setBackground(new Color(0,255,0));
//		valueP.setBackground(new Color(255,0,0));

		titleP.add(new JLabel(title), BorderLayout.WEST);

		add(titleP);
		dropDowns[0] = new DdItem(usrChoices[0]);
//		dropDowns[0].setBackground(new Color(0,0,0,0));
		valueP.add(dropDowns[0]);
		for ( int i=1; i<usrChoices.length; i++) {
			valueP.add(new JLabel(seperator));
			dropDowns[i] = new DdItem(usrChoices[i]);
			valueP.add(dropDowns[i]);
		}
		add(valueP);
	}

	@Override
	public String[] getUsrInp() {
		String[] ret = new String[dropDowns.length];
		for ( int i=0; i<dropDowns.length; i++ ) {
			JComboBox<String> dropDown = dropDowns[i];
			ret[i]=(String) dropDown.getSelectedItem();
		}
		return ret;
	}

}

class MyBasicComboBoxUI extends BasicComboBoxUI {
	protected ComboPopup createPopup() {
		ComboPopup popUp = new BasicComboPopup(comboBox) {
			private static final long serialVersionUID = -4866243803163618676L;
			@Override
			protected JScrollPane createScroller() {
				JScrollPane scroller = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
						JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

				BasicScrollBarUI scrlBarUI = new BasicScrollBarUI() {
					@Override
					protected JButton createDecreaseButton(int orientation) {
						return createZeroButton();
					}

					@Override
					protected JButton createIncreaseButton(int orientation) {
						return createZeroButton();
					}

					@Override
					public Dimension getPreferredSize(JComponent c) {
						return new Dimension(5, super.getPreferredSize(c).height);
					}

					private JButton createZeroButton() {
						return new JButton() {
							private static final long serialVersionUID = 4646525082412787836L;

							@Override
							public Dimension getMinimumSize() {
								return new Dimension(new Dimension(0, 0));
							}

							@Override
							public Dimension getPreferredSize() {
								return new Dimension(new Dimension(0, 0));
							}

							@Override
							public Dimension getMaximumSize() {
								return new Dimension(new Dimension(0, 0));
							}
						};
					}

					@Override 
					protected void configureScrollBarColors(){
						this.thumbColor = Color.BLACK;
						this.thumbHighlightColor = Color.BLACK;
						this.thumbDarkShadowColor = new Color(0,0,0,0);
						this.thumbLightShadowColor = new Color(0,0,0,0);

						this.trackColor = new Color(220,220,220);
					}
				};



				scroller.getVerticalScrollBar().setUI(scrlBarUI);

				scroller.setBackground(new Color(220,220,220,0));
				scroller.setForeground(new Color(0,0,0));

				scroller.getVerticalScrollBar().setBackground(Color.BLACK);
				scroller.getVerticalScrollBar().setForeground(Color.BLACK);

				return scroller;
			}
		};


		return popUp;
	}

	@Override
	protected JButton createArrowButton() {
//						JButton button = new BasicArrowButton(
//		BasicArrowButton.SOUTH, new Color(220,220,220), new Color(220,220,220,0), Color.BLACK, new Color(220,220,220,0) );
		JButton button = new JButton();
		button.setVisible(false);

		return button;
	}

	public JButton getArrowButton() {
		
		return this.arrowButton;
	}
}


class DdItem extends JComboBox<String> {
	private static final long serialVersionUID = -6177390442466763631L;

	 public DdItem(String[] usrChoices) {
		super(usrChoices);
		setUI(new MyBasicComboBoxUI());
	}
}
