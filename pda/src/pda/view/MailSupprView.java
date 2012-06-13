package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;

/**
* Classe gérant la suppression dans l'application pour les contacts et pour les mails.
*/
public class MailSupprView {

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
		
		JCheckBox b1 = new JCheckBox("Contact 1");
		JCheckBox b2 = new JCheckBox("Contact 2");
		JCheckBox b3 = new JCheckBox("Contact 3");
		JCheckBox b4 = new JCheckBox("Contact 4");
		JCheckBox b5 = new JCheckBox("Contact 5");
		JCheckBox b6 = new JCheckBox("Contact 1");
		JCheckBox b7 = new JCheckBox("Contact 2");
		JCheckBox b8 = new JCheckBox("Contact 3");
		JCheckBox b9 = new JCheckBox("Contact 4");
		JCheckBox b10 = new JCheckBox("Contact 5");
		JCheckBox b11 = new JCheckBox("Contact 1");
		JCheckBox b12 = new JCheckBox("Contact 2");
		JCheckBox b13 = new JCheckBox("Contact 3");
		JCheckBox b14 = new JCheckBox("Contact 4");
		JCheckBox b15 = new JCheckBox("Contact 5");
		JCheckBox b16 = new JCheckBox("Contact 1");
		JCheckBox b17 = new JCheckBox("Contact 2");
		JCheckBox b18 = new JCheckBox("Contact 3");
		JCheckBox b19 = new JCheckBox("Contact 4");
		JCheckBox b20 = new JCheckBox("Contact 5");
		JCheckBox b21 = new JCheckBox("Contact 1");
		JCheckBox b22 = new JCheckBox("Contact 2");
		JCheckBox b23 = new JCheckBox("Contact 3");
		JCheckBox b24 = new JCheckBox("Contact 4");
		JCheckBox b25 = new JCheckBox("Contact 5");
		
		JPanel panelCentre = new JPanel();
		BoxLayout layoutCentre = new BoxLayout(panelCentre, BoxLayout.Y_AXIS);
		panelCentre.setLayout(layoutCentre);
		
		JScrollPane defilementContact = new JScrollPane(panelCentre);
		
		panelCentre.add(b1);
		panelCentre.add(b2);
		panelCentre.add(b3);
		panelCentre.add(b4);
		panelCentre.add(b5);
		panelCentre.add(b6);
		panelCentre.add(b7);
		panelCentre.add(b8);
		panelCentre.add(b9);
		panelCentre.add(b10);
		panelCentre.add(b11);
		panelCentre.add(b12);
		panelCentre.add(b13);
		panelCentre.add(b14);
		panelCentre.add(b15);
		panelCentre.add(b16);
		panelCentre.add(b17);
		panelCentre.add(b18);
		panelCentre.add(b19);
		panelCentre.add(b20);
		panelCentre.add(b21);
		panelCentre.add(b22);
		panelCentre.add(b23);
		panelCentre.add(b24);
		panelCentre.add(b25);
		
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
