package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailListeContactView {

	private JPanel mainPanel;
	
	private JButton retour, supprimer, nouveau, modifier;
	private JList listeContactsGui;
	private DefaultListModel liste;
	
	public MailListeContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	private void initialiserGui() {
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
		
		JPanel panelBas = new JPanel(new GridLayout(1, 4));
		retour = new JButton("<-");
		supprimer = new JButton("Sup.");
		modifier = new JButton("Modif.");
		nouveau = new JButton("Nouv.");
		
		panelBas.add(retour);
		panelBas.add(supprimer);
		panelBas.add(modifier);
		panelBas.add(nouveau);
		
		mainPanel.add(defilementContact, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	private void attacherReactions() {
		MailListeContactCtrl listeCtrl = new MailListeContactCtrl(this);
		retour.addActionListener(listeCtrl);
		supprimer.addActionListener(listeCtrl);
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
