package pda.datas ;

/**
 * Cette classe teste entièrement toutes les méthodes
 * publiques de la classe Contacts.
 * @see pda.datas.Contacts
 */
public class TestContacts implements StaticRefs {

	/**
	 * Le main gère tous les tests. Pour plus d'informations
	 * consultez le code source.
	 */
	public static void main( String[] args ) {
		System.out.println( "\n---------------------------------------------" ) ;
		System.out.println( "	Test du constructeur et de taille." ) ;

		System.out.print( "\n[*] Construction d'un objet Contacts ... " ) ;
		Contacts cont = new Contacts() ;
		System.out.println( "FAIT" ) ;
		System.out.print( "[*] Taille de la TreeMap après l'instanciation ... " ) ;
		int tailleMap = cont.taille() ;
		if ( tailleMap != 0 ) {
			System.out.println( "FAUSSE" ) ;
		}
		else {
			System.out.println( "VALIDE" ) ;
		}
		//----------------------------Test ajouter (deuxième)---------------------------
		System.out.println( "\n---------------------------------------------" ) ;
		System.out.println( "	Test des méthodes ajouter." ) ;
		System.out.println( "\n[*] Test du constructeur avec des erreurs (identiques à celles de FicheContact)." ) ;
		int nberr = 0 ;
		// Tous les tests valides voués à l'échec :
		// On compte leur nombre pour déterminer si ils ont bel et bien générer les erreurs attendues.
		try { cont.ajouter( null , "prenom" , "email" ) ; }
		catch ( IllegalArgumentException e ) { nberr++ ; }
		try { cont.ajouter( "" , "prenom" , "email" ) ; }
		catch ( IllegalArgumentException e ) { nberr++ ; }
		try { cont.ajouter( "nom" , null , "email" ) ; }
		catch ( IllegalArgumentException e ) { nberr++ ; }
		try { cont.ajouter( "nom" , "" , "email" ) ; }
		catch ( IllegalArgumentException e ) { nberr++ ; }
		try { cont.ajouter( "nom" , "prenom" , null ) ; }
		catch ( IllegalArgumentException e ) { nberr++ ; }
		try { cont.ajouter( "nom" , "prenom" , "" ) ; }
		catch ( IllegalArgumentException e ) { nberr++ ; }
		// Cette création est valide : 
		try { cont.ajouter( "nom" , "prenom" , "email" ) ; }
		catch ( IllegalArgumentException e ) { System.out.println( e.getMessage() ) ; }
		if ( nberr != 6 ) {
			System.out.println( "\n[-] Les Tests basés sur des erreurs destinés au constructeur de FicheContact ne sont pas corrects!\n" ) ;
		}
		else {
			System.out.println( "\n[+] Tous les tests basés sur des erreurs destinés au constructeur de FicheContact se sont effectués." ) ;
		}

		//----------------------------Test taillee (bis)---------------------------
		if ( cont.taille() != 1 ) {
			System.out.println( "\n[-] La taille devrait être de 1 ! (Un contact a été ajouté) \n" ) ;
		}
		else {
			System.out.println( "\n[+] La taille correspond bien au contact ajouté." ) ;
		}
		//----------------------------Test ajouter (première)---------------------------
		System.out.print( "\n[*] Génération de 2 fiches valides pour ajout dans Contacts ... " ) ;
		FicheContact fiche1, fiche2 ;
		try { 
			fiche1 = new FicheContact( "nom1" , "prenom1" , "email1" ) ;
			fiche2 = new FicheContact( "nom2" , "prenom2" , "email2" ) ;
			System.out.println( "OK" ) ;
			System.out.println( "\n[*] Ajout des fiches générées (ces fiches ne peuvent pas être rejetées) ... " ) ;
			try {
				System.out.print( "	[*] Ajout d'une fiche sans renseigner la cle (pas d'erreurs cf doc) ... " ) ;
				cont.ajouter( fiche1 ) ;
				System.out.println( "OK" ) ;
			}
			catch ( IllegalArgumentException e ) {
				System.out.println( "FAIL" ) ;
			}
			try {
				System.out.print( "	[*] Ajout d'une fiche en renseignant la cle ... " ) ;
				cont.ajouter( fiche2 ) ;
				System.out.println( "OK" ) ;
			}
			catch ( IllegalArgumentException e ) {
				System.out.println( "FAIL" ) ;
			}
			System.out.print( "\n[*] Test basé sur une erreur (ajout d'une fiche déjà présente dans la TreeMap) ... " ) ;
			try {
				cont.ajouter( fiche2 ) ;
				System.out.println( "FAIL" ) ;
			}
			catch ( IllegalArgumentException e ) {
				System.out.println( "\n" + e.getMessage() ) ;
			}
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( "FAIL	-	ABANDON DES ACTIONS PLUS AVANCEES." ) ;
		}
		//----------------------------Test existe---------------------------
		System.out.println( "\n---------------------------------------------" ) ;
		System.out.println( "	Test de la méthode existe." ) ;

		System.out.println( "\n[*] Test basé sur une erreur (cle nulle) ... " ) ;
		try {
			cont.existe( null ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (cle vide) ... " ) ;
		try {
			cont.existe( "" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		try {
			cont.existe( "nom1 prenom1" ) ;
			System.out.println( "OK" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( "FAIL : " + e.getMessage() ) ;
		}
		//----------------------------Test supprimer---------------------------
		System.out.println( "\n---------------------------------------------" ) ;
		System.out.println( "	Test de la méthode supprimer." ) ;

		System.out.println( "\n[*] Test basé sur une erreur (cle nulle) ... " ) ;
		try {
			cont.supprimer( null ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (cle vide) ... " ) ;
		try {
			cont.supprimer( "" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		try {
			cont.supprimer( "nom2 prenom2" ) ;
			System.out.println( "OK" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( "FAIL : " + e.getMessage() ) ;
		}
		//----------------------------Test cles---------------------------
		java.util.Set<String> cles = cont.cles() ;
		System.out.println( "\n[*] Test de la méthode cles (contenu de la TreeMap) : " + cles.toString() ) ;
		//----------------------------Test consulter---------------------------
		System.out.println( "\n---------------------------------------------" ) ;
		System.out.println( "	Test de la méthode consulter." ) ;
		System.out.println( "\n[*] Test basé sur une erreur (clé nulle) ... " ) ;
		try {
			cont.consulter( null ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.println( "\n[*] Test basé sur une erreur (clé vide) ... " ) ;
		try {
			cont.consulter( "" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé sur une erreur (clé non présente dans la TreeMap) ... " ) ;
		try {
			if ( cont.consulter( "nom2 prenom2" ) == null ) {
				System.out.println( "OK" ) ;
			}
			else { System.out.println( "FAIL" ) ; }
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
		try {
			FicheContact tmpFiche = cont.consulter( "nom prenom" ) ;
			System.out.println( "OK : \n" + tmpFiche ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( "FAIL : " + e.getMessage() ) ;
		}
		//----------------------------Test modifier---------------------------
		System.out.println( "\n---------------------------------------------" ) ;
		System.out.println( "	Test de la méthode modifier." ) ;

		System.out.print( "\n[*] Test basé sur une erreur (clé non présente dans la TreeMap) ... " ) ;
		try {
			FicheContact tmpFiche = new FicheContact( "nomModif" , "prenomModif" , "emailModif" ) ;
			cont.modifier( "jhfdsjhfsdf" , tmpFiche ) ;
			System.out.println( "FAIL" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( "OK : \n" + e.getMessage() ) ;
		}

		System.out.print( "\n[*] Test basé SANS erreurs (modification de fiche dans la TreeMap) ... " ) ;
		try {
			FicheContact tmpFiche = new FicheContact( "nomModif" , "prenomModif" , "emailModif" ) ;
			cont.modifier( "nom prenom" , tmpFiche ) ;
			System.out.println( "OK" ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.out.println( "FAIL : \n" + e.getMessage() ) ;
		}
		//----------------------------Test sauvegarde---------------------------
		System.out.println( "\n---------------------------------------------" ) ;
		System.out.println( "	Test de la méthode sauver." ) ;

		System.out.print( "\n[*] Ecriture dans un fichier de l'objet ... " ) ;
		cont.sauver() ;
		System.out.println( "OK" ) ;
		java.util.Set<String> oldSet = cont.cles() ;
		System.out.println( "\n[*] Clés de la TreeMap : " + oldSet ) ;

		System.out.print( "\n[*] Restauration de l'objet Contacts depuis le fichier ... " ) ;
		try {
			Contacts loaded = (Contacts) myDB.charger( contactsFile ) ;
			System.out.println( "OK" ) ;

			java.util.Set<String> newSet = loaded.cles() ;
			System.out.println( "\n[*] Cles présentes dans la TreeMap de l'objet chargé : " + newSet ) ;

			if ( oldSet.equals( newSet ) ) {
				System.out.println( "\n[+] Les deux Set sont identiques : la sauvegarde est valide." ) ;
			}
			else {
				System.out.println( "\n[-] Les deux Set sont différents : la sauvegarde n'est PAS valide." ) ;
			}
		} catch ( java.io.FileNotFoundException e ) {
			System.out.println( e.getMessage() ) ;
		}

		//------------------------------Suppression du fichier généré--------------------------------------
		try {
			myDB.supprimer( contactsFile ) ;
		}
		catch ( IllegalArgumentException e ) {
			System.err.println( e.getMessage() ) ;
		}

		System.out.println( "\n\n######################################" ) ;
		System.out.println( "[+] Tous les tests sont achevés." ) ;
		System.out.println( "######################################\n" ) ;
	}
}
