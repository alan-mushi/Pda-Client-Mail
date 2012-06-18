package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe créant la vue pour la création d'un email.
*/
public class MailCreerView {
	
	/** Panel principal de l'application */
	private JPanel mainPanel;
	
	/** Label de l'email */
	private JLabel labObjet, labMessage;
	
	/** Champs de textes pour l'email */
	private JTextField objet;
	
	/** Champs de texte multiligne */
	private JTextArea message;
	
	/** Boutons de navigation */
	private JButton sauver, selectContact, retour, envoyer;
	
	/** Le mail auquel on répond si réponse il y a */
	private MailType mail;
	
	/**
	* Constructeur
	* @param thePanel Le JPanel principal de l'application.
	*/
	public MailCreerView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Constructeur
	* @param thePanel Le JPanel principal de l'application
	* @param mail Le mail auquel l'utilisateur va répondre
	*/
	public MailCreerView(JPanel thePanel, MailType mail) {
		// On appel pas le premier constructeur car sinon l'ordre d'exécution posera problème.
		this.mainPanel = thePanel;
		this.mail = mail;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Méthode qui initialise l'interface graphique.
	*/
	private void initialiserGui() {
		labObjet = new JLabel("Objet :");
		labMessage = new JLabel("Message :");
		objet = new JTextField(20);
		message = new JTextArea(15, 20);
		if(mail != null) {
			objet.setText("Re : " + mail.getObject());
			message.setText("\n\n\n\n===Message reçut par " + mail.getRecipient() + "===\n" + mail.getText());
		}
		JScrollPane defilementMessage = new JScrollPane(message);
		sauver = new JButton("Sauver");
		if(mail != null) {
			envoyer = new JButton("Répondre");
		}
		else {
			selectContact = new JButton("<html>Select.<br />contacts</html>");
		}
		retour = new JButton("Retour");
		
		mainPanel.setLayout(new BorderLayout());
		
		SpringLayout formulaire = new SpringLayout();
		JPanel panelCentre = new JPanel(formulaire);
		
		formulaire.putConstraint(SpringLayout.WEST, labObjet, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labObjet, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, labMessage, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labMessage, 50, SpringLayout.WEST, panelCentre);
		
		formulaire.putConstraint(SpringLayout.WEST, objet, 33, SpringLayout.EAST, labObjet);
		formulaire.putConstraint(SpringLayout.NORTH, objet, 10, SpringLayout.NORTH, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, defilementMessage, 10, SpringLayout.EAST, labMessage);
		formulaire.putConstraint(SpringLayout.NORTH, defilementMessage, 50, SpringLayout.NORTH, panelCentre);

		panelCentre.add(labObjet);
		panelCentre.add(objet);
		panelCentre.add(labMessage);
		panelCentre.add(defilementMessage);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 3));
		panelBas.add(retour);
		panelBas.add(sauver);
		if(mail != null) {
			panelBas.add(envoyer);
		}
		else {
			panelBas.add(selectContact);
		}
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailCreerCtrl controleur = new MailCreerCtrl(this);
		retour.addActionListener(controleur);
		sauver.addActionListener(controleur);
		
		if(mail != null) {
			envoyer.addActionListener(controleur);
		}
		else {
			selectContact.addActionListener(controleur);
		}
	}
	
	/**
	* Renvoie le bouton permettant d'aller vers l'interface pour sélectionner des contacts.
	* @return Le bouton selectContact.
	*/
	public JButton getBoutonSelectContact() {
		return this.selectContact;
	}
	
	/**
	* Renvoie le bouton permettant de répondre à un mail
	* @return Le bouton pour répondre.
	*/
	public JButton getBoutonEnvoyer() {
		return this.envoyer;
	}
	
	/**
	* Retourne le bouton "retour".
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Retourne le panel principal de l'application.
	* @return Le JPanel de l'application.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Retourne l'objet du message.
	* @return L'objet du message.
	*/
	public JTextField getObjet() {
		return this.objet;
	}
	
	/**
	* Retourne le message.
	* @return Le message.
	*/
	public JTextArea getMessage() {
		return this.message;
	}
	
	/**
	* Retourne le mail auquel on répond.
	* @return Le mail de départ.
	*/
	public MailType getMail() {
		return this.mail;
	}
	
	/**
	* Retourne le bouton pour sauvegarder un mail dans les brouillons.
	* @return Le bouton sauver.
	*/
	public JButton getBoutonSauver() {
		return this.sauver;
	}
}
