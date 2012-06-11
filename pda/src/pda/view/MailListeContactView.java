package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailListeContactView {

	private JPanel mainPanel;
	
	private JButton retour, supprimer, nouveau;
	private JList listeContactsGui;
	private DefaultListModel liste;
	
	public MailListeContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		
		liste = new DefaultListModel();
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");
		liste.addElement("Guillaume Claudic");
		liste.addElement("Thibault Guittet");

		listeContactsGui = new JList(liste);
		JScrollPane defilementContact = new JScrollPane(listeContactsGui);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 3));
		retour = new JButton("Retour");
		supprimer = new JButton("Suppr.");
		nouveau = new JButton("Nouveau");
		
		panelBas.add(retour);
		panelBas.add(supprimer);
		panelBas.add(nouveau);
		
		mainPanel.add(defilementContact, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	public void attacherReactions() {
		MailListeContactCtrl listeCtrl = new MailListeContactCtrl(this);
		retour.addActionListener(listeCtrl);
	}
	
	public JButton getBoutonNouveau() {
		return this.nouveau;
	}
	
	public JButton getBoutonSupprimer() {
		return this.supprimer;
	}
	
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	public JList getListeGUI() {
		return this.listeContactsGui;
	}
	
	public DefaultListModel getListeContact() {
		return this.liste;
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
