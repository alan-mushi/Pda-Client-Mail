package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

public class MailAffichageView implements StaticRefs {
	
	private JPanel mainPanel;
	
	private JLabel labObjet, labExpediteur;
	
	private JTextField objet, expediteur;
	
	private JTextArea message;
	
	private JButton retour;
	
	private int idMail;
	
	private int theLastMode;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	* @param idMail L'identifiant pour récupérer le mail dans la BDD.
	*/
	public MailAffichageView(JPanel thePanel, int idMail, int theLastMode) {
		this.mainPanel = thePanel;
		this.idMail = idMail;
		this.theLastMode = theLastMode;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		labObjet = new JLabel("Objet : ");
		labExpediteur = new JLabel("Expéditeur : ");
		
		//myDB.charger()
		/*HashMap<String , MailType> listeMail = null;
		if(theLastMode == MailListeView.MODE_BOITE_RECEPTION) {
			listeMail = getRecusMap();
		}
		else if(theLastMode == MailListeView.MODE_BOITE_ENVOIE) {
			
		}
		else if(theLastMode == MailListeView.MODE_BROUILLON) {
		
		}*/
		
		objet = new JTextField();
		expediteur = new JTextField();
		message = new JTextArea();
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
		return this.theLastMode;
	}
}
