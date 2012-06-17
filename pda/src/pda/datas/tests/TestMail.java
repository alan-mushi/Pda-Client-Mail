package pda.datas ;

import java.util.HashMap ;
import java.util.ArrayList ;
import pdaNetwork.misc.MailContent ;

/**
 *
 * <b>La validité du type de mail n'est pas testée dans cette classe.</b>
 * Pour les tests de validité reportez vous au test de MailType.
 * @see pda.datas.Mail
 * @see pda.datas.MailType
 * @see pdaNetwork.misc.MailContent
 */
public class TestMail implements StaticRefs {
	
	public static void main( String[] args ) {
		System.out.print( "[*] Création de l'objet Mail ... " ) ;
		Mail myMail = new Mail() ;
		System.out.println( "OK" ) ;
		
		//-----------------------------Test de la méthode add---------------------------------
		System.out.println( "\n-----------------------------------------" ) ;
		System.out.println( "	Test de la méthode add" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (type = supprimes) ... " ) ;
		try {
			MailContent email = new MailContent( "to" , "object" , "text" , "from" , null ) ;
			myMail.add( myMail.getNextMaxKey( MailType.SUPPR ) , email , MailType.SUPPR ) ;
		}
		catch ( IllegalArgumentException e ) { System.out.println( e.getMessage() ) ; }

		System.out.println( "\n[*] Test basé SANS erreurs (insertion de mails dans la HashMap recus) ... " ) ;
		try {
			MailContent email1 = new MailContent( "to" , "object1" , "text" , "from" , null ) ;
			myMail.add( myMail.getNextMaxKey( MailType.RECU ) , email1 , MailType.RECU ) ;
			MailContent email2 = new MailContent( "to" , "object2" , "text" , "from" , null ) ;
			myMail.add( myMail.getNextMaxKey( MailType.RECU ) , email2 , MailType.RECU ) ;
			MailContent email3 = new MailContent( "to" , "object3" , "text" , "from" , null ) ;
			myMail.add( myMail.getNextMaxKey( MailType.RECU ) , email3 , MailType.RECU ) ;
			System.out.println( "contenu de la hashmap recu : \n"+ myMail.getRecusMap() ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}
		//-----------------------------Test de la méthode changeTo---------------------------------
		System.out.println( "\n-----------------------------------------" ) ;
		System.out.println( "	Test de la méthode changeTo" ) ;
		System.out.println( "\n[*] Test basé surune erreur (type = supprimes) ... " ) ;
		try {
			myMail.changeTo( "1" , MailType.SUPPR ) ;
		} catch ( IllegalArgumentException e ) { System.out.println( e.getMessage() ) ; }

		System.out.print( "\n[*] Test basé SANS erreurs (Le mail repéré par l'id '1' a été changé de HashMap : RECU => LU) ... " ) ;
		try {
			myMail.changeTo( "1" , MailType.LU ) ;
			HashMap<String , MailType> recus , lus ;
			recus = myMail.getRecusMap() ;
			lus = myMail.getLusMap() ;
			if ( recus.size() == 2 && lus.size() == 1 ) {
				System.out.println( "OK : HashMap lus : \n"+lus ) ;
			}
			else {
				System.out.println( "FAIL : La taille des maps est fausse!" ) ;
			}
		} catch ( IllegalArgumentException e ) { System.out.println( e.getMessage() ) ; }
		//-----------------------------Test de la méthode supprMail---------------------------------
		System.out.println( "\n-----------------------------------------" ) ;
		System.out.println( "	Test de la méthode supprMail" ) ;

		System.out.println( "\n[*] Test basé sur une erreur (id non existant dans les HashMaps) ... " ) ;
		try {
			myMail.supprMail( "0" ) ;
		} catch ( IllegalArgumentException e ) { System.out.println( e.getMessage() ) ; }
		System.out.println( "\n[*] Test basé SANS erreurs (suppression du main id='2') ... " ) ;
		try {
			myMail.supprMail( "2" ) ;
			ArrayList<String> supprL = myMail.getSupprList() ;
			System.out.println( "	Contenu de la liste des mails supprimés : " + supprL ) ;
		} catch ( IllegalArgumentException e ) { System.out.println( e.getMessage() ) ; }
		//-----------------------------Test de sauvegarde/chargement---------------------------------
		System.out.println( "\n-----------------------------------------" ) ;
		System.out.println( "	Test de sauvegarde/chargement" ) ;

		System.out.print( "\n[*] Test de sauvegarde ... " ) ;
		try {
			myDB.sauvegarder( myMail , mailsFile ) ;
			System.out.println( "OK" ) ;
		} catch ( IllegalArgumentException e ) { System.out.println( e.getMessage() ) ; }

		System.out.println( "\n[*] Test de chargement ..." ) ;
		try {
			Mail newMails = (Mail) myDB.charger( mailsFile ) ;
			System.out.println( "	[*] Affichage des mails sauvegardés : \n" +newMails ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}
		catch ( java.io.FileNotFoundException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "[*] Suppression du fichier ..." ) ;
		myDB.supprimer( mailsFile ) ;
		System.out.println( "OK" ) ;
	}
}
