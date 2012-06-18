package pda.datas ;

import java.util.TreeMap ;
import java.util.Set ;

/**
 * Cette classe tient lieu d'annuaire. Les méthodes sont principalement des
 * surcouches pour les méthodes de TreeMap.<br />
 * Les intégritées des paramètres sont controlés dans toutes les méthodes
 * par les méthodes existe() et checkFiche().
 */
public class Contacts implements java.io.Serializable , StaticRefs {

	/** 
	 * Liste de toutes les fiches.<br />
	 * Organisation : <code>Nom Prenom , FicheContact</code>.
	 */
	private TreeMap<String , FicheContact> mapFiches ;
	private static final long serialVersionUID = 6L ;

	/**
	 * Le constructeur ne fait qu'initialiser la TreeMap avec une taille de 0.
	 */
	public Contacts() {
		this.mapFiches = new TreeMap<String , FicheContact>() ;
	}

	/**
	 * Retourne si oui ou non la cle est dans la TreeMap.<br />
	 * La deuxième fonction indirecte de cette méthode est de vérifier
	 * si la référence de la cle est non nulle et de taille non nulle.
	 * @return <code>true</code> si la cle est intègre et présente dans
	 * la TreeMap, <code>false</code> si la cle n'est pas dans la TreeMap.
	 * @throws IllegalArgumentException Si la cle n'est pas intègre.
	 */
	public boolean existe( String cle ) throws IllegalArgumentException {
		if ( cle == null || cle.isEmpty() ) {
			throw new IllegalArgumentException( "La cle est vide ou nulle." ) ;
		}
		return ( this.mapFiches.containsKey( cle ) ) ;
	}

	/**
	 * Jette une IllegalArgumentException si la référence su la FicheContact est nulle.
	 */
	private void checkFiche( FicheContact uneFicheContact ) throws IllegalArgumentException {
		if ( uneFicheContact == null ) {
			throw new IllegalArgumentException( "La FicheContact est nulle." ) ;
		}
	}

	/**
	 * Ajoute une FicheContact et sa clé correspondante à la TreeMap.
	 * @param uneFicheContact La FicheContact a ajouter.
	 * @throws IllegalArgumentException Si l'intégritée des paramètres n'est pas valide.
	 */
	public boolean ajouter( FicheContact uneFicheContact ) throws IllegalArgumentException {
		boolean res = false ;
		this.checkFiche( uneFicheContact ) ;
		String cle = uneFicheContact.getNom().concat(" ").concat( uneFicheContact.getPrenom() ) ;
		if ( ! this.existe( cle ) ) {
			this.mapFiches.put( cle , uneFicheContact ) ;
			res = true ;
		}
		else {
			throw new IllegalArgumentException( "La clé est déjà présente dans la TreeMap." ) ;
		}
		return ( res ) ;
	}

	/**
	 * Ajoute une fiche ainsi que sa cle par le biais du constructeur de FicheContact.
	 * @see pda.datas.FicheContact#FicheContact(java.lang.String leNom, java.lang.String lePrenom, java.lang.String lemail)
	 */
	public void ajouter( String nom , String prenom , String mail ) throws IllegalArgumentException {
		this.ajouter( new FicheContact( nom , prenom , mail ) ) ;
	}

	/**
	 * Retourne la taille de la TreeMap.
	 */
	public int taille() { 
		return ( this.mapFiches.size() ) ; 
	}

	/**
	 * Supprime une cle de la TreeMap.
	 */
	public void supprimer( String cle ) throws IllegalArgumentException {
		if ( ! this.existe( cle ) ) {
			throw new IllegalArgumentException( "La cle n'est pas présente dans la TreeMap." ) ;
		}
		else {
			this.mapFiches.remove( cle ) ;
		}
	}

	/**
	 * Modifie une FicheContact dans la TreeMap en identifiant la cle.
	 * @return <code>true</code> si l'action s'est déroulée avec succès, <code>false</code> sinon.
	 */
	public boolean modifier( String cle , FicheContact uneFicheContact ) throws IllegalArgumentException {
		boolean res = false ;
		this.checkFiche( uneFicheContact ) ;
		this.supprimer( cle ) ;
		this.ajouter( uneFicheContact ) ;
		res = true ;
		return ( res ) ;
	}

	/**
	 * Retourne la FicheContact associée à la cle.
	 * @param cle Cle associée à la FicheContact.
	 * @throws IllegalArgumentException Si l'intégrité de la cle n'est pas valide.
	 */
	public FicheContact consulter( String cle ) throws IllegalArgumentException {
		FicheContact res = null ;
		if ( this.existe( cle ) ) {
			res = this.mapFiches.get( cle ) ;
		}
		return ( res ) ;
	}

	/**
	 * Retourne les valeurs des clées présentes dans la TreeMap.
	 */
	public Set<String> cles() {
		return ( this.mapFiches.keySet() ) ;
	}

	/**
	 * Sauvegarde l'objet courant dans le fichier de sauvegarde.
	 */
	public void sauver() {
		myDB.sauvegarder( (Object) this , contactsFile ) ;
	}
}
