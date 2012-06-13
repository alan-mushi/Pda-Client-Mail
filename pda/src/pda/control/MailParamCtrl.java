package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe gérant les actions pour l'interface des paramètres.
*/
public class MailParamCtrl implements ActionListener {
	
	/** Une référence vers la vue */
	private MailParamView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailParamCtrl(MailParamView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface des paramètres.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailMenuView(this.view.getMainPanel());
		}
	}
}
