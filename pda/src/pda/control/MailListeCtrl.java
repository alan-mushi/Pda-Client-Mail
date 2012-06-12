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
		else if(src == this.view.getBoutonSupprimer()) {
			if(this.view.getMode() == MailListeView.MODE_BOITE_RECEPTION) {
				new MailSupprView(view.getMainPanel(), MailSupprView.MODE_SUPPRESSION_MAIL, MailListeView.MODE_BOITE_RECEPTION);
			}
			else if(this.view.getMode() == MailListeView.MODE_BOITE_ENVOIE) {
				new MailSupprView(view.getMainPanel(), MailSupprView.MODE_SUPPRESSION_MAIL, MailListeView.MODE_BOITE_ENVOIE);
			}
			else if(this.view.getMode() == MailListeView.MODE_BROUILLON) {
				new MailSupprView(view.getMainPanel(), MailSupprView.MODE_SUPPRESSION_MAIL, MailListeView.MODE_BROUILLON);
			}
		}
	}
}
