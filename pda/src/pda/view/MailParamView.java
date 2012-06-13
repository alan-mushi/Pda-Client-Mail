package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

/**
* Cette classe affiche et laisse à l'utilisateur la possibilité de modifier les 
* paramètres de l'application.
*/
public class MailParamView {
	/** Panel principal de l'application */
	private JPanel mainPanel;
	
	/** Boutons de navigation */;
	private JButton retour, modifier;
	
	/** Les labels pour la champs du formulaire */
	private JLabel labMdp, labHote, labPort, labAdresseProxy, labPortProxy;
	
	/** Les champs de textes permettant de modifier les paramètres */
	private JTextField hote, port, adresseProxy, portProxy;
	
	/** Le champ "mot de passe" */
	private JPasswordField mdp;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	*/
	public MailParamView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Permet d'initialiser l'interface graphique.
	*/
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		retour = new JButton("Retour");
		modifier = new JButton("Modifier");
		
		labMdp = new JLabel("Mot de passe :");
		labHote = new JLabel("Hôte :");
		labPort = new JLabel("Port :");
		labAdresseProxy = new JLabel("Adresse proxy :");
		labPortProxy = new JLabel("Port proxy :");
		
		mdp = new JPasswordField();
		hote = new JTextField();
		port = new JTextField();
		adresseProxy = new JTextField();
		portProxy = new JTextField();
		
		JPanel panelCentre = new JPanel(new GridLayout(10, 1));
		JScrollPane defilement = new JScrollPane(panelCentre);
		
		panelCentre.add(labMdp);
		panelCentre.add(mdp);
		panelCentre.add(labHote);
		panelCentre.add(hote);
		panelCentre.add(labPort);
		panelCentre.add(port);
		panelCentre.add(labAdresseProxy);
		panelCentre.add(adresseProxy);
		panelCentre.add(labPortProxy);
		panelCentre.add(portProxy);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		
		panelBas.add(retour);
		panelBas.add(modifier);
		
		mainPanel.add(defilement, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	public void attacherReactions() {
		MailParamCtrl controleur = new MailParamCtrl(this);
		retour.addActionListener(controleur);
	}
	
	/**
	* Permet de récupérer le bouton retour.
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Permet de récupérer le bouton modifier.
	* @return Le bouton modifier.
	*/
	public JButton getBoutonModifier() {
		return this.modifier;
	}
	
	/**
	* Permet de retourner le panel principal de l'application.
	* @return Le panel principal.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
