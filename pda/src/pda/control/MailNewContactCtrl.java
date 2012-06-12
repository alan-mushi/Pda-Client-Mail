package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailNewContactCtrl implements ActionListener {
	
	private MailNewContactView view;
	
	public MailNewContactCtrl(MailNewContactView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailListeContactView(view.getMainPanel());
		}
		else if(src == this.view.getBoutonCreer()) {
			
		}
	}
}
