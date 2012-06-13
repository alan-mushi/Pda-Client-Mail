package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;

/**
* Cette classe liste les contacts dans la partie GUI de l'application.
*/
public class MailListeContactView implements StaticRefs {

	/** Panel principal de l'application */
	private JPanel mainPanel;
	
	/** Boutons de navigation */
	private JButton retour, supprimer, nouveau, modifier;
	
	/** La liste des contacts affichable */
	private JList listeContactsGui;
	
	/** Le model utilisé pour affiché dans la JList la liste des contacts */
	private DefaultListModel liste;
	
	
	/**
	* Constructeur
	* @param thePanel Le JPanel principal de l'application.
	*/
	public MailListeContactView(JPanel thePanel) {
		this.mainPanel = thePanel;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}
	
	/**
	* Méthode qui initialise l'interface graphique.
	*/
	private void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());

		listeContactsGui = new JList( this.chargerContacts().cles().toArray() ) ;
		JScrollPane defilementContact = new JScrollPane(listeContactsGui);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 4));
		retour = new JButton("<-");
		ImageIcon sup = new ImageIcon("data/img/mail/suppr.png");
		supprimer = new JButton(sup);
		ImageIcon edit = new ImageIcon("data/img/mail/edit.png");
		modifier = new JButton(edit);
		ImageIcon add = new ImageIcon("data/img/mail/nouveau.png");
		nouveau = new JButton(add);
		
		panelBas.add(retour);
		panelBas.add(supprimer);
		panelBas.add(modifier);
		panelBas.add(nouveau);
		
		mainPanel.add(defilementContact, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Charge les contactsFile.
	* @return Renvoie la liste des contacts sinon null.
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
			contacts.sauver();
			chargerContacts();
		}
		
		return retour;
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailListeContactCtrl listeCtrl = new MailListeContactCtrl(this);
		retour.addActionListener(listeCtrl);
		supprimer.addActionListener(listeCtrl);
		modifier.addActionListener(listeCtrl);
		nouveau.addActionListener(listeCtrl);
	}
	
	/**
	* Retourne le bouton "nouveau".
	* @return Le bouton nouveau.
	*/
	public JButton getBoutonNouveau() {
		return this.nouveau;
	}
	
	/**
	* Retourne le bouton "modifier"
	* @return Le bouton modifier
	*/
	public JButton getBoutonModifier() {
		return this.modifier;
	}
	
	/**
	* Retourne le bouton "supprimer".
	* @return Le bouton supprimer.
	*/
	public JButton getBoutonSupprimer() {
		return this.supprimer;
	}
	
	/**
	* Retourne le bouton "retour".
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Retourne la liste permettant d'afficher la liste des contacts dans l'interface.
	* @return La JList des contacts.
	*/
	public JList getListeGUI() {
		return this.listeContactsGui;
	}
	
	/**
	* Retourne le modèle de données pour la JList.
	* @return Le modèle de données inclus dans la JList.
	*/
	public DefaultListModel getListeContact() {
		return this.liste;
	}
	
	/**
	* Retourne le panel principal de l'application.
	* @return Le JPanel de l'application.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
