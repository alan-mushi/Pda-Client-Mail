package pda.datas ;

import java.io.FileOutputStream ;
import java.io.ObjectOutputStream ;
import java.io.FileInputStream ;
import java.io.ObjectInputStream ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
import java.io.File ;
import java.io.PrintWriter;

/**
 * Cette classe permet de gérer les fichiers pour l'application.
 */
public class DB {

	/**
	 * Sauvegarde un objet dans un fichier. Le nom du fichier est testé pour
	 * la non nullité et pour la présence d'au moins un caractère.
	 * @param obj L'objet que l'on souhaite écrire.
	 * @param fileName Le fichier de destination.
	 * @return <code>true</code> si tout c'est bien passé, <code>false</code> sinon.
	 * @throws IllegalArgumentException Si les paramètre ne sont pas valides.
	 */
	public boolean sauvegarder( Object obj , String fileName ) throws IllegalArgumentException {
		boolean res = false ;
		if ( fileName == null || fileName.isEmpty() ) {
			throw new IllegalArgumentException( "Le nom du fichier est vide ou null." ) ;
		}
		else if ( obj == null ) {
			throw new IllegalArgumentException( "La référence sur l'objet est nulle." ) ;
		}
		else {
			try {
				FileOutputStream out = new FileOutputStream( fileName ) ;
				ObjectOutputStream flux = new ObjectOutputStream( out ) ;
				flux.writeObject( obj ) ;
				flux.close() ;
				out.close() ;
				res = true ;
			}
			catch ( FileNotFoundException e ) {
				// Le fichier peut ne pas être présent. Pour les tests
				// les fichiers sont supprimés systématiquement.
			}
			catch ( IOException e ) {
				System.err.println( e.getMessage() ) ;
			}
		}
		return ( res ) ;
	}

	/**
	 * Charge un objet à partir d'un fichier. Le nom du fichier est testé pour
	 * la non nullité et pour la présence d'au moins un caractère.
	 * @param fileName Le nom du fichier dans lequel l'objet est sauvegardé.
	 * @return L'objet chargé.
	 * @throws IllegalArgumentException Si le paramètre n'est pas valide.
	 * @throws FileNotFoundException Si le fichier n'est pas trouvé.
	 */
	public Object charger( String fileName ) throws IllegalArgumentException, FileNotFoundException {
		Object res = null ;
		if ( fileName == null || fileName.isEmpty() ) {
			throw new IllegalArgumentException( "Le nom du fichier est vide ou null." ) ;
		}
		else {
			try {
				FileInputStream in = new FileInputStream( fileName ) ;
				ObjectInputStream flux = new ObjectInputStream( in ) ;
				res = flux.readObject() ;
				flux.close() ;
				in.close() ;
			}
			catch ( FileNotFoundException e ) {
				throw new FileNotFoundException( e.getMessage() ) ;
			}
			catch ( IOException e ) {
				System.err.println( e.getMessage() ) ;
			}
			catch ( ClassNotFoundException e ) {
				System.err.println( e.getMessage() ) ;
			}
		}
		return ( res ) ;
	}

	/**
	 * Supprime un fichier. Le nom du fichier est testé pour
	 * la non nullité et pour la présence d'au moins un caractère.
	 * @param fileName Le fichier à supprimer.
	 * @return <code>true</code> si le fichier a bien été supprimé, <code>false</code> sinon.
	 * @throws IllegalArgumentException Si le paramètre est invalide.
	 */
	public boolean supprimer( String fileName ) throws IllegalArgumentException {
		boolean res = false ;
		if ( fileName == null || fileName.isEmpty() ) {
			throw new IllegalArgumentException( "Le nom du fichier est vide ou null." ) ;
		}
		else {
			File monFichier = new File( fileName ) ;
			res = monFichier.delete() ;
		}
		return ( res ) ;
	}
	
	/**
	* Sauvegarde un fichier texte.
	* @param contenu Le contenu du fichier à enregistrer
	* @param fileName L'adresse/nom du fichier à enregistrer
	* @throws IllegalArgumentException Si le paramètre fileName est invalide.
	*/
	public boolean sauvegarderTexte(String contenu, String fileName) throws IllegalArgumentException {
		boolean res = false;
		if(fileName == null || fileName.isEmpty()) {
			throw new IllegalArgumentException( "Le nom du fichier est vide ou null." ) ; 
		}
		else {
			PrintWriter fichier = null;
			try {
				fichier = new PrintWriter(fileName);
				fichier.println(contenu);
				res = true;
			}
			catch(FileNotFoundException e) {
				System.out.println(e.getMessage());
			}
			finally {
				fichier.close();
			}
		}
		
		return res;
	}
}
