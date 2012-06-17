package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.io.FileNotFoundException;

public class MailAffichageView implements StaticRefs {
	
	private JPanel mainPanel;
	
	private MailListeView viewListe;
	
	private JLabel labObjet, labExpediteur;
	
	private JTextField objet, expediteur;
	
	private JTextArea message;
	
	private JButton retour;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	* @param idMail L'identifiant pour récupérer le mail dans la BDD.
	*/
	public MailAffichageView(JPanel thePanel, MailListeView viewListe) {
		this.mainPanel = thePanel;
		this.viewListe = viewListe;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		labObjet = new JLabel("Objet : ");
		labExpediteur = new JLabel("Expéditeur : ");
		
		objet = new JTextField();
		expediteur = new JTextField();
		message = new JTextArea();
		
		try {
			Mail liste = (Mail) myDB.charger(mailsFile);
			HashMap<String , MailType> listeMail = null;
			if(this.viewListe.getMode() == MailListeView.MODE_BOITE_RECEPTION) {
				listeMail = liste.getRecusMap();
			}
			else if(this.viewListe.getMode() == MailListeView.MODE_BOITE_ENVOIE) {
				listeMail = liste.getEnvoyesMap();
			}
			else if(this.viewListe.getMode() == MailListeView.MODE_BROUILLON) {
				listeMail = liste.getBrouillonsMap();
			}
		
			long identifMails = this.viewListe.getTransitionIds()[this.viewListe.getTableau().getSelectedRow()][1];
			MailType mail = listeMail.get(Long.toString(identifMails));
			
			objet.setText(mail.getObject());
			expediteur.setText(mail.getExpeditor());
			message.setText(mail.getText());
		}
		catch(FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		JScrollPane messageDef = new JScrollPane(message);
		
		JPanel panelCentre = new JPanel(new GridLayout(2, 1));
		JPanel moitier = new JPanel(new GridLayout(4, 1));
		moitier.add(labExpediteur);
		moitier.add(expediteur);
		moitier.add(labObjet);
		moitier.add(objet);
		
		panelCentre.add(moitier);
		panelCentre.add(messageDef);
	
		JPanel panelBas = new JPanel(new GridLayout(1, 1));
		retour = new JButton("Retour");
		panelBas.add(retour);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	public void attacherReactions() {
		MailAffichageCtrl controleur = new MailAffichageCtrl(this);
		retour.addActionListener(controleur);
	}
	
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	public int getTheLastMode() {
		return this.viewListe.getMode();
	}
}
