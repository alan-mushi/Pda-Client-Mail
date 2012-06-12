package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailSupprContactView {
	private JPanel mainPanel;
	
	private JButton retour, supprimer;
	
	public MailSupprContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	private void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		JCheckBox b1 = new JCheckBox("Contact 1");
		JCheckBox b2 = new JCheckBox("Contact 2");
		JCheckBox b3 = new JCheckBox("Contact 3");
		JCheckBox b4 = new JCheckBox("Contact 4");
		JCheckBox b5 = new JCheckBox("Contact 5");
		JCheckBox b6 = new JCheckBox("Contact 1");
		JCheckBox b7 = new JCheckBox("Contact 2");
		JCheckBox b8 = new JCheckBox("Contact 3");
		JCheckBox b9 = new JCheckBox("Contact 4");
		JCheckBox b10 = new JCheckBox("Contact 5");
		JCheckBox b11 = new JCheckBox("Contact 1");
		JCheckBox b12 = new JCheckBox("Contact 2");
		JCheckBox b13 = new JCheckBox("Contact 3");
		JCheckBox b14 = new JCheckBox("Contact 4");
		JCheckBox b15 = new JCheckBox("Contact 5");
		JCheckBox b16 = new JCheckBox("Contact 1");
		JCheckBox b17 = new JCheckBox("Contact 2");
		JCheckBox b18 = new JCheckBox("Contact 3");
		JCheckBox b19 = new JCheckBox("Contact 4");
		JCheckBox b20 = new JCheckBox("Contact 5");
		JCheckBox b21 = new JCheckBox("Contact 1");
		JCheckBox b22 = new JCheckBox("Contact 2");
		JCheckBox b23 = new JCheckBox("Contact 3");
		JCheckBox b24 = new JCheckBox("Contact 4");
		JCheckBox b25 = new JCheckBox("Contact 5");
		
		JPanel panelCentre = new JPanel();
		BoxLayout layoutCentre = new BoxLayout(panelCentre, BoxLayout.Y_AXIS);
		panelCentre.setLayout(layoutCentre);
		
		JScrollPane defilementContact = new JScrollPane(panelCentre);
		
		panelCentre.add(b1);
		panelCentre.add(b2);
		panelCentre.add(b3);
		panelCentre.add(b4);
		panelCentre.add(b5);
		panelCentre.add(b6);
		panelCentre.add(b7);
		panelCentre.add(b8);
		panelCentre.add(b9);
		panelCentre.add(b10);
		panelCentre.add(b11);
		panelCentre.add(b12);
		panelCentre.add(b13);
		panelCentre.add(b14);
		panelCentre.add(b15);
		panelCentre.add(b16);
		panelCentre.add(b17);
		panelCentre.add(b18);
		panelCentre.add(b19);
		panelCentre.add(b20);
		panelCentre.add(b21);
		panelCentre.add(b22);
		panelCentre.add(b23);
		panelCentre.add(b24);
		panelCentre.add(b25);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		retour = new JButton("Retour");
		ImageIcon sup = new ImageIcon("data/img/mail/suppr.png");
		supprimer = new JButton(sup);
		panelBas.add(retour);
		panelBas.add(supprimer);
		
		mainPanel.add(defilementContact, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	private void attacherReactions() {
		MailSupprContactCtrl supprCtrl = new MailSupprContactCtrl(this);
		retour.addActionListener(supprCtrl);
	}
	
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	public JButton getBoutonSupprimer() {
		return this.supprimer;
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
