package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe gérant les évènements pour l'interface du menu principal.
*/
public class MailMenuCtrl implements ActionListener {
	
	/** Une référence vers la vue du menu */
	private MailMenuView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailMenuCtrl(MailMenuView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface du menu principal.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonReception()) {
			new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_RECEPTION);
		}
		else if(src == this.view.getBoutonNotification()) {
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
