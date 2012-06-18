package pda.view;

import pda.control.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableColumn;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
* Classe gérant la partie graphique de la boite de réception/envoie/brouillon
*/
public class MailListeView implements StaticRefs {
	
	/** Le panel principal de l'application */
	private JPanel mainPanel;
	
	/** Boutons de navigation */
	private JButton nouveau, supprimer, retour;
	
	/** La liste des mails affiché sous forme de tableau */
	private JTable liste;
	
	/** Le mode de la classe :
	*	Boite de réception : 1
	*	Boite d'envoie : 2
	*	Brouiilon : 3
	*/
	private int mode;
	
	/**
	 * Structure : [case affichage JTable] [id MailType]
	 */
	private long transitionIds[][];
	
	/** Pour spécifier le mode boite de réception */
	public static final int MODE_BOITE_RECEPTION = 1;
	
	/** Pour spécifier le mode boite d'envoie */
	public static final int MODE_BOITE_ENVOIE = 2;
	
	/** Pour spécifier le mode brouillon */
	public static final int MODE_BROUILLON = 3;
	
	/**
	* Constructeur
	* @param thePanel Le panel principal de l'application.
	* @param theMode Le mode dans lequel la classe doit agir.
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
		
		String[] nomColones;
		
		if(this.mode == MailListeView.MODE_BOITE_RECEPTION) {
			nomColones = new String[3];
			nomColones[0] = "Statut";
			nomColones[1] = "Objet";
			nomColones[2] = "Expéditeur";
		}
		else if(this.mode == MailListeView.MODE_BOITE_ENVOIE || this.mode == MailListeView.MODE_BROUILLON) {
			nomColones = new String[2];
			nomColones[0] = "Statut";
			nomColones[1] = "Objet";
		}
		else {
			nomColones = new String[3];
			nomColones[0] = "";
			nomColones[1] = "";
			nomColones[2] = "";
		}
		
		//La colone 0 doit comporter UNIQUEMENT des types Integer pour que les images puissent s'afficher correctement.
		Object[][] data = liste();
							
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
			
		ImageIcon icon = new ImageIcon("data/img/mail/nouveau.png");
		nouveau = new JButton(icon);
		panelBas.add(nouveau);
		
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}
	
	/**
	* Permet de faire le lien entre la vue (cette classe) et son controleur.
	*/
	private void attacherReactions() {
		MailListeCtrl reception = new MailListeCtrl(this);
		
		nouveau.addActionListener(reception);
		
		supprimer.addActionListener(reception);
		
		retour.addActionListener(reception);
		
		liste.addMouseListener(reception);
	}
	
	/**
	* Retourne la liste des mails de la boite de réception sous forme de tableau à 2 dimension.
	* @return Un tableau contenant le contenu du tableau graphique. Retourne <code>null</code> si la méthode à échouée.
	*/
	private Object[][] liste() {
		int sizeTab = 0;
		HashMap<String, MailType> mails = null;
		try {
			Login user = (Login) myDB.charger(loginFile);
			Mail listeMail = (Mail) myDB.charger(mailsFile);
			Sync synchronisation = new Sync(listeMail, user);
			if(synchronisation.getLastConnectionSucced()) {
				myDB.sauvegarder(listeMail, mailsFile);
			}
			
			if(mode == MODE_BOITE_RECEPTION) { 
				HashMap<String, MailType> listeRecut = listeMail.getRecusMap();
				HashMap<String, MailType> listeLu = listeMail.getLusMap();
				
				mails = new HashMap<String, MailType>();
				
				Iterator iterator1 = listeRecut.keySet().iterator();
				while(iterator1.hasNext()) {
					String cle = (String)iterator1.next();
					mails.put(cle, listeRecut.get(cle));
				}
				
				Iterator iterator2 = listeLu.keySet().iterator();
				while(iterator2.hasNext()) {
					String cle = (String) iterator2.next();
					mails.put(cle, listeRecut.get(cle));
				}
			}
			else if(mode == MODE_BOITE_ENVOIE) {
				mails = listeMail.getEnvoyesMap();
			}
			else if(mode == MODE_BROUILLON) {
				mails = listeMail.getBrouillonsMap();
			}
			sizeTab = mails.size();
		}
		catch(FileNotFoundException e) {
			System.out.println( "[-] Le fichier mails.bin n'a pas été trouvé, génération d'un nouveau fichier de mails." ) ;
			Mail listeMail = new Mail();
			myDB.sauvegarder(listeMail, mailsFile);
			this.liste();
			return ( new Object[0][0] ) ;
		}
		
		Object[][] data;
		if(sizeTab > 0) {
			if(this.mode == MODE_BOITE_RECEPTION)
				data = new Object[sizeTab][3];
			else
				data = new Object[sizeTab][2];
				
			Integer nonLu = new Integer(0);
			Integer lu = new Integer(1);
			Integer brouillon = new Integer(2);
			Integer envoye = new Integer(3);
			System.out.println( "mails : "+mails ) ;
			Object[] ids = mails.keySet().toArray() ;
			transitionIds = new long[sizeTab][2];
			Iterator iterator = mails.keySet().iterator();
			
			for ( int i = 0 ; i < sizeTab; i++ ) {
				MailType tmpEmail = mails.get( (String) ids[i] ) ;
				if ( tmpEmail.getType().equals( MailType.LU ) ) {
					data[i][0] = lu ;
				}
				else if(tmpEmail.getType().equals(MailType.BROUILLON)) {
					data[i][0] = brouillon;
				}
				else if(tmpEmail.getType().equals(MailType.ENVOYE)) {
					data[i][0] = envoye;
				}
				else {
					data[i][0] = nonLu ; 
				}
				System.out.println( "email n°"+i+" : " + tmpEmail.getObject() ) ;
				System.out.println( "email n°"+i+" : " + tmpEmail.getExpeditor() ) ;
				data[i][1] = tmpEmail.getObject() ;
				if(mode == MODE_BOITE_RECEPTION) {
					data[i][2] = tmpEmail.getExpeditor() ;
				}
				transitionIds[i][0] = i;
				long id = Long.parseLong((String)iterator.next());
				transitionIds[i][1] = id;
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
	
	/**
	* Retourne le tableau graphique de la liste des mails.
	* @return Le tableau de la liste des mails.
	*/
	public JTable getTableau() {
		return this.liste;
	}
	
	/**
	 * Retourne la table des transitions entre la case et l'id des mails.
	 * @see pda.view.MailListeView
	 */
	public long[][] getTransitionIds() {
		return this.transitionIds;
	}
}
