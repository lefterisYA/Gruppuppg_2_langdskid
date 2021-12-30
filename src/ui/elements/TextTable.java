package ui.elements;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

//	JTable table;
public class TextTable extends JScrollPane {
	private static final long serialVersionUID = 7159859631870386429L;
	public enum Linetype { CHECKPOINT, FINISHLINE };
	DefaultTableModel tableModel;
	JTable table;

	public TextTable(String[] colTitles) {
		super(new JTable(new DefaultTableModel(colTitles, 0)));
		JViewport viewport = getViewport(); 
		table = (JTable) viewport.getView();
		table.setDefaultEditor(Object.class, null);
		tableModel = (DefaultTableModel) table.getModel();
	}

	public void addTableRow(String[] fields) { //, int x, int y, boolean absPos) {
		tableModel.addRow(fields);
	}

}