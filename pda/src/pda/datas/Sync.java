package pda.datas ;

import pdaNetwork.client.service.MailClient ;
import pdaNetwork.misc.MailContent ;
import pdaNetwork.misc.ProtocolException ;
import pdaNetwork.misc.ConfigConst ;
import java.util.ArrayList ;
import java.util.HashMap ;

/**
 * Syncronise la liste des messages en local avec le serveur.
 */
public class Sync implements StaticRefs {

	/** Indique si la dernière connection a pu être établie. */
	private boolean lastConnectionSucced ;
	/** Référence privée sur Mail. */
	private Mail myMail ;
	/** Références privées pour les logins du serveur. */
	private String user, passwd ;

	/**
	 * Ce constructeur effectue la synchronisation des messages.
	 * @param MailObject Contient toutes les listes des messages.
	 * @param myLog Objet Login qui contient les identifiants.
	 */
	public Sync( Mail MailObject , Login myLog ) {
		try {
			this.user = myLog.getUser() ;
			this.passwd = myLog.getPasswd() ;
			ConfigConst.readConfigFile( "data/xml/pdaServer/configClient.xml" , false ) ;
			this.myMail = MailObject ;
			if ( this.myMail != null ) {
				this.deleteOnServer() ;
				this.getNewMails() ;
				this.sendNewMails() ;
				this.lastConnectionSucced = true ;
			}
			this.lastConnectionSucced = false ;
		} catch ( ProtocolException e ) {
			this.lastConnectionSucced = false ;
		}
	}

	/**
	 * Supprime tous les messages du serveur supprimés en local.
	 */
	private void deleteOnServer() throws ProtocolException {
		ArrayList<String> toDel = this.myMail.getSupprList() ;
		if ( toDel != null ) {
			MailClient deleter = new MailClient( this.user , this.passwd ) ;
			for ( int i = 0 ; i < toDel.size() ; i++ ) {
				deleter.delete( toDel.get(i) ) ;
			}
			toDel.clear() ;
			deleter.close() ;
			System.out.println( "[+] " + toDel.size() + " mail(s) supprimé(s) sur le serveur." ) ;
		}
	}

	/**
	 * Ajoute les nouveaux mails en provenance du serveur à Mail.
	 */
	private void getNewMails() throws ProtocolException {
		MailClient receiver = new MailClient( this.user , this.passwd ) ;
		ArrayList<String> newMails = receiver.getHeaders() ;
		if ( newMails != null && newMails.size() > 0 ) {
			int j = 0 ; // Compte le nombre de mails réellement ajoutées (cf Mail.add() et Mail.unique() )
			for ( int i = 0 ; i < newMails.size() ; i++ ) {
				String id = newMails.get(i) ;
				MailContent email = receiver.receive( id ) ;
				if ( this.myMail.add( id , email , MailType.RECU ) ) { j++ ; }
			}
			System.out.println( "[+] " + j + " nouveau(x) mail(s) ajouté(s) aux mails recus." ) ;
		}
		receiver.close() ;
	}

	/**
	 * Envoit les nouveaux mails stockés dans la HashMap toSend.
	 */
	private void sendNewMails() throws ProtocolException {
		HashMap<String , MailType> toSend = myMail.getToSendMap() ;
		if ( toSend != null && toSend.size() > 0 ) {
			MailClient sender = new MailClient( this.user , this.passwd ) ;
			Object[] ids = toSend.keySet().toArray() ;
			for ( int i = 0 ; i < toSend.size() ; i++ ) {
				sender.send( (MailContent) toSend.get( (String) ids[i] ) ) ;
				this.myMail.changeTo( (String) ids[i] , MailType.ENVOYE ) ;
			}
			toSend.clear() ;
			sender.close() ;
			System.out.println( "[+] " + toSend.size() + " mail(s) envoyé(s)." ) ;
		}
	}

	/**
	 * Retourne si la dernière connection au serveur a été possible.
	 */
	public boolean getLastConnectionSucced() {
		return ( this.lastConnectionSucced ) ;
	}
}
