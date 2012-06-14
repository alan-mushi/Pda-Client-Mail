package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

/** 
* Classe qui gère l'identification à la messagerie au niveau graphique 
*/
public class MailView {
	
	/** Panel principal de l'application Mail Client qui s'intègre dans le PDA */
	private JPanel mainPanel;
	
	/** Les labels d'identification/erreurs de connxion */
	private JLabel labLogin, labMdp, labErreur;
	
	/** Le champs pour renseigner le login */
	private JTextField login;
	
	/** Le champs pour renseigner le mot de passe */
	private JPasswordField mdp;
	
	/** Le bouton pour se connecter */
	private JButton valider;
	
	/** Lien vers le controleur */
	private MailCtrl mailCtrl ;
	
	/**
	* Constructeur
	* @param thisControl Le controleur pour la connexion
	*/
	public MailView( MailCtrl thisControl ) {
		this.mailCtrl = thisControl ;
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Permet d'initialiser l'interface graphique.
	*/
	private void initialiserGui() {
		mainPanel = new JPanel(new BorderLayout(45, 45));
		JPanel panelCentre = new JPanel(new GridLayout(6, 1, 10, 10));
		JPanel panelGauche = new JPanel();
		JPanel panelDroit = new JPanel();
		JPanel panelHaut = new JPanel();
		JPanel panelBas = new JPanel();
		
		labLogin = new JLabel("Login :");
		labLogin.setHorizontalAlignment(SwingConstants.CENTER);
		labMdp = new JLabel("Mot de passe :");
		labMdp.setHorizontalAlignment(SwingConstants.CENTER);
		
		login = new JTextField();
		login.setHorizontalAlignment(SwingConstants.CENTER);
		mdp = new JPasswordField();
		mdp.setHorizontalAlignment(SwingConstants.CENTER);
		
		valider = new JButton("Valider");
		
		labErreur = new JLabel();
		labErreur.setForeground(Color.RED);
		
		valider.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelCentre.add(labLogin, BorderLayout.CENTER);
		panelCentre.add(login, BorderLayout.CENTER);
		panelCentre.add(labMdp, BorderLayout.CENTER);
		panelCentre.add(mdp, BorderLayout.CENTER);
		panelCentre.add(valider, BorderLayout.CENTER);
		panelCentre.add(labErreur, BorderLayout.CENTER);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelDroit, BorderLayout.EAST);
		mainPanel.add(panelGauche, BorderLayout.WEST);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
		mainPanel.add(panelHaut, BorderLayout.NORTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		valider.addActionListener( this.mailCtrl );
		mdp.addActionListener( this.mailCtrl ) ;
		login.addActionListener( this.mailCtrl ) ;
	}
	
	/**
	* Permet de récupérer le Panel principal.
	* @return Le panel principal.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Permet de récupérer le bouton valide.
	* @return Le bouton valider.
	*/
	public JButton getBoutonValider() {
		return this.valider;
	}
	
	/**
	* Permet de récupérer le champs du mot de passe.
	* @return Le champs du mot de passe.
	*/
	public JPasswordField getFieldMDP() { 
		return ( this.mdp ) ; 
	}
	
	/**
	* Permet de récupérer le champs du login.
	* @return Le champs login.
	*/
	public JTextField getFieldLogin() {
		return ( this.login ) ;
	}
	
	/**
	* Définit un message d'erreur s'affichant dans la fenêtre.
	* @param e L'erreur à afficher.
	*/
	public void setErreur(String e) {
		labErreur.setText(e);
	}
}
