package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe gérant les évènements pour l'interface de l'ajout d'un contact
*/
public class MailNewContactCtrl implements ActionListener {
	
	/** Une référence vers la vue */
	private MailNewContactView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailNewContactCtrl(MailNewContactView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface de l'ajout d'un contact.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailListeContactView(view.getMainPanel());
		}
		else if(src == this.view.getBoutonCreer()) {
			
		}
	}
}
