package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailView {
	
	//Panel principal de l'application Mail Client qui s'intègre dans le PDA
	private JPanel mainPanel;
	private JLabel labLogin, labMdp, labErreur;
	private JTextField login;
	private JPasswordField mdp;
	private JButton valider;
	private MailCtrl mailCtrl ;
	
	public MailView( MailCtrl thisControl ) {
		this.mailCtrl = thisControl ;
		initialiserGui();
		attacherReactions();
	}
	
	private void initialiserGui() {
		mainPanel = new JPanel(new BorderLayout(50, 50));
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
	
	private void attacherReactions() {
		valider.addActionListener( this.mailCtrl );
		mdp.addActionListener( this.mailCtrl ) ;
		login.addActionListener( this.mailCtrl ) ;
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public JButton getBoutonValider() {
		return this.valider;
	}

	public JPasswordField getFieldMDP() { 
		return ( this.mdp ) ; 
	}

	public JTextField getFieldLogin() {
		return ( this.login ) ;
	}
	
	public void setErreur(String e) {
		labErreur.setText(e);
	}
}
