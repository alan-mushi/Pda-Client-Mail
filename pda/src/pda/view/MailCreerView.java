package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailCreerView {

	private JPanel mainPanel;
	
	private JLabel labTitre, labObjet, labMessage;
	private JTextField titre, objet;
	private JTextArea message;
	private JButton sauver, selectContact, retour;
	
	public MailCreerView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	public void initialiserGui() {
		labTitre = new JLabel("Titre :");
		labObjet = new JLabel("Objet :");
		labMessage = new JLabel("Message :");
		titre = new JTextField();
		objet = new JTextField();
		message = new JTextArea();
		JScrollPane defilementMessage = new JScrollPane(message);
		sauver = new JButton("Sauver");
		selectContact = new JButton("<html>Select.<br />contacts</html>");
		retour = new JButton("Retour");
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel panelCentre = new JPanel(new GridLayout(2, 1));
		JPanel panelChamps = new JPanel(new GridLayout(4, 1));
		JPanel panelMessage = new JPanel(new GridLayout(2, 1));
		panelChamps.add(labTitre);
		panelChamps.add(titre);
		panelChamps.add(labObjet);
		panelChamps.add(objet);
		panelMessage.add(labMessage);
		panelMessage.add(defilementMessage);
		panelCentre.add(panelChamps);
		panelCentre.add(panelMessage);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 3));
		panelBas.add(retour);
		panelBas.add(sauver);
		panelBas.add(selectContact);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	public void attacherReactions() {
		
	}
}
