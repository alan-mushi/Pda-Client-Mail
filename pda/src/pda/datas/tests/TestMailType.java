package pda.datas ;

/**
 * Cette classe teste les méthodes et le constructeur de la classe
 * MailType. <b>Les méthodes héritées de MailContent ne sont aps testées.</b>
 * @see pdaNetwork.misc.MailContent
 */
public class TestMailType {

	/**
	 * Méthode unique de cette classe de test.
	 * Pour plus d'informations regardez le code source.
	 */
	public static void main( String[] args ) {
		MailType mail ;
		//------------------Test du constructeur-----------------
		System.out.println( "\n------------------------------" ) ;
		System.out.println( "	Test du constructeur" ) ;
		try {
			System.out.print( "\n[*] Test basé sur une erreur (type null) ... " ) ;
			mail = new MailType( "destinataire" , "juste un petit coucou" , "hello world !" , null ) ;
			System.out.println( "FAIL" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( "OK : " + e.getMessage() ) ;
		}
		try {
			System.out.print( "\n[*] Test basé sur une erreur (type vide) ... " ) ;
			mail = new MailType( "destinataire" , "juste un petit coucou" , "hello world !" , "" ) ;
			System.out.println( "FAIL" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( "OK : " + e.getMessage() ) ;
		}
		try {
			System.out.print( "\n[*] Test basé SANS erreurs ... " ) ;
			mail = new MailType( "destinataire" , "juste un petit coucou" , "hello world !" , MailType.BROUILLON ) ;
			System.out.println( "mail crée ... OK" ) ;

			//------------------Test des méthodes-----------------
			System.out.println( "\n[*] Test des accesseurs par le biais de la méthode toString() : " + mail ) ;

			System.out.print( "[*] Test de changement de type pour le mail ... " ) ;
			mail.setType( MailType.LU ) ;
			if ( mail.getType().equals( MailType.LU ) ) {
				System.out.println( "OK" ) ;
			}
			else {
				System.out.println( "FAIL" ) ;
			}

			System.out.println( "\n[*] Les méthodes héritées de MailContent ne sont pas testées." ) ;

			System.out.println( "\n######################################" ) ;
			System.out.println( "[+] Tous les tests sont achevés." ) ;
			System.out.println( "######################################\n" ) ;
		} catch ( IllegalArgumentException e ) {
			System.out.println( "FAIL : " + e.getMessage() ) ;
		}
	}
}
