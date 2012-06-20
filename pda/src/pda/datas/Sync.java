package pda.datas ;

import pdaNetwork.client.service.MailClient ;
import pdaNetwork.misc.MailContent ;
import pdaNetwork.misc.ProtocolException ;
import pdaNetwork.misc.ConfigConst ;
import java.util.ArrayList ;
import java.util.HashMap ;
import java.util.Iterator ;

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
				myDB.sauvegarder( this.myMail , mailsFile ) ;
			}
		} catch ( ProtocolException e ) {
			this.lastConnectionSucced = false ;
			System.out.println( e.getMessage() ) ;
		} catch ( IllegalArgumentException e ) {
			this.lastConnectionSucced = false ;
			System.out.println( e.getMessage() ) ;
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
			deleter.close() ;
			System.out.println( "[+] " + toDel.size() + " mail(s) supprimé(s) sur le serveur." ) ;
			toDel.clear() ;
		}
	}

	/**
	 * Ajoute les nouveaux mails en provenance du serveur à Mail.
	 */
	private void getNewMails() throws ProtocolException {
		MailClient receiver = new MailClient( this.user , this.passwd ) ;
		ArrayList<String> newMails = receiver.getHeaders() ;
		
		// Construction d'une HashMap pour contenir tous les mails recus + lus.
		HashMap<String , MailType> oldMails = myMail.getRecusMap() ;
		HashMap<String , MailType> tmpMap = myMail.getLusMap() ;

		HashMap<String , MailType> looky = new HashMap<String , MailType>(0) ;

		Iterator it = tmpMap.keySet().iterator() ;
		while ( it.hasNext() ) {
			String cle = (String) it.next() ;
			looky.put( cle, tmpMap.get(cle) ) ;
		}
		Iterator it2 = oldMails.keySet().iterator() ;
		while ( it2.hasNext() ) {
			String cle = (String) it2.next() ;
			looky.put( cle , oldMails.get(cle)) ;
		}
		Object[] ids = looky.keySet().toArray() ;

		if ( newMails != null && newMails.size() > 0 ) {
			int j = 0 ; // Compte le nombre de mails réellement ajoutées (cf Mail.add() et Mail.unique() )
			for ( int i = 0 ; i < newMails.size() ; i++ ) {
				String id = newMails.get(i) ;
				MailContent email = receiver.receive( id ) ;
				int k ;
				for ( k = 0 ; k < ids.length ; k++ ) {
					MailType tmpMail = looky.get( (String) ids[k] ) ;
					if ( email.getExpeditor().equals( tmpMail.getExpeditor() ) &&
						email.getObject().equals( tmpMail.getObject() ) &&
						email.getRecipient().equals( tmpMail.getRecipient() ) &&
						email.getText().equals( tmpMail.getText() ) ) { break ; }
				}
				if ( k == ids.length ) {
					// l'ajout est possible
					this.myMail.add( id , email , MailType.RECU ) ;
					j++ ;
				}
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
			sender.close() ;
			System.out.println( "[+] " + toSend.size() + " mail(s) envoyé(s)." ) ;
			toSend.clear() ;
		}
	}

	/**
	 * Retourne si la dernière connection au serveur a été possible.
	 */
	public boolean getLastConnectionSucced() {
		return ( this.lastConnectionSucced ) ;
	}
}
