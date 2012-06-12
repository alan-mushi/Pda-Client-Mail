package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailSupprCtrl implements ActionListener {
	
	private MailSupprView view;
	
	public MailSupprCtrl(MailSupprView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour() && this.view.getMode() == MailSupprView.MODE_SUPPRESSION_MAIL) {
			if(this.view.getTheLastMode() == MailListeView.MODE_BOITE_ENVOIE) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_ENVOIE);
			}
			if(this.view.getTheLastMode() == MailListeView.MODE_BOITE_RECEPTION) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_RECEPTION);
			}
			if(this.view.getTheLastMode() == MailListeView.MODE_BROUILLON) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BROUILLON);
			}
		}
		else if(src == this.view.getBoutonRetour() && this.view.getMode() == MailSupprView.MODE_SUPPRESSION_CONTACT) {
			new MailListeContactView(this.view.getMainPanel());
		}
	}
}
