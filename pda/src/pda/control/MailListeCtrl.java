package pda.control;

import pda.view.*;
import pda.datas.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.io.FileNotFoundException;

/**
* Classe gérant les évènements pour les listes de mails (reçut/envoyés/brouillons)
*/
public class MailListeCtrl extends MouseAdapter implements ActionListener, StaticRefs {
	
	/** Une référence vers la vue */
	private MailListeView view;
	
	/**
	* Constructeur
	* @param view Une référence vers la vue correspondante au controleur.
	*/
	public MailListeCtrl(MailListeView view) {
		this.view = view;
	}
	
	/**
	* Gère les évènements pour l'interface de la liste des mails.
	* @param e L'évènement.
	*/
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src == this.view.getBoutonNouveau()) {
			new MailCreerView(view.getMainPanel());
		}
		else if(src == this.view.getBoutonRetour()) {
			new MailMenuView(view.getMainPanel());
		}
		else if(src == this.view.getBoutonSupprimer()) {
			if(this.view.getMode() == MailListeView.MODE_BOITE_RECEPTION) {
				new MailSupprView(view.getMainPanel(), MailSupprView.MODE_SUPPRESSION_MAIL, MailListeView.MODE_BOITE_RECEPTION);
			}
			else if(this.view.getMode() == MailListeView.MODE_BOITE_ENVOIE) {
				new MailSupprView(view.getMainPanel(), MailSupprView.MODE_SUPPRESSION_MAIL, MailListeView.MODE_BOITE_ENVOIE);
			}
			else if(this.view.getMode() == MailListeView.MODE_BROUILLON) {
				new MailSupprView(view.getMainPanel(), MailSupprView.MODE_SUPPRESSION_MAIL, MailListeView.MODE_BROUILLON);
			}
		}
	}
	
	/**
	* Méthode gérant les évèments lors des clics dans le JTable.
	* @param e L'évèment.
	*/
	public void mouseClicked(MouseEvent e) {
		if(this.view.getTableau().isRowSelected(this.view.getTableau().getSelectedRow()) && (this.view.getMode() == MailListeView.MODE_BOITE_RECEPTION || this.view.getMode() == MailListeView.MODE_BOITE_ENVOIE )) {
			new MailAffichageView(this.view.getMainPanel(), this.view);
		}
		else if(this.view.getTableau().isRowSelected(this.view.getTableau().getSelectedRow()) && (this.view.getMode() == MailListeView.MODE_BROUILLON)) {
			try {
				Mail liste = (Mail) myDB.charger(mailsFile);
				HashMap<String, MailType> mailBrouillon = liste.getBrouillonsMap();
				long[][] ids = this.view.getTransitionIds();
				System.out.println( "id du brouillon sélectionné : "+ ids[this.view.getTableau().getSelectedRow()][1]) ;
				MailType mail = mailBrouillon.get(String.valueOf( ids[this.view.getTableau().getSelectedRow()][1]));
				new MailCreerView(this.view.getMainPanel(), mail, MailCreerView.MODE_BROUILLON);
			}
			catch(FileNotFoundException erreur) {
				System.err.println(erreur.getMessage());
			}
		}
	}
}
