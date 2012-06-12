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
		ImageIcon sup = new ImageIcon("data/img/mail/suppr.png");
		supprimer = new JButton(sup);
		ImageIcon edit = new ImageIcon("data/img/mail/edit.png");
		modifier = new JButton(edit);
		ImageIcon add = new ImageIcon("data/img/mail/nouveau.png");
		nouveau = new JButton(add);
		
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
