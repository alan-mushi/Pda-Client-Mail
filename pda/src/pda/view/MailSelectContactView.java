package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe permettant de sélectionner des contacts avant d'envoyer un mail.
*/
public class MailSelectContactView {

	/** Le panel principal de l'application */
	private JPanel mainPanel;
	
	/** Boutons de navigation */
	private JButton retour, envoyer;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	*/
	public MailSelectContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Permet d'initialiser l'interface graphique.
	*/
	private void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		JCheckBox contact1 = new JCheckBox("Thibault Guittet");
		JCheckBox contact2 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact3 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact4 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact5 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact6 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact7 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact8 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact9 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact10 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact11 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact12 = new JCheckBox("Guillaume Claudic");
		JCheckBox contact13 = new JCheckBox("Thibault Guittet");
		JCheckBox contact14 = new JCheckBox("Thibault Guittet");
		JCheckBox contact15 = new JCheckBox("Thibault Guittet");
		JCheckBox contact16 = new JCheckBox("Thibault Guittet");
		JCheckBox contact17 = new JCheckBox("Thibault Guittet");
		JCheckBox contact18 = new JCheckBox("Thibault Guittet");
		JCheckBox contact19 = new JCheckBox("Thibault Guittet");
		JCheckBox contact20 = new JCheckBox("Thibault Guittet");
		
		JPanel panelCentre = new JPanel();
		BoxLayout layoutCentre = new BoxLayout(panelCentre, BoxLayout.Y_AXIS);
		panelCentre.setLayout(layoutCentre);
		
		JScrollPane defilementContact = new JScrollPane(panelCentre);
		panelCentre.add(contact1);
		panelCentre.add(contact2);
		panelCentre.add(contact3);
		panelCentre.add(contact4);
		panelCentre.add(contact5);
		panelCentre.add(contact6);
		panelCentre.add(contact7);
		panelCentre.add(contact8);
		panelCentre.add(contact9);
		panelCentre.add(contact10);
		panelCentre.add(contact11);
		panelCentre.add(contact12);
		panelCentre.add(contact13);
		panelCentre.add(contact14);
		panelCentre.add(contact15);
		panelCentre.add(contact16);
		panelCentre.add(contact17);
		panelCentre.add(contact18);
		panelCentre.add(contact19);
		panelCentre.add(contact20);
		
		mainPanel.add(defilementContact, BorderLayout.CENTER);
		
		retour = new JButton("Retour");
		envoyer = new JButton("Envoyer");
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(retour);
		panelBas.add(envoyer);
		
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailSelectContactCtrl select = new MailSelectContactCtrl(this);
		retour.addActionListener(select);
	}
	
	/**
	* Permet de récupérer le bouton retour.
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Permet de retourner le panel principal de l'application.
	* @return Le panel principal.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Permet de récupérer le bouton envoyer.
	* @return Le bouton envoyer.
	*/
	public JButton getBoutonEnvoyer() {
		return this.envoyer;
	}
}
