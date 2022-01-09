package ui.elements;

import java.awt.GridLayout;


import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.GUI;
import ui.interfaces.GuiCallback;

public class ButtonTable extends JPanel {
	private static final long serialVersionUID = -3065613945162621665L;
    private GUI ui;

	public enum Linetype { CHECKPOINT, FINISHLINE };

	Map<Integer, Button[]> buttonTable = new HashMap<Integer, Button[]>();

	public ButtonTable(GUI ui, int height) {
		super(new GridLayout(height,4));
        this.ui = ui;
	}

	public void addRow(String skierName, int skierNum, GuiCallback<Integer> chckPntCback, GuiCallback<Integer> fnshCback) {
		Button checkpointButton = new <Integer> Button(ui, "Checkpoint", chckPntCback, skierNum);
		Button finishlineButton = new <Integer> Button(ui, "Slutlinje", fnshCback, skierNum);


		JPanel iPanel = new JPanel(new GridLayout(1,4));
		iPanel.add(new JLabel(skierName));
		iPanel.add(checkpointButton);
		iPanel.add(new JLabel(""));
		iPanel.add(finishlineButton);
		iPanel.setBorder(new EmptyBorder(5, 0, 5, 0));


		add(iPanel);

		buttonTable.put(skierNum, new Button[] {checkpointButton, finishlineButton});
	}

	public JComponent getTblCmp( int key, int rowIdx ) {
		return buttonTable.get(key)[rowIdx];
	}
}
