package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

public class MailListeContactCtrl implements ActionListener {
	
	private MailListeContactView view;
	
	public MailListeContactCtrl(MailListeContactView view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailMenuView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonSupprimer()) {
			//On passe -1 pour le dernier paramètre car il n'y a pas de "mode" pour la classe parente.
			new MailSupprView(this.view.getMainPanel(), MailSupprView.MODE_SUPPRESSION_CONTACT, -1);
		}
		else if(src == this.view.getBoutonNouveau()) {
			new MailNewContactView(this.view.getMainPanel());
		}
	}
}
