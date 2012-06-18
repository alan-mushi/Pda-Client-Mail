package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;

/**
* Classe gérant les actions pour l'interface des paramètres.
*/
public class MailParamCtrl implements ActionListener , StaticRefs {
	
	/** Une référence vers la vue */
	private MailParamView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailParamCtrl(MailParamView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface des paramètres.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailMenuView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonModifier()) {
			Config configuration = new Config();
			configuration.setAdresseServeur(this.view.getHote().getText());
			
			try {
				if(validerPort(this.view.getPort().getText()))
					configuration.setPortServeur(this.view.getPort().getText());
			}
			catch(Exception erreur) {
				System.err.println("Port du serveur : " + erreur.getMessage());
			}
			
			configuration.setProxy(this.view.getProxyUsed().isSelected());
			configuration.setAdresseProxy(this.view.getAdresseProxy().getText());
			
			try {
				if(validerPort(this.view.getPortProxy().getText()))
					configuration.setPortProxy(this.view.getPortProxy().getText());
			}
			catch(Exception erreur) {
				System.err.println("Port du proxy : " + erreur.getMessage());
			}
			
			try {
				if(!configuration.sauvegarderConfig())
					throw new Exception("Un problème est survenue lors de l'enregistrement du fichier de configuration.");
			}
			catch(Exception erreur) {
				System.err.println(erreur.getMessage());
			}
			// Changements d'informations sur l'uilisateur
			try {
				Login myLog = (Login) myDB.charger( loginFile ) ;
				boolean userLoginChanged = false ;
				if ( ! this.view.getUserName().getText().equals( myLog.getUser() ) ) {
					try {
						myLog.modifyUser( this.view.getUserName().getText() ) ;
						myDB.sauvegarder( myLog , loginFile ) ;
						userLoginChanged = true ;
					} catch ( IllegalArgumentException err ) {
						System.out.println( err.getMessage() ) ;
					}
				}
				if ( ! this.view.getMdp().getText().equals( myLog.getPasswd() ) ) {
					try {
						myLog.modifyPasswd( this.view.getMdp().getText() ) ;
						myDB.sauvegarder( myLog , loginFile ) ;
						userLoginChanged = true ;
					} catch ( IllegalArgumentException err ) {
						System.out.println( err.getMessage() ) ;
					}
				}
				if ( userLoginChanged ) {
					System.out.println( "\n[+] Changements pour l'utilisateur enregistrés." ) ;
					System.out.println( "[+] Pensez à changer les identifiants dans le fichier du serveur!\n" ) ;
				}
			}
			catch ( IllegalArgumentException ex ) {
				System.out.println( ex.getMessage() ) ;
			}
			catch ( java.io.FileNotFoundException ex ) {
				System.out.println( ex.getMessage() ) ;
			}

			new MailMenuView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonParamDefaut()) {
			Config configuration = new Config();
			try {
				if(!configuration.sauvegarderConfig())
					throw new Exception("Un problème est survenue lors de l'enregistrement du fichier de configuration.");
			}
			catch(Exception erreur) {
				System.err.println(erreur.getMessage());
			}
			
			new MailMenuView(this.view.getMainPanel());
		}
	}
	
	public boolean validerPort(String port) throws Exception {
		int ok = 0;
		boolean ret = false;
		for(int i=0; i<port.length(); i++) {
			if(Character.isDigit(port.charAt(i)))
				ok++;
		}
		
		if(ok == port.length()) {
			if(Integer.parseInt(port) < 65536) {
				ret = true;
			}
			else {
				throw new Exception("Le numéro de port doit être compris entre 0 et 65535");
			}	
		}
		else {
			throw new Exception("Le numéro de port n'est pas correct.");
		}
		
		return ret;
	}
}
