package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailReceptionCtrl implements ActionListener {
	
	private MailReceptionView view;
	
	public MailReceptionCtrl(MailReceptionView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonNouveau()) {
			new MailCreerView(view.getMainPanel());
		}
	}
}
