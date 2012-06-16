package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableColumn;
import java.util.HashMap;
import java.io.FileNotFoundException;

/**
* Classe gérant la partie graphique de la boite de réception/envoie/brouillon
*/
public class MailListeView implements StaticRefs {
	
	/** Le panel principal de l'application */
	private JPanel mainPanel;
	
	/** Boutons de navigation */
	private JButton nouveau, editer, supprimer, retour;
	
	/** La liste des mails affiché sous forme de tableau */
	private JTable liste;
	
	/** Le mode de la classe :
	*	Boite de réception : 1
	*	Boite d'envoie : 2
	*	Brouiilon : 3
	*/
	private int mode;
	
	/** Pour spécifier le mode boite de réception */
	public static final int MODE_BOITE_RECEPTION = 1;
	
	/** Pour spécifier le mode boite d'envoie */
	public static final int MODE_BOITE_ENVOIE = 2;
	
	/** Pour spécifier le mode brouillon */
	public static final int MODE_BROUILLON = 3;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application
	* @param theMode Le mode dans lequel la classe doit agir
	*/
	public MailListeView(JPanel thePanel, int theMode) {
		this.mainPanel = thePanel;
		this.mode = theMode;
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
	
		JPanel panelCentre = new JPanel(new GridLayout(1, 1));
		
		String[] nomColones = { "Statut", "Objet", "Expéditeur" };
		
		//La colone 0 doit comporter UNIQUEMENT des types Integer pour que les images puissent s'afficher correctement.
		Object[][] data = listeReception();
							
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
		ImageIcon sup = new ImageIcon("data/img/mail/suppr.png");
		supprimer = new JButton(sup);
		
		JPanel panelBas = new JPanel(new GridLayout(1, 3));
		panelBas.add(retour);
		panelBas.add(supprimer);
		try {
			if(this.mode == MODE_BOITE_RECEPTION || this.mode == MODE_BOITE_ENVOIE) {
				ImageIcon icon = new ImageIcon("data/img/mail/nouveau.png");
				nouveau = new JButton(icon);
				panelBas.add(nouveau);
				editer = null;
			}
			else if(this.mode == MODE_BROUILLON) {
				ImageIcon edit = new ImageIcon("data/img/mail/edit.png");
				editer = new JButton(edit);
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
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailListeCtrl reception = new MailListeCtrl(this);
		
		if(this.mode == MODE_BOITE_RECEPTION || this.mode == MODE_BOITE_ENVOIE)
			nouveau.addActionListener(reception);
		else
			editer.addActionListener(reception);
		
		supprimer.addActionListener(reception);
		
		retour.addActionListener(reception);
	}
	
	/**
	* Retourne la liste des mails de la boite de réception sous forme de tableau à 2 dimension.
	* @return Un tableau contenant le contenu du tableau graphique. Retourne <code>null</code> si la méthode à échouée.
	*/
	private Object[][] listeReception() {
		int sizeTab = 0;
		HashMap<String , MailType> mails = null;
		try {
			Login user = (Login) myDB.charger(loginFile);
			Mail listeMail = (Mail) myDB.charger(mailsFile);
			Sync synchronisation = new Sync(listeMail, user);
			if(synchronisation.getLastConnectionSucced()) {
				myDB.sauvegarder(listeMail, mailsFile);
			}
			
			mails = listeMail.getRecusMap();
			sizeTab = mails.size();
		}
		catch(FileNotFoundException e) {
			System.out.println( "[-] Le fichier mails.bin n'a pas été trouvé, génération d'un nouveau fichier de mails." ) ;
			Mail listeMail = new Mail();
			myDB.sauvegarder(listeMail, mailsFile);
			this.listeReception();
			return ( new Object[0][0] ) ;
		}
		
		Object[][] data;
		if(sizeTab > 0) {
			data = new Object[sizeTab][3];
			Integer nonLu = new Integer(0);
			Integer lu = new Integer(1);
			System.out.println( "mails : "+mails ) ;
			Object[] ids = mails.keySet().toArray() ;
			for ( int i = 0 ; i < sizeTab; i++ ) {
				MailType tmpEmail = mails.get( (String) ids[i] ) ;
				if ( tmpEmail.getType().equals( MailType.LU ) ) {
					data[i][0] = lu ;
				}
				else { data[i][0] = nonLu ; }
				System.out.println( "email n°"+i+" : " + tmpEmail.getObject() ) ;
				System.out.println( "email n°"+i+" : " + tmpEmail.getExpeditor() ) ;
				data[i][1] = tmpEmail.getObject() ;
				data[i][2] = tmpEmail.getExpeditor() ;
			}
		}
		else {
			data = new Object[0][0];
		}
		
		return data;
	}
	
	/**
	* Retourne le bouton "retour".
	* @return Le bouton retour.
	*/
	public JButton getBoutonRetour() {
		return this.retour;
	}
	
	/**
	* Retourne le bouton "nouveau".
	* @return Le bouton nouveau.
	*/
	public JButton getBoutonNouveau() {
		return this.nouveau;
	}
	
	/**
	* Retourne le bouton "supprimer".
	* @return Le bouton supprimer.
	*/
	public JButton getBoutonSupprimer() {
		return this.supprimer;
	}
	
	/**
	* Retourne le panel principal de l'application
	* @return Le panel de l'application
	*/
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
	
	/**
	* Renvoie le mode actuel de l'objet courant.
	* @return Le mode de l'objet courant
	*/
	public int getMode() {
		return this.mode;
	}
}
