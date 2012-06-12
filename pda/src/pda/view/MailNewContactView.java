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
		
		nom = new JTextField(15);
		prenom = new JTextField(15);
		email = new JTextField(15);
		
		SpringLayout formulaire = new SpringLayout();
		JPanel panelCentre = new JPanel(formulaire);
		formulaire.putConstraint(SpringLayout.WEST, labNom, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labNom, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, labPrenom, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labPrenom, 50, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, labEmail, 10, SpringLayout.WEST, panelCentre);
		formulaire.putConstraint(SpringLayout.NORTH, labEmail, 90, SpringLayout.WEST, panelCentre);
		
		formulaire.putConstraint(SpringLayout.WEST, nom, 33, SpringLayout.EAST, labNom);
		formulaire.putConstraint(SpringLayout.NORTH, nom, 10, SpringLayout.NORTH, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, prenom, 10, SpringLayout.EAST, labPrenom);
		formulaire.putConstraint(SpringLayout.NORTH, prenom, 50, SpringLayout.NORTH, panelCentre);
		formulaire.putConstraint(SpringLayout.WEST, email, 27, SpringLayout.EAST, labEmail);
		formulaire.putConstraint(SpringLayout.NORTH, email, 90, SpringLayout.NORTH, panelCentre);
		
		panelCentre.add(labNom);
		panelCentre.add(nom);
		panelCentre.add(labPrenom);
		panelCentre.add(prenom);
		panelCentre.add(labEmail);
		panelCentre.add(email);
		
		retour = new JButton("Retour");
		creer = new JButton("Créer");
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(retour);
		panelBas.add(creer);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	public void attacherReactions() {
		MailNewContactCtrl ctrlAdd = new MailNewContactCtrl(this);
		retour.addActionListener(ctrlAdd);
		creer.addActionListener(ctrlAdd);
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	public JButton getBoutonCreer() {
		return this.creer;
	}
}
