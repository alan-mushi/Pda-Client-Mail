package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe gérant les actions pour l'interface de sélection des contacts avant l'envoi d'un mail
*/
public class MailSelectContactCtrl implements ActionListener {
	
	/** Une référence vers la vue*/
	private MailSelectContactView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailSelectContactCtrl(MailSelectContactView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface lors de la sélection d'un contact avant l'envoie d'un mail.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailCreerView(view.getMainPanel());
		}
		else if(src == this.view.getBoutonEnvoyer()) {
			
		}
	}
}
