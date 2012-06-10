package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailMenuView {
	/** Le JPanel principal récupéré de la classe parente*/
	private JPanel mainPanel;
	
	private JButton envoyes, reception, parametre, brouillons, contacts, notification;

	public MailMenuView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	private void initialiserGui() {
		envoyes = new JButton("Envoyés");
		reception = new JButton("Réception");
		parametre = new JButton("Paramètres");
		brouillons = new JButton("Brouillons");
		contacts = new JButton("Contacts");
		notification = new JButton("Pas de nouveaux mails");
		notification.setEnabled(false);
	
		mainPanel.setLayout(new BorderLayout(15, 30));
		JPanel panelBouton = new JPanel(new GridLayout(3, 2, 30, 30));
		JPanel panelLigne1 = new JPanel(new GridLayout(1, 2, 30, 20));
		JPanel panelLigne2 = new JPanel(new GridLayout(1, 2, 30, 30));
		
		mainPanel.add(panelBouton, BorderLayout.CENTER);
		mainPanel.add(notification, BorderLayout.SOUTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		
		panelLigne1.add(reception);
		panelLigne1.add(envoyes);
		
		panelLigne2.add(parametre);
		panelLigne2.add(brouillons);
		
		panelBouton.add(panelLigne1);
		panelBouton.add(panelLigne2);
		panelBouton.add(contacts);
	}
	
	private void attacherReactions() {
		MailMenuCtrl menuControle = new MailMenuCtrl(this);
		reception.addActionListener(menuControle);
		envoyes.addActionListener(menuControle);
		brouillons.addActionListener(menuControle);
	}
	
	public JButton getBoutonReception() {
		return this.reception;
	}
	
	public JButton getBoutonEnvoyes() {
		return this.envoyes;
	}
	
	public JButton getBoutonParametre() {
		return this.parametre;
	}
	
	public JButton getBoutonBrouillons() {
		return this.brouillons;
	}
	
	public JButton getBoutonContacts() {
		return this.contacts;
	}
	
	public JButton getBoutonNotification() {
		return this.notification;
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
