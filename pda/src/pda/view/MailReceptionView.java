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
		mainPanel.setLayout(new BorderLayout());
	
		nouveau = new JButton("Nouveau");
		supprimer = new JButton("Supprimer");
		
		listeMail = new DefaultListModel();
		JLabel mail1 = new JLabel("Mail1", new ImageIcon("nonLu.png"), SwingConstants.LEFT);
		listeMail.addElement(mail1);
		listeMail.addElement("Mail2");
		listeMail.addElement("Mail3");
		listeMail.addElement("Mail4");
		listeMail.addElement("Mail5");
		listeMail.addElement("Mail6");
		listeMail.addElement("Mail7");
		listeMail.addElement("Mail8");
		listeMail.addElement("Mail9");
		listeMail.addElement("Mail10");
		listeMail.addElement("Mail11");
		listeMail.addElement("Mail12");
		listeMail.addElement("Mail13");
		listeMail.addElement("Mail14");
		listeMail.addElement("Mail15");
		listeMail.addElement("Mail16");
		listeMail.addElement("Mail17");
		listeMail.addElement("Mail18");
		listeMailGUI = new JList(listeMail);
		listeMailGUI.setFixedCellHeight(50);
		JScrollPane listeScroll = new JScrollPane(listeMailGUI);
		
		mainPanel.add(listeScroll, BorderLayout.CENTER);
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(nouveau);
		panelBas.add(supprimer);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
}
