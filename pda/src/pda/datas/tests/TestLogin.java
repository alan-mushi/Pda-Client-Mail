package pda.datas ;

import pdaNetwork.client.network.Md5 ;

/**
 * Cette classe teste les méthodes de la classe Login.
 * @see pda.datas.Login
 */
public class TestLogin {

	public static void main( String[] args ) {
		System.out.println( "---------------------------------" ) ;
		System.out.println( "	Test de la classe Login" ) ;

		Login monLogin = new Login() ;

		System.out.println( "\n---------------------------------" ) ;
		System.out.println( "	Tests sans utilisateur crée" ) ;

		System.out.println( "\n[*] Test basé sur une erreur (utilisateur null) ... " ) ;
		try {
			monLogin.logMe( null , "passwd" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}
		System.out.println( "\n[*] Test basé sur une erreur (utilisateur vide) ... " ) ;
		try {
			monLogin.logMe( "" , "passwd" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (passwd null) ... " ) ;
		try {
			monLogin.logMe( "user" , null ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}
		System.out.println( "\n[*] Test basé sur une erreur (passwd vide) ... " ) ;
		try {
			monLogin.logMe( "user" , "" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}
		System.out.println( "\n---------------------------------" ) ;
		System.out.println( "	Test en créant un utilisateur" ) ;

		System.out.print( "\n[*] Test basé SANS erreurs => création d'un utilisateur ... " ) ;
		try {
			monLogin.logMe( "username1" , "passwd" ) ;
			System.out.println( "OK" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		String user = monLogin.getUser() ;
		System.out.print( "\n[*] Test de l'accesseur getUser : user = " + user + " ... " ) ;
		if ( user.equals( "username1" ) ) {
			System.out.println( "OK" ) ;
		}
		else { System.out.println( "FAIL" ) ; }

		String hash = monLogin.getPasswd() ;
		System.out.print( "\n[*] Test de l'accesseur getPasswd : passwd (md5 hash) = " + hash + " ... " ) ;
		if ( hash.equals( Md5.encode( "passwd" ) ) ) { System.out.println( "OK" ) ; }
		else { System.out.println( "FAIL : hash différent de ce qu'il devrait..." ) ; }

		System.out.println( "\n---------------------------------" ) ;
		System.out.println( "	Test de modification du nom d'utilisateur." ) ;
		System.out.println( "\n[*] Test basé sur une erreur (utilisateur null) ... " ) ;
		try {
			monLogin.modifyUser( null ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}
		System.out.println( "\n[*] Test basé sur une erreur (utilisateur vide) ... " ) ;
		try {
			monLogin.modifyUser( "" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test SANS erreurs (changement du nom de l'utilisateur) ... " ) ;
		monLogin.modifyUser( "username2" );
		user = monLogin.getUser() ;
		if ( user.equals( "username2" ) ) {
			System.out.println( "OK : username1 => " + user ) ;
		}
		else { System.out.println( "FAIL" ) ; }

		System.out.println( "\n---------------------------------" ) ;
		System.out.println( "	Test de modification du mot de passe." ) ;
		System.out.println( "\n[*] Test basé sur une erreur (passwd null) ... " ) ;
		try {
			monLogin.modifyPasswd( null ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}
		System.out.println( "\n[*] Test basé sur une erreur (passwd vide) ... " ) ;
		try {
			monLogin.modifyPasswd( "" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test SANS erreurs (changement du mot de passe) ... " ) ;
		monLogin.modifyPasswd( "passwd2" );
		try {
			monLogin.logMe( user , "passwd2" ) ;
			System.out.println( "OK : passwd => passwd2" ) ;
		}
		catch ( IllegalArgumentException e ) { System.out.println( "FAIL" ) ; }

		System.out.println( "\n---------------------------------" ) ;
		System.out.println( "	Test de sauvegarde de Login" ) ;

		System.out.print( "\n[*] Ecriture du fichier ... " ) ;
		try {
			boolean save = StaticRefs.myDB.sauvegarder( monLogin , StaticRefs.loginFile ) ;
			if ( save ) { System.out.println( "OK" ) ; }
			else { System.out.println( "FAIL" ) ; }
		} catch ( IllegalArgumentException e ) {
			e.printStackTrace() ;
		}

		System.out.print( "\n[*] Restauration des paramètres depuis le fichier ... " ) ;
		try {
			Login tmpLogin = (Login) StaticRefs.myDB.charger( StaticRefs.loginFile ) ;
			if ( tmpLogin.logMe( "username2" , "passwd2" ) ) {
				System.out.println( "OK" ) ;
			}
			else { System.out.println( "FAIL" ) ; }
		}
		catch ( java.io.FileNotFoundException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Suppression du fichier." ) ;
		StaticRefs.myDB.supprimer( StaticRefs.loginFile ) ;

		System.out.println( "\n######################################" ) ;
		System.out.println( "[+] Tous les tests sont achevés." ) ;
		System.out.println( "######################################\n" ) ;
	}
}
