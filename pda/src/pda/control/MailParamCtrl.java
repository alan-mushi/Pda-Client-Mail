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
		else if(src == this.view.getBoutonModifier()) {
			Config configuration = new Config();
			configuration.setAdresseServeur(this.view.getHote().getText());
			configuration.setPortServeur(this.view.getPort().getText());
			configuration.setProxy(this.view.getProxyUsed().isSelected());
			configuration.setAdresseProxy(this.view.getAdresseProxy().getText());
			configuration.setPortProxy(this.view.getPortProxy().getText());
			try {
				if(!configuration.sauvegarderConfig())
					throw new Exception("Un problème est survenue lors de l'enregistrement du fichier de configuration.");
			}
			catch(Exception erreur) {
				System.err.println(erreur.getMessage());
			}
				
			new MailMenuView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonParamDefaut()) {
			Config configuration = new Config();
			try {
				if(!configuration.sauvegarderConfig())
					throw new Exception("Un problème est survenue lors de l'enregistrement du fichier de configuration.");
			}
			catch(Exception erreur) {
				System.err.println(erreur.getMessage());
			}
			
			new MailMenuView(this.view.getMainPanel());
		}
	}
}
