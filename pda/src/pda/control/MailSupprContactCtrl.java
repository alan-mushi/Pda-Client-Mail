package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailSupprContactCtrl implements ActionListener {
	
	private MailSupprContactView view;
	
	public MailSupprContactCtrl(MailSupprContactView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailListeContactView(this.view.getMainPanel());
		}
	}
}
