package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailMenuCtrl implements ActionListener {
	
	private MailMenuView view;
	
	public MailMenuCtrl(MailMenuView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonReception()) {
			new MailReceptionView(view.getMainPanel());
		}
	}
}
