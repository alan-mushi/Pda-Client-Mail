package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailSelectContactView {
	private JPanel mainPanel;
	
	private JButton retour, envoyer;
	
	public MailSelectContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	public void initialiserGui() {
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
	
	public void attacherReactions() {
		
	}
}
