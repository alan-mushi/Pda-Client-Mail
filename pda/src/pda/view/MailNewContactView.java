package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe gérant l'ajout des contacts (partie graphique)
*/
public class MailNewContactView {
	
	/** Le panel principal de l'application. */
	private JPanel mainPanel;
	
	/** Les labels des champs. */
	private JLabel labNom, labPrenom, labEmail;
	
	/** Les champs de texte pour l'ajout de contacts. */
	private JTextField nom, prenom, email;
	
	/** Les boutons de navigation */
	private JButton retour, creer;
	
	/** Le contact à modifier */
	FicheContact contact;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application
	*/
	public MailNewContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	* @param selected Le contact à modifier.
	*/
	public MailNewContactView(JPanel thePanel, FicheContact selected) {
		this.contact = selected;
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
		
		labNom = new JLabel("Nom : ");
		labPrenom = new JLabel("Prénom : ");
		labEmail = new JLabel("Email : ");
		
		nom = new JTextField(15);
		prenom = new JTextField(15);
		email = new JTextField(15);
		
		if(contact != null) {
			if(contact instanceof FicheContact) {
				nom.setText(contact.getNom());
				prenom.setText(contact.getPrenom());
				email.setText(contact.getEmail());
			}
		}
		
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
		if(this.contact != null)
			creer = new JButton("Editer");
		else
			creer = new JButton("Créer");
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(retour);
		panelBas.add(creer);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	public void attacherReactions() {
		MailNewContactCtrl ctrlAdd = new MailNewContactCtrl(this);
		retour.addActionListener(ctrlAdd);
		creer.addActionListener(ctrlAdd);
	}
	
	/**
	* Permet de récupéré le panel principal de l'application.
	* @return Le panel principal.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Permet de récupérer le bouton retour.
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Permet de récupérer le bouton créer.
	* @return Le bouton créer.
	*/
	public JButton getBoutonCreer() {
		return this.creer;
	}
	
	/**
	* Permet de récupérer le champs nom.
	* @return Le nom.
	*/
	public JTextField getNom() {
		return this.nom;
	}
	
	/**
	* Permet de récupérer le champs prénom.
	* @return Le prénom.
	*/
	public JTextField getPrenom() {
		return this.prenom;
	}
	
	/**
	* Permet de récupérer le champs email.
	* @return L'email.
	*/
	public JTextField getEmail() {
		return this.email;
	}
	
	/**
	* Permet de récupérer la fiche contact à modifier.
	* @return la fiche du contact à modifier.
	*/
	public FicheContact getFicheContact() {
		return this.contact;
	}
}
