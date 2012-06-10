package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailListeCtrl implements ActionListener {
	
	private MailListeView view;
	
	public MailListeCtrl(MailListeView view) {
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonNouveau()) {
			new MailCreerView(view.getMainPanel());
		}
		else if(src == this.view.getBoutonRetour()) {
			new MailMenuView(view.getMainPanel());
		}
	}
}
