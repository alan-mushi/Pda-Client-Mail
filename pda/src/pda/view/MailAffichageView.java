package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator ;
import java.io.FileNotFoundException;

/**
* Classe gérant l'affichage des messages.
*/
public class MailAffichageView implements StaticRefs {
	
	/** Le panel principal de l'application */
	private JPanel mainPanel;
	
	/** Une référence vers la vue de la liste des mails */
	private MailListeView viewListe;
	
	/** Les labels du formulaire d'affichage */
	private JLabel labObjet, labExpediteur;
	
	/** Les champs de texte */
	private JTextField objet, expediteur;
	
	/** La zone de texte ou s'affichera le message */
	private JTextArea message;
	
	/** Les boutons de navigation */
	private JButton retour, repondre;
	
	private MailType mail;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	* @param viewListe La vue de la liste des mails.
	*/
	public MailAffichageView(JPanel thePanel, MailListeView viewListe) {
		this.mainPanel = thePanel;
		this.viewListe = viewListe;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Permet d'initialiser l'interface graphique.
	*/
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		labObjet = new JLabel("Objet : ");
		if(this.viewListe.getMode() != MailListeView.MODE_BOITE_ENVOIE) {
			labExpediteur = new JLabel("Expéditeur : ");
		}
		
		objet = new JTextField();
		objet.setEditable(false);
		if(this.viewListe.getMode() != MailListeView.MODE_BOITE_ENVOIE) {
			expediteur = new JTextField();
			expediteur.setEditable(false);
		}
		message = new JTextArea();
		message.setEditable(false);
		
		try {
			Mail liste = (Mail) myDB.charger(mailsFile);
			HashMap<String , MailType> listeMail = null;
			if(this.viewListe.getMode() != MailListeView.MODE_BOITE_RECEPTION) {
				listeMail = new HashMap<String, MailType>();
				HashMap<String , MailType> listeRecut = liste.getRecusMap() ;
				Iterator iterator1 = listeRecut.keySet().iterator();
				while(iterator1.hasNext()) {
					String cle = (String)iterator1.next();
					listeMail.put(cle, listeRecut.get(cle));
				}
				HashMap<String , MailType> listeLu = liste.getLusMap() ;
				Iterator iterator2 = listeLu.keySet().iterator();
				while(iterator2.hasNext()) {
					String cle = (String) iterator2.next();
					listeMail.put(cle, listeLu.get(cle));
				}
			}
			else if(this.viewListe.getMode() == MailListeView.MODE_BOITE_ENVOIE) {
				listeMail = liste.getEnvoyesMap();
			}
			else if(this.viewListe.getMode() == MailListeView.MODE_BROUILLON) {
				listeMail = liste.getBrouillonsMap();
			}
		
			long identifMails = this.viewListe.getTransitionIds()[this.viewListe.getTableau().getSelectedRow()][1];
			mail = listeMail.get(Long.toString(identifMails));
			if(this.viewListe.getMode() == MailListeView.MODE_BOITE_RECEPTION) {
				if(mail.getType().equals( MailType.RECU) ) {
					liste.changeTo(Long.toString(identifMails), MailType.LU);
					myDB.sauvegarder(liste, mailsFile);
				}
			}

			objet.setText(mail.getObject());
			if(this.viewListe.getMode() != MailListeView.MODE_BOITE_ENVOIE) {
				expediteur.setText(mail.getExpeditor());
			}
			message.setText(mail.getText());
		}
		catch(FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
		
		JScrollPane messageDef = new JScrollPane(message);
		
		JPanel panelCentre = new JPanel(new GridLayout(2, 1));
		JPanel moitier;
		if(this.viewListe.getMode() == MailListeView.MODE_BOITE_ENVOIE) {
			moitier = new JPanel(new GridLayout(4, 1));
			moitier.add(labExpediteur);
			moitier.add(expediteur);
		}
		else {
			moitier = new JPanel(new GridLayout(2, 1));
		}
		moitier.add(labObjet);
		moitier.add(objet);
		
		panelCentre.add(moitier);
		panelCentre.add(messageDef);
	
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		retour = new JButton("Retour");
		repondre = new JButton("Répondre");
		panelBas.add(retour);
		panelBas.add(repondre);
		
		mainPanel.add(panelCentre, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	public void attacherReactions() {
		MailAffichageCtrl controleur = new MailAffichageCtrl(this);
		retour.addActionListener(controleur);
		repondre.addActionListener(controleur);
	}
	
	/**
	* Retourne le panel principal de l'application
	* @return Le panel de l'application
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Retourne le bouton "retour".
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Permet de récupérer le mode de la liste des mails.
	* @return Le mode utilisé par la liste des mails.
	*/
	public int getTheLastMode() {
		return this.viewListe.getMode();
	}
	
	/**
	* Permet de récupérer le bouton répondre.
	* @return Le bouton pour répondre au mail.
	*/
	public JButton getBoutonRepondre() {
		return this.repondre;
	}
	
	/**
	* Retourne le mail actuellement affiché.
	*/
	public MailType getMail() {
		return this.mail;
	}
}
