package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe controleur gérant les évènements de la classe MailAffichageView.
*/
public class MailAffichageCtrl implements ActionListener {
	
	/** Une référence vers la vue. */
	private MailAffichageView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailAffichageCtrl(MailAffichageView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface de l'affichage des mails.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailListeView(this.view.getMainPanel(), this.view.getTheLastMode());
		}
		else if(src == this.view.getBoutonRepondre()) {
			new MailCreerView(this.view.getMainPanel(), this.view.getMail(), MailCreerView.MODE_REPONSE, this.view.getTheLastMode());
		}
	}
}
