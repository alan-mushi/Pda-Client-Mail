package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailReceptionView {
	
	private JPanel mainPanel;
	
	private JButton nouveau, supprimer;
	
	public MailReceptionView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
	
		JPanel panelCentre = new JPanel(new GridLayout(22, 1));
		JScrollPane defilement = new JScrollPane(panelCentre);
		mainPanel.add(defilement, BorderLayout.CENTER);
		
		ImageIcon icon = new ImageIcon("data/img/mail/nonLu.png");
		
		JButton test1 = new JButton("<html>Bienvenue sur Mail Client<br /><h5>Guillaume Claudic</h5></html>", icon);
		test1.setBackground(Color.WHITE);
		
		JButton test2 = new JButton("test1", icon);
		test2.setBackground(Color.WHITE);
		
		JButton test3 = new JButton("test1", icon);
		JButton test4 = new JButton("test1", icon);
		JButton test5 = new JButton("test1", icon);
		JButton test6 = new JButton("test1", icon);
		JButton test7 = new JButton("test1", icon);
		JButton test8 = new JButton("test1", icon);
		JButton test9 = new JButton("test1", icon);
		JButton test10 = new JButton("test1", icon);
		JButton test11 = new JButton("test1", icon);
		JButton test12 = new JButton("test1", icon);
		JButton test13 = new JButton("test1", icon);
		JButton test14 = new JButton("test1", icon);
		JButton test15 = new JButton("test1", icon);
		JButton test16 = new JButton("test1", icon);
		JButton test17 = new JButton("test1", icon);
		JButton test18 = new JButton("test1", icon);
		JButton test19 = new JButton("test1", icon);
		JButton test20 = new JButton("test1", icon);
		JButton test21 = new JButton("test1", icon);
		JButton test22 = new JButton("test1", icon);
		
		test1.setHorizontalAlignment(SwingConstants.LEFT);
		test2.setHorizontalAlignment(SwingConstants.LEFT);
		test3.setHorizontalAlignment(SwingConstants.LEFT);
		test4.setHorizontalAlignment(SwingConstants.LEFT);
		test5.setHorizontalAlignment(SwingConstants.LEFT);
		test6.setHorizontalAlignment(SwingConstants.LEFT);
		test7.setHorizontalAlignment(SwingConstants.LEFT);
		test8.setHorizontalAlignment(SwingConstants.LEFT);
		test9.setHorizontalAlignment(SwingConstants.LEFT);
		test10.setHorizontalAlignment(SwingConstants.LEFT);
		test11.setHorizontalAlignment(SwingConstants.LEFT);
		test12.setHorizontalAlignment(SwingConstants.LEFT);
		test13.setHorizontalAlignment(SwingConstants.LEFT);
		test14.setHorizontalAlignment(SwingConstants.LEFT);
		test15.setHorizontalAlignment(SwingConstants.LEFT);
		test16.setHorizontalAlignment(SwingConstants.LEFT);
		test17.setHorizontalAlignment(SwingConstants.LEFT);
		test18.setHorizontalAlignment(SwingConstants.LEFT);
		test19.setHorizontalAlignment(SwingConstants.LEFT);
		test20.setHorizontalAlignment(SwingConstants.LEFT);
		test21.setHorizontalAlignment(SwingConstants.LEFT);
		test22.setHorizontalAlignment(SwingConstants.LEFT);
		
		panelCentre.add(test1);
		panelCentre.add(test2);
		panelCentre.add(test3);
		panelCentre.add(test4);
		panelCentre.add(test5);
		panelCentre.add(test6);
		panelCentre.add(test7);
		panelCentre.add(test8);
		panelCentre.add(test9);
		panelCentre.add(test10);
		panelCentre.add(test11);
		panelCentre.add(test12);
		panelCentre.add(test13);
		panelCentre.add(test14);
		panelCentre.add(test15);
		panelCentre.add(test16);
		panelCentre.add(test17);
		panelCentre.add(test18);
		panelCentre.add(test19);
		panelCentre.add(test20);
		panelCentre.add(test21);
		panelCentre.add(test22);
	
		nouveau = new JButton("Nouveau");
		supprimer = new JButton("Supprimer");
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(nouveau);
		panelBas.add(supprimer);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	public void attacherReactions() {
		nouveau.addActionListener(new MailReceptionCtrl(this));
	}
	
	public JButton getBoutonNouveau() {
		return this.nouveau;
	}
	
	public JButton getBoutonSupprimer() {
		return this.supprimer;
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
