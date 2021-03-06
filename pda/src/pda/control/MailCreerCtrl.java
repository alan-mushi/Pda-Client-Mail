package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import pdaNetwork.misc.MailContent;

/**
* Classe controleur pour l'interface de rédaction de mail.
*/
public class MailCreerCtrl implements ActionListener, StaticRefs {
	
	/** Une référence vers la vue */
	private MailCreerView view;
	
	/**
	* Constructeur
	* @param view La référence vers la vue.
	*/
	public MailCreerCtrl(MailCreerView view) {
		this.view = view;
	}
	
	/**
	* Méthode gérant les actions
	* @param e L'action.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailListeView(this.view.getMainPanel(), this.view.getTheLastMode());
		}
		else if(src == this.view.getBoutonSelectContact()) {
			new MailSelectContactView(this.view.getMainPanel(), this.view.getObjet().getText(), this.view.getMessage().getText());
		}
		else if(src == this.view.getBoutonEnvoyer()) {
			try {
				Login user = (Login) myDB.charger(loginFile);
				Mail listeEnvoie = (Mail) myDB.charger(mailsFile);
				Contacts contacts = (Contacts) myDB.charger(contactsFile);

				MailType mail = new MailType(this.view.getMail().getExpeditor(), this.view.getObjet().getText(), this.view.getMessage().getText(), user.getUser(), MailType.ENVOYE);
				listeEnvoie.addToSend(mail);
				System.out.println("Le mail va être envoyé à " + this.view.getMail().getExpeditor() + ".");

				Sync synchronisation = new Sync(listeEnvoie, user);
			}
			catch(FileNotFoundException erreur) {
				System.err.println(erreur.getMessage());
			}
			new MailMenuView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonSauver()) {
			try {
				Login user = (Login) myDB.charger(loginFile);
				Mail liste = (Mail) myDB.charger(mailsFile);
				MailContent mail = new MailContent("brouillon", this.view.getObjet().getText(), this.view.getMessage().getText(), user.getUser());
				String id = liste.getNextMaxKey();
				long trueID = Long.parseLong(id) + 1;
				String idFinal = Long.toString(trueID);
				liste.add(idFinal, mail, MailType.BROUILLON);
				myDB.sauvegarder(liste, mailsFile);
			}
			catch(FileNotFoundException erreur) {
				System.err.println(erreur.getMessage());
			}
			new MailListeView(this.view.getMainPanel(), MailListeView.MODE_BROUILLON);
		}
	}
}
