package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;


public class MailCtrl implements IApplication, ActionListener {
	
	/* Attributs */
	private String name;
	
	private MailView view;

	/* Constructeur */
	public MailCtrl() {
		view = new MailView( this );
	}

	/* Méthodes */
	public void start(PdaCtrl pda) {
		System.out.println("Début de l'application Mail Client");
	}
	public String getAppliName() {
		return name;
	}
	public JPanel getAppliPanel() {
		return view.getMainPanel();
	}
	public boolean close() {
		return true;
	}
	public void setAppliName(String theName) {
		this.name = theName;
	}
	
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		
		// Si la touche enter est pressée sur le champ mdp ou login ou le bouton validé
		if(src == this.view.getBoutonValider() || src == this.view.getFieldMDP() || src == this.view.getFieldLogin()) {
			Login connexion = new Login();
			
			try {
				char[] mdpRec = this.view.getFieldMDP().getPassword();
				String mdp = new String(mdpRec);
				if(connexion.logMe(this.view.getFieldLogin().getText(), mdp)) {
					new MailMenuView(this.view.getMainPanel());
				}
				else {
					this.view.setErreur("Identifiant/mot de passe incorrect.");
					System.out.println("Impossible de se connecter au serveur. Identifiant ou mot de passe invalide.");
				}
			}
			catch(IllegalArgumentException erreur) {
				System.out.println(erreur.getMessage());
			}
		}
	}
}
