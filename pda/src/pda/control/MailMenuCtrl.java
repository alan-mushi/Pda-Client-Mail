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
			new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_RECEPTION);
		}
		else if(src == this.view.getBoutonEnvoyes()) {
			new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_ENVOIE);
		}
		else if(src == this.view.getBoutonBrouillons()) {
			new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BROUILLON);
		}
		else if(src == this.view.getBoutonContacts()) {
			new MailListeContactView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonParametre()) {
			new MailParamView(this.view.getMainPanel());
		}
	}
}
