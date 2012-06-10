package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailCreerCtrl implements ActionListener {
	
	private MailCreerView view;
	
	public MailCreerCtrl(MailCreerView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailReceptionView(view.getMainPanel());
		}
		else if(src == this.view.getBoutonSelectContact()) {
			new MailSelectContactView(view.getMainPanel());
		}
	}
}
