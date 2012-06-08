package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;


public class MailCtrl implements IApplication, ActionListener {
	
	/* Attributs */
	private String name;
	
	private MailView view;
	
	// private MailView instance;

	/* Constructeur(s) */
	
	//Constructeur utilisé pour pouvoir lancer l'application dans le launcher
	public MailCtrl() {
		view = new MailView( this );
	//	instance = null;
	}
	
	//Constructeur pour pouvoir gérer les réactions notament à l'identification
	// public MailCtrl(MailView instance) {
	// 	view = null;
	// 	this.instance = instance;
	// }

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
		try {
			Object src = e.getSource();
			if(src == this.view.getBoutonValider()) {
				MailMenuView test = new MailMenuView(this.view.getMainPanel());
			}
			// Si la touche enter est pressée sur le champ mdp ou login
			else if ( src == this.view.getFieldMDP() || src == this.view.getFieldLogin() ) {
				MailMenuView test = new MailMenuView(this.view.getMainPanel());
			}
			else
				throw new Exception("La classe n'est pas utilitée correctement. Utilisez le bon constructeur pour l'utilisation de la classe pour que la classe fonctionne normalement.");
		}
		catch(Exception erreur) {
			System.out.println(erreur.getMessage());
		}
	}
}
