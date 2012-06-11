package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableColumn;


public class MailListeView {
	
	private JPanel mainPanel;
	
	private JButton nouveau, editer, supprimer, retour;
	
	private JTable liste;
	
	private int mode;
	
	public static final int MODE_BOITE_RECEPTION = 1;
	public static final int MODE_BOITE_ENVOIE = 2;
	public static final int MODE_BROUILLON = 3;
	
	public MailListeView(JPanel thePanel, int theMode) {
		this.mainPanel = thePanel;
		this.mode = theMode;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	private void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
	
		JPanel panelCentre = new JPanel(new GridLayout(1, 1));
		
		String[] nomColones = { "Statut", "Objet", "Expéditeur" };
		
		Object[][] data = {	{new Integer(0), "test", "Guillaume Claudic"},
							{new Integer(0), "test", "Thibault Guittet"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un mec louche"},
							{new Integer(0), "test", "Un fan"}
							};
							
		ModeleTableau model = new ModeleTableau(data, nomColones);
							
		liste = new JTable(model);
		liste.setDefaultRenderer(Integer.class, new MailRendererTableau());
		liste.setRowHeight(50);
		TableColumn col = liste.getColumnModel().getColumn(0);
		col.setPreferredWidth(30);
		
		panelCentre.add(liste);
		JScrollPane defilement = new JScrollPane(panelCentre);
		mainPanel.add(liste.getTableHeader(), BorderLayout.NORTH);
		mainPanel.add(defilement, BorderLayout.CENTER);
	
		retour = new JButton("retour");
		supprimer = new JButton("Suppr.");
		
		JPanel panelBas = new JPanel(new GridLayout(1, 3));
		panelBas.add(retour);
		panelBas.add(supprimer);
		try {
			if(this.mode == MODE_BOITE_RECEPTION || this.mode == MODE_BOITE_ENVOIE) {
				nouveau = new JButton("Nouveau");
				panelBas.add(nouveau);
				editer = null;
			}
			else if(this.mode == MODE_BROUILLON) {
				editer = new JButton("Editer");
				panelBas.add(editer);
				nouveau = null;
			}
			else {
				throw new Exception("Le type du bouton est indéterminé. Veuillez vérifier que vous avez spécifié le bon mode dans le constructeur.");
			}
		}
		catch(Exception e) {
			System.out.println("Une erreur est survenue :" + e.getMessage());
		}
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	private void attacherReactions() {
		MailListeCtrl reception = new MailListeCtrl(this);
		
		if(this.mode == MODE_BOITE_RECEPTION || this.mode == MODE_BOITE_ENVOIE)
			nouveau.addActionListener(reception);
		else
			editer.addActionListener(reception);
		
		retour.addActionListener(reception);
	}
	
	public JButton getBoutonRetour() {
		return this.retour;
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
