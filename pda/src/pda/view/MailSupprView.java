package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.*;

/**
* Classe gérant la suppression dans l'application pour les contacts et pour les mails.
*/
public class MailSupprView implements StaticRefs {

	/** Le panel principal de l'application */
	private JPanel mainPanel;
	
	/** Boutons de navigation */
	private JButton retour, supprimer;
	
	/** Le mode de la classe :
	*	Suppression de mail : 1
	*	Suppression de contacts : 2
	*/
	private int mode;
	
	/**
	* Le mode de la classe graphique parente (en général pour la classe MailListeView).
	* 1, 2, 3 (cf. doc de MailListeView). Si il n'y a pas de mode à spécifier, -1 généralement ou tout autre nombre ne correspondant
	* pas à un mode de MailListeView.
	*/
	private int theLastMode;
	
	/** Permet de spécifier le mode de suppression de mails */
	public static final int MODE_SUPPRESSION_MAIL = 1;
	
	/** Permet de spécifier le mode de suppression de contacts */
	public static final int MODE_SUPPRESSION_CONTACT = 2;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	* @param theMode Le mode voulu (suppression de mail(1) ou suppression de contacts (2)).
	* @param theLastMode Le mode de la GUI précédente (cf. MailListeView).
	*/
	public MailSupprView(JPanel thePanel, int theMode, int theLastMode) {
		this.mainPanel = thePanel;
		this.mode = theMode;
		this.theLastMode = theLastMode;
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
		ArrayList<Object> liste = new ArrayList<Object>();
		Object[] ret = this.chargerContacts().cles().toArray();
		for(int i=0; i<ret.length; i++)
			liste.add(ret[i]);	
		
		for(int i=0; i<liste.size(); i++) {
			JCheckBox b1 = new JCheckBox((String)liste.get(i));
			panelCentre.add(b1);
		}
		
		JPanel panelBas = new JPanel(new GridLayout(1, 2));
		retour = new JButton("Retour");
		ImageIcon sup = new ImageIcon("data/img/mail/suppr.png");
		supprimer = new JButton(sup);
		panelBas.add(retour);
		panelBas.add(supprimer);
		
		mainPanel.add(defilementContact, BorderLayout.CENTER);
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
		MailSupprCtrl supprCtrl = new MailSupprCtrl(this);
		retour.addActionListener(supprCtrl);
	}
	
	/**
	* Permet de récupérer le bouton retour.
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Permet de récupérer le bouton supprimer.
	* @return Le bouton supprimer.
	*/
	public JButton getBoutonSupprimer() {
		return this.supprimer;
	}
	
	/**
	* Permet de récupérer le Panel principal.
	* @return Le panel principal.
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Renvoie le mode actuel.
	* @return Le mode actuel.
	*/
	public int getMode() {
		return this.mode;
	}
	
	/**
	* Permet de récupérer le mode de la classe graphique parente (par exemple MailListeView)
	* @return Le mode de la classe graphique parente.
	*/
	public int getTheLastMode() {
		return this.theLastMode;
	}
}
