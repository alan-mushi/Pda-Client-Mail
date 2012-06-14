package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;
import pdaNetwork.client.service.MailClient;
import java.io.FileNotFoundException;
import pdaNetwork.misc.ProtocolException;
import pdaNetwork.misc.ConfigConst;

/**
* Classe gérant les actions pour l'interface de sélection des contacts avant l'envoi d'un mail
*/
public class MailSelectContactCtrl implements ActionListener, StaticRefs {
	
	/** Une référence vers la vue*/
	private MailSelectContactView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailSelectContactCtrl(MailSelectContactView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface lors de la sélection d'un contact avant l'envoie d'un mail.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonRetour()) {
			new MailCreerView(this.view.getMainPanel());
		}
		else if(src == this.view.getBoutonEnvoyer()) {
			try {
				JCheckBox[] listeCheckBox = this.view.getCheckBox();
				String config = "data/xml/pdaServer/configClient.xml";
				ConfigConst.readConfigFile(config, false);
				Login user = (Login) myDB.charger(loginFile);
				
				for(int i=0; i<listeCheckBox.length; i++) {
					if(listeCheckBox[i].isSelected()) {
						MailType mail = new MailType(listeCheckBox[i].getText(), this.view.getObjet(), this.view.getMessage(), user.getUser(), MailType.ENVOYE);
						MailClient sender = new MailClient(user.getUser(), user.getPasswd());
						sender.send(mail);
						System.out.println("Le mail a été envoyé à " + user.getUser() + ".");
					}
				}
				
				new MailMenuView(this.view.getMainPanel());
			}
			catch(FileNotFoundException erreur) {
				System.err.println(erreur.getMessage());
			}
			catch(ProtocolException erreur) {
				System.err.println(erreur.getMessage());
			}
		}
	}
}
