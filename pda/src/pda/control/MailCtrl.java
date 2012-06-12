package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;


public class MailCtrl implements IApplication, ActionListener , StaticRefs {

	/* Attributs */
	private String name;

	private MailView view;

	private Login connexion ;
	private boolean loginNotFound ;
	public static String username ;

	/* Constructeur */
	public MailCtrl() {
		view = new MailView( this );
		try {
			connexion = (Login) myDB.charger( loginFile ) ;
			loginNotFound = false ;
			this.username = connexion.getUser() ;
		}
		catch ( java.io.FileNotFoundException e ) {
			loginNotFound = true ;
			System.out.println( "[-] Le fichier login.bin n'a pas été trouvé, génération d'un nouveau fichier de login." ) ;
			connexion = new Login() ;
		}
	}

	/* Méthodes */
	public void start(PdaCtrl pda) {
		System.out.println("Début de l'application Mail Client");
	}
	public String getAppliName() {
		return name;
	}
	public JPanel getAppliPanel() {
		return view.getMainPanel();
	}
	public boolean close() {
		return true;
	}
	public void setAppliName(String theName) {
		this.name = theName;
	}

	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();

		// Si la touche enter est pressée sur le champ mdp ou login ou le bouton validé
		if(src == this.view.getBoutonValider() || src == this.view.getFieldMDP() || src == this.view.getFieldLogin()) {
			try {
				char[] mdpRec = this.view.getFieldMDP().getPassword();
				String mdp = new String(mdpRec);
				connexion.logMe(this.view.getFieldLogin().getText(), mdp) ;
				if ( loginNotFound ) { 
					this.username = connexion.getUser() ;
					myDB.sauvegarder( connexion , loginFile ) ;
				}
				new MailMenuView(this.view.getMainPanel());
			}
			catch(IllegalArgumentException erreur) {
				System.out.println(erreur.getMessage());
			}
		}
	}
}
