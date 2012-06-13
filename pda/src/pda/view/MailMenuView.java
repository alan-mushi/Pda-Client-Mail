package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe gérant le menu principal de l'application
*/
public class MailMenuView {

	/** Le JPanel principal récupéré de la classe parente*/
	private JPanel mainPanel;
	
	/** Boutons du menu permettant de naviguer dans l'application */
	private JButton envoyes, reception, parametre, brouillons, contacts, notification;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	*/
	public MailMenuView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Permet d'initialiser l'interface graphique.
	*/
	private void initialiserGui() {
		envoyes = new JButton("Envoyés");
		reception = new JButton("Réception");
		parametre = new JButton("Paramètres");
		brouillons = new JButton("Brouillons");
		contacts = new JButton("Contacts");
		notification = new JButton("Pas de nouveaux mails");
		notification.setEnabled(false);
	
		mainPanel.setLayout(new BorderLayout(15, 30));
		JPanel panelBouton = new JPanel(new GridLayout(3, 2, 30, 30));
		JPanel panelLigne1 = new JPanel(new GridLayout(1, 2, 30, 20));
		JPanel panelLigne2 = new JPanel(new GridLayout(1, 2, 30, 30));
		
		mainPanel.add(panelBouton, BorderLayout.CENTER);
		mainPanel.add(notification, BorderLayout.SOUTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		
		panelLigne1.add(reception);
		panelLigne1.add(envoyes);
		
		panelLigne2.add(parametre);
		panelLigne2.add(brouillons);
		
		panelBouton.add(panelLigne1);
		panelBouton.add(panelLigne2);
		panelBouton.add(contacts);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailMenuCtrl menuControle = new MailMenuCtrl(this);
		reception.addActionListener(menuControle);
		envoyes.addActionListener(menuControle);
		brouillons.addActionListener(menuControle);
		contacts.addActionListener(menuControle);
		parametre.addActionListener(menuControle);
	}
	
	/**
	* Récupère le bouton pour accèder à la boite de réception.
	* @return Le bouton de la boite de réception.
	*/
	public JButton getBoutonReception() {
		return this.reception;
	}
	
	/**
	* Récupère le bouton de la boite d'envoie.
	* @return Le bouton de la boite d'envoie.
	*/
	public JButton getBoutonEnvoyes() {
		return this.envoyes;
	}
	
	/**
	* Récupère le bouton pour accèder au paramètres de l'application.
	* @return Le bouton des paramètres.
	*/
	public JButton getBoutonParametre() {
		return this.parametre;
	}
	
	/**
	* Récupère le bouton pour accèder aux brouillons.
	* @return Le boutons des brouillons.
	*/
	public JButton getBoutonBrouillons() {
		return this.brouillons;
	}
	
	/**
	* Récupère le bouton pour accèder à la gestion des contacts.
	* @return Le bouton des contacts.
	*/
	public JButton getBoutonContacts() {
		return this.contacts;
	}
	
	/**
	* Récupère le bouton des notifications.
	* @return le bouton des notifications.
	*/
	public JButton getBoutonNotification() {
		return this.notification;
	}
	
	/**
	* Récupère le panel principal de l'application.
	* @return Le panel principal.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
