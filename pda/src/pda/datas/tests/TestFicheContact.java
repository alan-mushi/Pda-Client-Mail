package pda.datas ;

/**
 *
 */
public class TestFicheContact {

	/**
	 *
	 */
	public static void main( String[] args ) {

		System.out.println( "\n------------------------------------------------------" ) ;
		System.out.println( "		Tests du constructeur\n" ) ;

		// --------------------------Test NOM-----------------------------------------------------------------
		System.out.println( "[*] Test basé sur une erreur (nom null) ... " ) ;
		try {
			FicheContact contact = new FicheContact( null , "prenom" , "email" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (nom vide) ... " ) ;
		try {
			FicheContact contact = new FicheContact( "" , "prenom" , "email" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		// --------------------------Test PRENOM-----------------------------------------------------------------
		System.out.println( "\n\n[*] Test basé sur une erreur (prenom null) ... " ) ;
		try {
			FicheContact contact = new FicheContact( "nom" , null , "email" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (prenom vide) ... " ) ;
		try {
			FicheContact contact = new FicheContact( "nom" , "" , "email" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		// --------------------------Test EMAIL-----------------------------------------------------------------
		System.out.println( "\n\n[*] Test basé sur une erreur (email null) ... " ) ;
		try {
			FicheContact contact = new FicheContact( "nom" , "prenom" , null ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (email vide) ... " ) ;
		try {
			FicheContact contact = new FicheContact( "nom" , "prenom" , "" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "\n\n[*] Test basé SANS erreurs ... " ) ;
		FicheContact contact ;
		try {
			contact = new FicheContact( "nom" , "prenom" , "email" ) ;
			System.out.println( "OK" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( "FAIL" ) ;
		}

		System.out.println( "\n\n------------------------------------------------------" ) ;
		System.out.println( "		Test de la méthode toString()" ) ;
		try {
			contact = new FicheContact( "nom" , "prenom" , "email" ) ;
			System.out.println( "[*] Affichage de l'objet crée : \n" + contact.toString() ) ;

			System.out.println( "\n\n------------------------------------------------------" ) ;
			System.out.println( "		Test des accesseurs" ) ;

			System.out.println( "\n[*] Retour getNom() : " + contact.getNom() ) ;
			System.out.println( "\n[*] Retour getPrenom() : " + contact.getPrenom() ) ;
			System.out.println( "\n[*] Retour getEmail() : " + contact.getEmail() ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n\n######################################################" ) ;
		System.out.println( "		Fin des tests." ) ;
		System.out.println( "######################################################" ) ;
	}
}
