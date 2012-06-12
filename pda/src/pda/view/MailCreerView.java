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
	private JLabel labTitre, labObjet, labMessage;
	
	/** Champs de textes pour l'email */
	private JTextField titre, objet;
	
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
		labTitre = new JLabel("Titre :");
		labObjet = new JLabel("Objet :");
		labMessage = new JLabel("Message :");
		titre = new JTextField();
		objet = new JTextField();
		message = new JTextArea();
		JScrollPane defilementMessage = new JScrollPane(message);
		sauver = new JButton("Sauver");
		selectContact = new JButton("<html>Select.<br />contacts</html>");
		retour = new JButton("Retour");
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel panelCentre = new JPanel(new GridLayout(2, 1));
		JPanel panelChamps = new JPanel(new GridLayout(4, 1));
		JPanel panelMessage = new JPanel(new GridLayout(2, 1));
		panelChamps.add(labTitre);
		panelChamps.add(titre);
		panelChamps.add(labObjet);
		panelChamps.add(objet);
		panelMessage.add(labMessage);
		panelMessage.add(defilementMessage);
		panelCentre.add(panelChamps);
		panelCentre.add(panelMessage);
		
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
}
