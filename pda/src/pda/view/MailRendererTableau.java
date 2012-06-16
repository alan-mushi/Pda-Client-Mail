package pda.view;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

/**
* Classe permettant d'afficher des images (et éventuellement autres) dans la liste des mails reçut/envoyés/brouillon.
*/
public class MailRendererTableau extends DefaultTableCellRenderer {
    
    /** Les images possibles */
    private Icon nonLu, lu, brouillon, envoye;
    
    /** Le serialVersionUID */
    private static final long serialVersionUID = 31L;

	/**
	* Constructeur
	*/
    public MailRendererTableau() {
        super();

        nonLu = new ImageIcon("data/img/mail/nonLu.png");
        lu = new ImageIcon("data/img/mail/lu.png");
        brouillon = new ImageIcon("data/img/mail/brouillon.png");
        envoye = new ImageIcon("data/img/mail/envoye.png");
    }
	
	/**
	* Méthode redéfinit qui permet celon le nombre passé en paramètre d'afficher une certaine image dans le tableau
	* Voir la doc de DefaultTableCellRenderer pour les paramètres.
	*/
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	System.out.println( "heeelloooo value : " + value) ;
        //Integer type = value;
	Integer type = new Integer( String.valueOf( value ) ) ;

        setText("");

        if(type.equals(new Integer(0))){
            setIcon(nonLu);
        }
        else if(type.equals(new Integer(1))) {
        	setIcon(lu);
        }
        else if(type.equals(new Integer(2))) {
        	setIcon(brouillon);
        }
        else if(type.equals(new Integer(3))) {
        	setIcon(envoye);
        }
        
        return this;
    }
}

