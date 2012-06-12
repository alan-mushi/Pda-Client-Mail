package pda.datas;

import javax.swing.table.AbstractTableModel;

public class ModeleTableau extends AbstractTableModel {

	private Object[][] data;
	private String[] title;
	
	public ModeleTableau(Object[][] data, String[] title) {
		this.data = data;
		this.title = title;
	}
	
	public int getColumnCount() {
		return this.title.length;
	}
	
	public int getRowCount() {
		return this.data.length;
	}
	
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}
	
	public String getColumnName(int col) {
 		return this.title[col];
	}
	
	public Class getColumnClass(int col){
		return this.data[0][col].getClass();
	}
}
