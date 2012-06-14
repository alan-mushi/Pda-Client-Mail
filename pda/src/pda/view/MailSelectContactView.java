package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

/**
* Classe permettant de sélectionner des contacts avant d'envoyer un mail.
*/
public class MailSelectContactView implements StaticRefs {

	/** Le panel principal de l'application */
	private JPanel mainPanel;
	
	/** Boutons de navigation */
	private JButton retour, envoyer;
	
	/** Liste des CheckBox pour pouvoir facilement les identifier */
	private JCheckBox[] listeCheckBox;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	*/
	public MailSelectContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Permet d'initialiser l'interface graphique.
	*/
	private void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());
		
		JPanel panelCentre = new JPanel();
		BoxLayout layoutCentre = new BoxLayout(panelCentre, BoxLayout.Y_AXIS);
		panelCentre.setLayout(layoutCentre);
		
		JScrollPane defilementContact = new JScrollPane(panelCentre);
		
		Contacts contacts = chargerContacts();
		if(contacts.taille() <= 0) {
			JLabel RAS = new JLabel("<html>Aucun contacts a supprimer.<br />Veuillez en créer avant d'envoyer un mail.<html>");
			panelCentre.add(RAS);
		}
		
		ArrayList<Object> liste = new ArrayList<Object>();
		Object[] ret = this.chargerContacts().cles().toArray();
		int i = 0;
		for(i=0; i<ret.length; i++)
			liste.add(ret[i]);
			
		listeCheckBox = new JCheckBox[i];
	
		for(int j=0; j<liste.size(); j++) {
			listeCheckBox[j] = new JCheckBox((String)liste.get(j));
			panelCentre.add(listeCheckBox[j]);
		}
		
		mainPanel.add(defilementContact, BorderLayout.CENTER);
		
		retour = new JButton("Retour");
		envoyer = new JButton("Envoyer");
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		panelBas.add(retour);
		panelBas.add(envoyer);
		
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Charge les contactsFile.
	* @return Renvoie la liste des contacts si une liste est remplie,
	* sinon une nouvelle liste est fabriquée avec un message d'avertissement.
	*/
	private Contacts chargerContacts() {
		Contacts retour = null;
		try {
			retour = (Contacts) myDB.charger( contactsFile ) ;
		}
		catch(IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		catch(FileNotFoundException e) {
			Contacts contacts = new Contacts();
			try {
				contacts.ajouter( "Pas de contacts" , "enregistrés." , "no Mail" ) ;
			} catch ( IllegalArgumentException err ) { System.err.println( err.getMessage() ) ; }
			contacts.sauver();
			return ( contacts ) ;
		}
		return retour;
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailSelectContactCtrl select = new MailSelectContactCtrl(this);
		retour.addActionListener(select);
		envoyer.addActionListener(select);
	}
	
	/**
	* Permet de récupérer le bouton retour.
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Permet de retourner le panel principal de l'application.
	* @return Le panel principal.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Permet de récupérer le bouton envoyer.
	* @return Le bouton envoyer.
	*/
	public JButton getBoutonEnvoyer() {
		return this.envoyer;
	}
	
	/**
	* Retourne la liste des JCheckBox.
	* @return Un tableau de JCheckBox.
	*/
	public JCheckBox[] getCheckBox() {
		return this.listeCheckBox;
	}
}
