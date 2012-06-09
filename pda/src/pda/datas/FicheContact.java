package pda.datas ;

/**
 * Cette classe gère une somme d'informations sur une personne à savoir
 * son nom, prenom et son email.
 */
public class FicheContact implements java.io.Serializable {

	/** Champ pour l'interface Serializable. */
	private static final long serialVersionUID = 2L ;
	/** Attributs d'une Fiche. */
	private String nom , prenom , email ;

	/**
	 * Ce constructeur gère différents cas de non validité
	 * des arguments qui lui sont passé en paramètre.
	 * @param leNom Nom à affecter à la Fiche.
	 * @param lePrenom Prenom à affecter à la Fiche.
	 * @param lemail Email à affecter à la Fiche.
	 * @throws IllegalArgumentException Un des paramètres n'est pas valide, le message d'exception
	 * contient le paramètre fautif.
	 */
	public FicheContact( String leNom , String lePrenom , String lemail ) throws IllegalArgumentException {
		if ( leNom == null || leNom.isEmpty() ) { throw new IllegalArgumentException( "Le nom n'est pas valide ") ; }
		else if ( lePrenom == null || lePrenom.isEmpty() ) { throw new IllegalArgumentException( "Le prenom n'est pas valide ") ; }
		else if ( lemail == null || lemail.isEmpty() ) { throw new IllegalArgumentException( "L'email n'est pas valide ") ; }
		this.nom = leNom ;
		this.prenom = lePrenom ;
		this.email = lemail ;
	}

	/**
	 * Accesseur pour le nom.
	 * @return Le nom de la Fiche.
	 */
	public String getNom() { return ( this.nom ) ; }

	/**
	 * Accesseur pour le prenom.
	 * @return Le prenom de la Fiche.
	 */
	public String getPrenom() { return ( this.prenom ) ; }

	/**
	 * Accesseur pour le l'email.
	 * @return L'email de la Fiche.
	 */
	public String getEmail() { return ( this.email ) ; }
	
	/**
	 * Toutes les informations sur la Fiche en une String.
	 * @return Les paramètres de la Fiche.
	 */
	public String toString() {
		String res = "---------------------\nNom : " + this.nom ;
		res += "\nPrenom : " + this.prenom ;
		res += "\nEmail : " + this.email ;
		return ( res ) ;
	}
}
