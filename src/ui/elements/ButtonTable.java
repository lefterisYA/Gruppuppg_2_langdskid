package ui.elements;

import java.awt.GridLayout;


import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ui.Clock;
import ui.interfaces.GuiCallback;

public class ButtonTable extends JPanel { 
	private static final long serialVersionUID = -3065613945162621665L;

	public enum Linetype { CHECKPOINT, FINISHLINE };

	Map<Integer, Button[]> buttonTable = new HashMap<Integer, Button[]>();

	Clock clk = new Clock(); // TODO REMOVE

	public ButtonTable(int height) {
		super(new GridLayout(height,4));
	}
	
	public void addRow(String skierName, int skierNum, GuiCallback chckPntCback, GuiCallback fnshCback) {
		Button checkpointButton = new Button("Checkpoint", chckPntCback, skierNum);
		Button finishlineButton = new Button("Slutlinje", fnshCback, skierNum);

		JPanel iPanel = new JPanel(new GridLayout(1,4));
		iPanel.add(new JLabel(skierName));
		iPanel.add(checkpointButton);
		iPanel.add(new JLabel(""));
		iPanel.add(finishlineButton);
		iPanel.setBorder(new EmptyBorder(5, 0, 5, 0));

	
		add(iPanel);

		buttonTable.put(skierNum, new Button[] {checkpointButton, finishlineButton});
	}

	public void updateSkierLinepass(int skierNum, Linetype linetype) {
		int bttnIndx =  linetype == Linetype.CHECKPOINT ? 0 : 1;
		buttonTable.get(skierNum)[bttnIndx].setEnabled(false);
		buttonTable.get(skierNum)[bttnIndx].setText(clk.getCurrTime());
	}
	
	public void disableTableComponent( int key, int btnIdx ) {
		buttonTable.get(key)[btnIdx].setEnabled(false);
		buttonTable.get(key)[btnIdx].setText(clk.getCurrTime());
	}
	
	public JComponent getTblCmp( int key, int rowIdx ) {
		return buttonTable.get(key)[rowIdx];
	}
}