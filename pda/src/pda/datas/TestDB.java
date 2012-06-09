package pda.datas ;

/**
 * Cette classe teste entièrement les méthodes de la classee DB.
 * @see pda.datas.DB
 */
public class TestDB implements java.io.Serializable {
	
	/** Utilisé pour tester la validité de l'objet chargé. */
	private static String champ ;
	/** Utilisé pour compiler sans warnings. */
	private static final long serialVersionUID = 354054054054L ;

	/**
	 *
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
		DB dataB = new DB() ;
		System.out.println( "\n--------------------------------------\n	Tests de sauvegarder" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		dataB.sauvegarder( obj , "" ) ;
		
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		dataB.sauvegarder( obj , null ) ;

		System.out.println( "\n[*] Test basé sur une erreur (objet null) ... " ) ;
		dataB.sauvegarder( null , "test" ) ;

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		boolean save = dataB.sauvegarder( obj , "file.bin" ) ;
		if ( save ) {
			System.out.println( "OK" ) ;
		}
		else {
			System.out.println( "FAIL" ) ;
		}

		System.out.println( "\n--------------------------------------\n	Tests de charger" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		dataB.charger( null ) ;

		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		dataB.charger( "" ) ;

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		TestDB loaded = (TestDB) dataB.charger( "file.bin" ) ;
		String tmpChamp = loaded.getChamp() ;
		if ( ! tmpChamp.equals( champ ) ) {
			System.out.println( "FAIL" ) ;
		}
		else {
			System.out.println( "OK" ) ;
		}

		System.out.println( "\n--------------------------------------\n	Test de supprimer" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		dataB.supprimer( null ) ;

		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		dataB.supprimer( "" ) ;

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		boolean suppr = dataB.supprimer( "file.bin" ) ;
		if ( suppr ) {
			System.out.println( "OK" ) ;
		}
		else {
			System.out.println( "FAIL" ) ;
		}

		System.out.print( "\n[*] Test basé sur une erreur (fichier absent) ... " ) ;
		suppr = dataB.supprimer( "fichier inconnu" ) ;
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
