package pda.datas ;

import pdaNetwork.client.service.MailClient ;
import pdaNetwork.misc.MailContent ;
import pdaNetwork.misc.ProtocolException ;
import pdaNetwork.misc.ConfigConst ;
import java.util.ArrayList ;
import java.util.HashMap ;

/**
 * Cette classe teste les méthodes de la classe Sync.
 * @see pda.datas.Sync
 */
public class TestSync implements StaticRefs {

	public static void main( String[] args ) {
		//-------------------------------Test du constructeur---------------------------
		System.out.println( "===============================================" ) ;
		System.out.println( "Ce test nécessite un fichier login.bin valide !" ) ;
		System.out.println( "===============================================" ) ;
		System.out.print( "\n[*] Construction de l'objet Login ... " ) ;
		Login myLogin ;
		try { 
			myLogin = (Login) myDB.charger( StaticRefs.loginFile ) ;
			System.out.println( "OK" ) ;
			System.out.print( "	[*] Authentification ... " ) ;
			if ( ! myLogin.logMe( "user" , "pass" ) ) {
				System.out.println( "FAIL ... bad login ?" ) ;
			}
			else { System.out.println( "OK" ) ; }
			System.out.print( "\n[*] Création d'un objet Mail ... " ) ;
			Mail myMails = new Mail() ;
			System.out.println( "OK" ) ;
			System.out.println( "\n[*] Envoit de messages au server (destinataire : compte d'utilisateur local) ... " ) ;
			String fileName = "data/xml/pdaServer/configClient.xml";
			ConfigConst.readConfigFile( fileName , false ) ;
			MailClient sender = new MailClient( "user1" , "aa36dc6e81e2ac7ad03e12fedcb6a2c0" ) ;
			sender.send( new MailContent( "user" , "Objet 1" , "Le texte 1" ) ) ;
			sender.send( new MailContent( "user" , "Objet 2" , "Le texte 2" ) ) ;
			sender.send( new MailContent( "user" , "Objet 3" , "Le texte 3" ) ) ;
			sender.close();
			System.out.println( "[+] Tous les messages ont été envoyés." ) ;

			System.out.println( "\n[*] Création de l'objet Sync (réception + répartition des mails dans Mail) ... " ) ;
			Sync mySync = new Sync( myMails , myLogin ) ;

			//--------------------------Constatations dans Mail-------------------------------
			System.out.println( "\n==============================================================" ) ;
			System.out.println( "Le constructeur a pour effet de charger automatiquement\ndans l'objet Mail les nouveaux mails et supprimer les anciens." ) ;
			System.out.println( "==============================================================" ) ;

			HashMap<String , MailType> recus = myMails.getRecusMap() ;
			System.out.println( "\n[*] Liste des mails recus : \n" + recus ) ;
			System.out.println( "\n\n[*] Suppression du 2ème mail ... " ) ;
			Object[] keys = recus.keySet().toArray() ;
			String delId = (String) keys[1] ;
			myMails.supprMail( delId ) ;
			System.out.println( "	[*] Contenu de la liste des emails supprimés : " + myMails.getSupprList() ) ;

			System.out.print( "[*] Création d'un nouveau mail (destinataire = user1) ... " ) ;
			// On ajoute un message à la liste des messages a envoyer.
			myMails.addToSend( new MailType( "user1" , "test" , "rien de spécial." , "user" , MailType.TOSEND ) ) ;

			System.out.println( "\n\n[*] Pour que les opérations soient effectives il faut recréer un objet Sync ... " ) ;
			mySync = new Sync( myMails , myLogin ) ;

			System.out.println( "\n[*] Le mail a t-il été supprimé sur le serveur ? ... " ) ;
			fileName = "data/xml/pdaServer/configClient.xml";
			ConfigConst.readConfigFile( fileName , false ) ;
			MailClient receiver = new MailClient( myLogin.getUser() , myLogin.getPasswd() ) ;

			ArrayList<String> headers = receiver.getHeaders() ;
			if ( ! headers.contains( delId ) ) { System.out.println( "	[+] Le mail a bien été supprimé sur le serveur." ) ; }
			else { System.out.println( "	[-] Le mail n'a pas été supprimé du serveur ... FAIL" ) ; }

			System.out.print( "	[*] Suppression de tous les messages de user ... " ) ;
			for ( int i = 0 ; i < headers.size() ; i++ ) { receiver.delete( headers.get(i) ) ; }
			System.out.println( "OK" ) ;
			receiver.close() ;

			System.out.println( "\n\n[*] L'utilisateur 1 a t-il reçu le mail ? ... " ) ;
			fileName = "data/xml/pdaServer/configClient.xml";
			ConfigConst.readConfigFile( fileName , false ) ;
			receiver = new MailClient( "user1" , "aa36dc6e81e2ac7ad03e12fedcb6a2c0" ) ;
			headers = receiver.getHeaders() ;
			System.out.println( "	[*] headers from user1 : " + headers ) ;
			if ( headers.size() == 1 ) {
				System.out.println( "	[+] Le mail a bien été envoyé." ) ;
				System.out.println( "	[+] message : " + new MailType( receiver.receive(headers.get(0)) , MailType.RECU ) ) ;
			}
			else { System.out.println( "	[-] Le mail n'a pas été envoyé ... FAIL" ) ; }
			receiver.delete( headers.get(0) ) ;
			receiver.close() ;
		} 
		catch ( IllegalArgumentException e ) { 
			System.out.println( "FAIL : " + e.getMessage() ) ; 
		}
		catch ( java.io.FileNotFoundException e ) { 
			System.out.println( "FAIL : " + e.getMessage() ) ; 
		}
		catch ( ProtocolException e ) { 
			System.out.println( e.getMessage() ) ; 
		}
	}
}
