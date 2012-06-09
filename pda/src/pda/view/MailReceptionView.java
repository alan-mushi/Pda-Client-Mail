package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailReceptionView {
	
	private JPanel mainPanel;
	
	private JButton nouveau, supprimer;
	private JList listeMailGUI;
	private DefaultListModel listeMail;
	
	public MailReceptionView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
	}
	public void initialiserGui() {
		mainPanel.setLayout(new GridLayout(2, 1));
	
		nouveau = new JButton("Nouveau");
		supprimer = new JButton("Supprimer");
		
		listeMail = new DefaultListModel();
		listeMail.addElement("Mail1");
		listeMail.addElement("Mail2");
		listeMail.addElement("Mail3");
		listeMailGUI = new JList(listeMail);
		
		mainPanel.add(listeMailGUI);
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(nouveau);
		panelBas.add(supprimer);
		mainPanel.add(panelBas);
	}
}
