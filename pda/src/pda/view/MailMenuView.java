package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailMenuView {
	/** Le JPanel principal récupéré de la classe parente*/
	private JPanel mainPanel;
	
	private JButton envoie, reception, parametre, brouillons, contacts, notification;

	public MailMenuView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
	}
	
	public void initialiserGui() {
		envoie = new JButton("Envoie");
		reception = new JButton("Réception");
		parametre = new JButton("Paramètres");
		brouillons = new JButton("Brouillons");
		contacts = new JButton("Contacts");
		notification = new JButton("Pas de nouveaux mails");
	
		mainPanel.setLayout(new BorderLayout(15, 30));
		JPanel panelBouton = new JPanel(new GridLayout(3, 2, 30, 30));
		JPanel panelLigne1 = new JPanel(new GridLayout(1, 2, 30, 20));
		JPanel panelLigne2 = new JPanel(new GridLayout(1, 2, 30, 30));
		
		mainPanel.add(panelBouton, BorderLayout.CENTER);
		mainPanel.add(notification, BorderLayout.SOUTH);
		mainPanel.add(new JPanel(), BorderLayout.WEST);
		mainPanel.add(new JPanel(), BorderLayout.EAST);
		mainPanel.add(new JPanel(), BorderLayout.NORTH);
		
		panelLigne1.add(envoie);
		panelLigne1.add(reception);
		
		panelLigne2.add(parametre);
		panelLigne2.add(brouillons);
		
		panelBouton.add(panelLigne1);
		panelBouton.add(panelLigne2);
		panelBouton.add(contacts);
	}
}
