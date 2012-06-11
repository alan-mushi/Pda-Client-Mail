package pda.view;

import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class MailRendererTableau extends DefaultTableCellRenderer {
    
    private Icon icon;

    public MailRendererTableau() {
        super();

        icon = new ImageIcon("data/img/mail/nonLu.png");
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Integer type = (Integer) value;

        setText("");

        if(type.equals(new Integer(0))){
            setIcon(icon);
        }

        return this;
    }
}

