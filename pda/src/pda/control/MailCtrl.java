package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe controleur pour l'identification à la messagerie.
*/
public class MailCtrl implements IApplication, ActionListener , StaticRefs {

	/* Attributs */
	/** Le nom de l'application */
	private String name;

	/** Une référence vers la vue */
	private MailView view;

	/** L'objet permettant de se connecter */
	private Login connexion ;
	
	/** Indique si le login est existant */
	private boolean loginNotFound ;
	
	/** Le nom d'utilisateur */
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
	/** 
	* Affiche un message dans la console lors du démarage dans le pda de l'application.
	* @param Une référence vers le controleur du PDA.
	*/
	public void start(PdaCtrl pda) {
		System.out.println("Début de l'application Mail Client");
	}
	
	/**
	* Retourne le nom de l'application.
	* @return Le nom de l'application.
	*/
	public String getAppliName() {
		return name;
	}
	
	/**
	* Retoune le panel du PDA qui sert à contenir l'application.
	* @return Le panel principal.
	*/
	public JPanel getAppliPanel() {
		return view.getMainPanel();
	}
	
	/**
	* Méthode renvoyant true si l'application a bien été fermée.
	* @return renvoie toujours true.
	*/
	public boolean close() {
		return true;
	}
	
	/**
	* Définit le nom de l'application.
	* @param theName Le nom de l'application.
	*/
	public void setAppliName(String theName) {
		this.name = theName;
	}
	
	/**
	* Gère les évènements pour l'interface de login à la messagerie.
	* @param e L'évènement.
	*/
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
