package ui.elements;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.ComboPopup;

import ui.interfaces.UsrInp;

public class Dropdown extends JPanel implements UsrInp {
    private static final long serialVersionUID = 8944226329210546780L;

    private DdItem[] dropDowns;

    public Dropdown(String[][] usrChoices, String title, String seperator, int x, int y) {
        super(new GridBagLayout());
        JPanel titleP = new JPanel(new BorderLayout());
        JPanel spacer = new JPanel();
        JPanel valueP = new JPanel();

        setPreferredSize(new Dimension(600, 20));

        titleP.add(new JLabel(title), BorderLayout.WEST);

        dropDowns = new DdItem[usrChoices.length];

        dropDowns[0] = new DdItem(usrChoices[0]);
        dropDowns[0].setBackground(new Color(0,0,0,0));
        valueP.add(dropDowns[0]);
        for (int i = 1; i < usrChoices.length; i++) {
            valueP.add(new JLabel(seperator));
            dropDowns[i] = new DdItem(usrChoices[i]);
            valueP.add(dropDowns[i]);
        }

        GridBagConstraints c = new GridBagConstraints();

        titleP.setBorder(new EmptyBorder(0, 0, 0, 0));
        valueP.setBorder(new EmptyBorder(0, 0, 0, 0));

        c.fill = GridBagConstraints.VERTICAL;

        c.anchor = GridBagConstraints.PAGE_START;
        add(titleP, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        add(spacer, c);

        c.anchor = GridBagConstraints.PAGE_END;
        c.weightx = 0.0;

        add(valueP, c);
    }

    @Override
    public String[] getUsrInp() {
        String[] ret = new String[dropDowns.length];
        for (int i = 0; i < dropDowns.length; i++) {
            JComboBox<String> dropDown = dropDowns[i];
            ret[i] = (String) dropDown.getSelectedItem();
        }
        return ret;
    }

}

class DdItem extends JComboBox<String> {
	private static final long serialVersionUID = -6177390442466763631L;

	 public DdItem(String[] usrChoices) {
		super(usrChoices);
		setUI(new MyBasicComboBoxUI());
	}
}

class MyBasicComboBoxUI extends BasicComboBoxUI {
    final private Color cTrnspt = new Color(0,0,0,0);
    // final private Color cBackgd = new Color(238,238,238);
    final private Color cTintBg = new Color(220,220,220);
    final private Color cForegd = Color.BLACK;
    // final private Color cTintFg = Color.BLACK;

    protected ComboPopup createPopup() {
        ComboPopup popUp = new BasicComboPopup(comboBox) {
            private static final long serialVersionUID = -4866243803163618676L;

            @Override
            protected JScrollPane createScroller() {
                JScrollPane scroller =
                        new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
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
                    protected void configureScrollBarColors() {
                        this.thumbColor = new Color(200, 200, 200);
                        this.thumbHighlightColor = new Color(200, 200, 200);
                        this.thumbDarkShadowColor = new Color(0, 0, 0, 0);
                        this.thumbLightShadowColor = new Color(0, 0, 0, 0);

                        this.trackColor = new Color(220, 220, 220);
                    }
                };

                scroller.getVerticalScrollBar().setUI(scrlBarUI);

                scroller.setBackground(cTintBg);
                scroller.setForeground(cForegd);

                scroller.getVerticalScrollBar().setBackground(cForegd);
                scroller.getVerticalScrollBar().setForeground(cForegd);

                return scroller;
            }
        };

        return popUp;
    }

    @Override
    protected JButton createArrowButton() {
        JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, cTrnspt, cTrnspt, cForegd, cTrnspt);
        button.setBorder(BorderFactory.createEmptyBorder());
        return button;
    }
}

