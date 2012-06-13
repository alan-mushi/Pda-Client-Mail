package pda.datas;

import javax.swing.table.AbstractTableModel;

/**
* Classe gérant un modèle de tableau personnalisé pour l'affichage dans un tableau de la liste des mails.
*/
public class ModeleTableau extends AbstractTableModel {
	
	/** Les données du tableau */
	private Object[][] data;
	
	/** Les entêtes de colones */
	private String[] title;
	
	/** Le serialVersionUID pour identifier la classe */
	private static final long serialVersionUID = 30L;
	
	/**
	* Constructeur
	* @param data Les données du tableau.
	* @param Les entêtes des colones du tableau.
	*/
	public ModeleTableau(Object[][] data, String[] title) {
		this.data = data;
		this.title = title;
	}
	
	/**
	* Retourne le nombre de colones du tableau.
	* @return Le nombre de colones du tableau.
	*/
	public int getColumnCount() {
		return this.title.length;
	}
	
	/**
	* Retourne le nombre de ligne du tableau
	* @return Le nombre de ligne dans le tableau
	*/
	public int getRowCount() {
		return this.data.length;
	}
	
	/**
	* Retourne la valeur d'une case du tableau.
	* @param row Le numéro de ligne.
	* @param col Le numéro de colone.
	* @return Le contenu de la case demandée via les paramètres.
	*/
	public Object getValueAt(int row, int col) {
		return this.data[row][col];
	}
	
	/**
	* Retourne le nom d'une colone.
	* @param col Le numéro de la colones.
	* @return Le nom de la colone.
	*/
	public String getColumnName(int col) {
 		return this.title[col];
	}
	
	/**
	* Retourne le type de Class de la colone.
	* @param col Le numéro de colones.
	* @return Le nom de la classe de la colone.
	*/
	public Class getColumnClass(int col){
		return this.data[0][col].getClass();
	}
}
