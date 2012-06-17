package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailAffichageCtrl implements ActionListener {
	
	private MailAffichageView view;
	
	public MailAffichageCtrl(MailAffichageView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailListeView(this.view.getMainPanel(), this.view.getTheLastMode());
		}
	}
}
