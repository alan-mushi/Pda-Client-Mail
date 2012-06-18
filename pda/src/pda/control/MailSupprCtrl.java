package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.HashMap ;

/**
 * Classe gérant les actions pour l'interface de suppression de mails et de contacts
 */
public class MailSupprCtrl implements ActionListener, StaticRefs {

	/** Une référence vers la vue */
	private MailSupprView view;

	/**
	 * Constructeur
	 * @param view Une référence vers la vue correspondante au controleur.
	 */
	public MailSupprCtrl(MailSupprView view) {
		this.view = view;
	}

	/**
	 * Gère les évènements pour l'interface lors de la sélection d'un contact avant l'envoie d'un mail.
	 * @param e L'évènement.
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour() && this.view.getMode() == MailSupprView.MODE_SUPPRESSION_MAIL) {
			if(this.view.getTheLastMode() == MailListeView.MODE_BOITE_ENVOIE) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_ENVOIE);
			}
			if(this.view.getTheLastMode() == MailListeView.MODE_BOITE_RECEPTION) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_RECEPTION);
			}
			if(this.view.getTheLastMode() == MailListeView.MODE_BROUILLON) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BROUILLON);
			}
		}
		else if(src == this.view.getBoutonRetour() && this.view.getMode() == MailSupprView.MODE_SUPPRESSION_CONTACT) {
			new MailListeContactView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonSupprimer() && this.view.getMode() == MailSupprView.MODE_SUPPRESSION_CONTACT) {
			try {
				JCheckBox[] listeCheckBox = this.view.getCheckBox();
				Contacts contacts = (Contacts) myDB.charger(contactsFile);
				for(int i=0; i < listeCheckBox.length; i++) {
					if(listeCheckBox[i].isSelected()) {
						contacts.supprimer(listeCheckBox[i].getText());
					}
				}
				contacts.sauver();
				new MailListeContactView(this.view.getMainPanel());
			}
			catch(IllegalArgumentException erreur) {
				System.err.println(erreur.getMessage());
			}
			catch(FileNotFoundException erreur) {
				System.err.println(erreur.getMessage());
			}
		}
		else if(src == this.view.getBoutonSupprimer() && this.view.getMode() == MailSupprView.MODE_SUPPRESSION_MAIL) {
			try {
				Mail myMail = (Mail) myDB.charger( mailsFile ) ;
				HashMap<Integer , String> transitionIds = this.view.getTransitionIds() ;
				JCheckBox[] listeCheckBox = this.view.getCheckBox() ;
				if ( listeCheckBox != null && listeCheckBox.length > 0 ) {
					int j = 0 ; // Compte le nombre de cases cochées.
					for ( int i = 0 ; i < listeCheckBox.length ; i++ ) {
						if ( listeCheckBox[i].isSelected() ) {
							myMail.supprMail( transitionIds.get( i ) ) ;
							j++ ;
						}
					}
					if ( j == 0 ) {
						System.out.println( "[-] Vous devez sélectionner des mails/brouillons pour pouvoir en supprimer." ) ;
					}
					else { 
						System.out.println( "[+] Suppression effectuée." ) ;
						myDB.sauvegarder( myMail , mailsFile ) ;
					}
				}

			} 
			catch ( IllegalArgumentException err ) {
				System.out.println( err.getMessage() ) ;
			} 
			catch ( FileNotFoundException err ) {
				System.out.println( err.getMessage() ) ;
			}
			if(this.view.getTheLastMode() == MailListeView.MODE_BOITE_ENVOIE) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_ENVOIE);
			}
			if(this.view.getTheLastMode() == MailListeView.MODE_BOITE_RECEPTION) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BOITE_RECEPTION);
			}
			if(this.view.getTheLastMode() == MailListeView.MODE_BROUILLON) {
				new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BROUILLON);
			}
		}
	}
}
