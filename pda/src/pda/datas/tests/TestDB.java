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
		try {
			myDB.sauvegarder( obj , "" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		try {
			myDB.sauvegarder( obj , null ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (objet null) ... " ) ;
		try {
			myDB.sauvegarder( null , "test" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		try {
			boolean save = myDB.sauvegarder( obj , "file.bin" ) ;
			if ( save ) {
				System.out.println( "OK" ) ;
			}
			else {
				System.out.println( "FAIL" ) ;
			}
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n--------------------------------------\n	Tests de charger" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		try {
			myDB.charger( null ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}
		catch ( java.io.FileNotFoundException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		try {
			myDB.charger( "" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}
		catch ( java.io.FileNotFoundException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		try {
			TestDB loaded = (TestDB) myDB.charger( "file.bin" ) ;
			String tmpChamp = loaded.getChamp() ;
			if ( ! tmpChamp.equals( champ ) ) {
				System.out.println( "FAIL" ) ;
			}
			else {
				System.out.println( "OK" ) ;
			}
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}
		catch ( java.io.FileNotFoundException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n--------------------------------------\n	Test de supprimer" ) ;
		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier null) ... " ) ;
		try {
			myDB.supprimer( null ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (nom du fichier vide) ... " ) ;
		try {
			myDB.supprimer( "" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		try {
			boolean suppr = myDB.supprimer( "file.bin" ) ;
			if ( suppr ) {
				System.out.println( "OK" ) ;
			}
			else {
				System.out.println( "FAIL" ) ;
			}
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé sur une erreur (fichier absent) ... " ) ;
		try {
			boolean suppr = myDB.supprimer( "fichier inconnu" ) ;
			if ( ! suppr ) {
				System.out.println( "OK" ) ;
			}
			else {
				System.out.println( "FAIL" ) ;
			}
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
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
