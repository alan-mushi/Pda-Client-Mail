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
	private JButton sauver, selectContact;
	
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
		sauver = new JButton("Sauver");
		selectContact = new JButton("<html>Select.<br />contacts</html>");
		
		mainPanel.setLayout(new BorderLayout());
		
		JPanel panelCentre = new JPanel(new GridLayout(6, 1));
		panelCentre.add(labTitre);
		panelCentre.add(titre);
		panelCentre.add(labObjet);
		panelCentre.add(objet);
		panelCentre.add(labMessage);
		panelCentre.add(message);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(sauver);
		panelBas.add(selectContact);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	public void attacherReactions() {
		
	}
}
