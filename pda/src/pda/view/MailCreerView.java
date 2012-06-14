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
	private JButton sauver, selectContact, retour;
	
	/**
	* Constructeur
	* @param thePanel Le JPanel principale de l'application.
	*/
	public MailCreerView(JPanel thePanel) {
		this.mainPanel = thePanel;
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
		JScrollPane defilementMessage = new JScrollPane(message);
		sauver = new JButton("Sauver");
		selectContact = new JButton("<html>Select.<br />contacts</html>");
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
		panelBas.add(selectContact);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailCreerCtrl creer = new MailCreerCtrl(this);
		retour.addActionListener(creer);
		selectContact.addActionListener(creer);
	}
	
	/**
	* Renvoie le bouton permettant d'aller vers l'interface pour sélectionner des contacts.
	* @return Le bouton selectContact.
	*/
	public JButton getBoutonSelectContact() {
		return this.selectContact;
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
}
