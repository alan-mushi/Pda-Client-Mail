package pda.view;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class MailRendererTableau extends DefaultTableCellRenderer {
    
    private Icon nonLu, lu, brouillon, envoye;
    
    private static final long serialVersionUID = 31L;

    public MailRendererTableau() {
        super();

        nonLu = new ImageIcon("data/img/mail/nonLu.png");
        lu = new ImageIcon("data/img/mail/lu.png");
        brouillon = new ImageIcon("data/img/mail/brouillon.png");
        envoye = new ImageIcon("data/img/mail/envoye.png");
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Integer type = (Integer) value;

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

