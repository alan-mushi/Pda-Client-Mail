package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailNewContactView {

	private JPanel mainPanel;
	
	private JLabel labNom, labPrenom, labEmail;
	private JTextField nom, prenom, email;
	
	private JButton retour, creer;
	
	public MailNewContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		labNom = new JLabel("Nom : ");
		labPrenom = new JLabel("Prénom : ");
		labEmail = new JLabel("Email : ");
		
		nom = new JTextField();
		prenom = new JTextField();
		email = new JTextField();
		
		SpringLayout formulaire = new SpringLayout();
		JPanel panelCentre = new JPanel(formulaire);
		formulaire.putConstraint(SpringLayout.WEST, labNom, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labNom, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, labPrenom, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labPrenom, 30, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, labEmail, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labEmail, 50, SpringLayout.WEST, panelCentre);
		
		formulaire.putConstraint(SpringLayout.WEST, nom, 10, SpringLayout.EAST, labNom);
		formulaire.putConstraint(SpringLayout.NORTH, nom, 10, SpringLayout.NORTH, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, prenom, 10, SpringLayout.EAST, labPrenom);
		formulaire.putConstraint(SpringLayout.NORTH, prenom, 30, SpringLayout.NORTH, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, email, 10, SpringLayout.EAST, labEmail);
		formulaire.putConstraint(SpringLayout.NORTH, email, 50, SpringLayout.NORTH, panelCentre);
		
		panelCentre.add(labNom);
		panelCentre.add(nom);
		panelCentre.add(labPrenom);
		panelCentre.add(prenom);
		panelCentre.add(labEmail);
		panelCentre.add(email);
		
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
	}
	
	public void attacherReactions() {
		
	}
}
