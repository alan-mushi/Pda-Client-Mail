package pda.datas ;

import java.io.* ;
import pdaNetwork.client.network.Md5 ;

/**
 * Cette classe permet de générer un fichier d'identification.
 * Il est nécessaire de faire une classe pour pouvoir le générer 
 * a cause du mode d'encryption.
 * @see pda.datas.Login
 */
public class UsersServerGen {
	
	public static void main( String[] args ) {
		
		String fileName = "users" ;
		System.out.println( "\n[*] Le fichier généré sera enregistré dans le répertoire courant sous le nom : " + fileName ) ;

		try {
			FileWriter fw = new FileWriter( fileName ) ;
			BufferedWriter bw = new BufferedWriter( fw ) ;
			PrintWriter output = new PrintWriter( bw ) ;

			System.out.print( "[+] Header ... " ) ;
			output.println( "#####################################################" ) ;
			output.println( "# Ce fichier a été modifier pour ne pas qu'un       #" ) ;
			output.println( "# utilisateur transmette en clair son mot de passe  #" ) ;
			output.println( "# sur le réseau.                                    #" ) ;
			output.println( "# Le fichier original est : user.bck                #" ) ;
			output.println( "#####################################################" ) ;
			output.println( "# Format du md5 : md5( md5 (passwd) )" ) ;
			System.out.println( "OK" ) ;

			System.out.print( "[+] Comptes par défaut ... " ) ;
			output.println( "user2:7101427ac886f90030b230c9132d961d:::mail,chat" ) ;
			output.println( "user1:7101427ac886f90030b230c9132d961d:::mail,chat" ) ;
			System.out.println( "OK" ) ;

			output.close() ;
			bw.close() ;
			fw.close() ;

			System.out.println( "[+] Ecriture du fichier achevé." ) ;
		}
		catch ( FileNotFoundException e ) {
			System.out.println( "LE fichier n'a pas été crée!" ) ;
		}
		catch ( IOException e ) {
			System.out.println( e.getMessage() ) ;
		}
	}
}
