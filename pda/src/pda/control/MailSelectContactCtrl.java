package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailSelectContactCtrl implements ActionListener {
	
	private MailSelectContactView view;
	
	public MailSelectContactCtrl(MailSelectContactView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailCreerView(view.getMainPanel());
		}
	}
}
