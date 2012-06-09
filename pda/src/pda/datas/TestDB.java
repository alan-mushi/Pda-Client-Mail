package pda.datas ;

/**
 * Cette classe teste entièrement les méthodes de la classee DB.
 * @see pda.datas.DB
 */
public class TestDB implements java.io.Serializable , StaticRefs {
	
	/** Utilisé pour tester la validité de l'objet chargé. */
	private static String champ ;
	/** Utilisé pour compiler sans warnings. */
	private static final long serialVersionUID = 1L ;

	/**
	 * Le constructeur initialise le champ.
	 */
	public TestDB() {
		champ = "Ceci est le champ originel." ;
	}

	/**
	 * Méthode qui contient les tests.
	 */
	public static void main( String[] args ) {
		
		TestDB obj = new TestDB() ;
		// Ecriture de l'objet courant dans un fichier
		System.out.println( "\n--------------------------------------\n	Tests de sauvegarder" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		myDB.sauvegarder( obj , "" ) ;
		
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		myDB.sauvegarder( obj , null ) ;

		System.out.println( "\n[*] Test basé sur une erreur (objet null) ... " ) ;
		myDB.sauvegarder( null , "test" ) ;

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		boolean save = myDB.sauvegarder( obj , "file.bin" ) ;
		if ( save ) {
			System.out.println( "OK" ) ;
		}
		else {
			System.out.println( "FAIL" ) ;
		}

		System.out.println( "\n--------------------------------------\n	Tests de charger" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		myDB.charger( null ) ;

		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		myDB.charger( "" ) ;

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		TestDB loaded = (TestDB) myDB.charger( "file.bin" ) ;
		String tmpChamp = loaded.getChamp() ;
		if ( ! tmpChamp.equals( champ ) ) {
			System.out.println( "FAIL" ) ;
		}
		else {
			System.out.println( "OK" ) ;
		}

		System.out.println( "\n--------------------------------------\n	Test de supprimer" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		myDB.supprimer( null ) ;

		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		myDB.supprimer( "" ) ;

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		boolean suppr = myDB.supprimer( "file.bin" ) ;
		if ( suppr ) {
			System.out.println( "OK" ) ;
		}
		else {
			System.out.println( "FAIL" ) ;
		}

		System.out.print( "\n[*] Test basé sur une erreur (fichier absent) ... " ) ;
		suppr = myDB.supprimer( "fichier inconnu" ) ;
		if ( ! suppr ) {
			System.out.println( "OK" ) ;
		}
		else {
			System.out.println( "FAIL" ) ;
		}

		System.out.println( "\n\n######################################" ) ;
		System.out.println( "[+] Tous les tests sont achevés." ) ;
		System.out.println( "######################################\n" ) ;
	}

	/**
	 * Utilisé pour savoir si la sauvegarde s'est bien effectuée.
	 */
	public String getChamp() { return ( this.champ ) ; }
}
