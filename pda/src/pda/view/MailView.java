package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailView {
	
	//Panel principal de l'application Mail Client qui s'intègre dans le PDA
	private JPanel mainPanel;
	private JLabel labLogin, labMdp;
	private JTextField login;
	private JPasswordField mdp;
	private JButton valider;
	
	public MailView() {
		initialiserGui();
		attacherReactions();
	}
	
	private void initialiserGui() {
		mainPanel = new JPanel(new BorderLayout(50, 50));
		JPanel panelCentre = new JPanel(new GridLayout(5, 1, 10, 10));
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
		valider.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelCentre.add(labLogin, BorderLayout.CENTER);
		panelCentre.add(login, BorderLayout.CENTER);
		panelCentre.add(labMdp, BorderLayout.CENTER);
		panelCentre.add(mdp, BorderLayout.CENTER);
		panelCentre.add(valider, BorderLayout.CENTER);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelDroit, BorderLayout.EAST);
		mainPanel.add(panelGauche, BorderLayout.WEST);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
		mainPanel.add(panelHaut, BorderLayout.NORTH);
	}
	
	private void attacherReactions() {
		valider.addActionListener(new MailCtrl(this));
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public JButton getBoutonValider() {
		return this.valider;
	}
}
