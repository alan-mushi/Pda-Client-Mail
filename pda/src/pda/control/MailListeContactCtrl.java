package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe gérant les évènements pour la liste des contacts
*/
public class MailListeContactCtrl implements ActionListener {
	
	/** Une référence vers la vue */
	private MailListeContactView view;
	
	/**
	* Constructeur
	* @param view La vue correspondante au controleur.
	*/
	public MailListeContactCtrl(MailListeContactView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface de la liste des contacts.
	* @param e L'évènement.
	*/
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
