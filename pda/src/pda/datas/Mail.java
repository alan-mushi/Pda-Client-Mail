package pda.datas ;

import java.util.ArrayList ;
import java.util.HashMap ;
import pdaNetwork.misc.MailContent ;

/**
 * Cette classe rassemble les objets MailType dans 4 HashMaps
 * et une ArrayList.
 * @see pda.datas.MailType
 */
public class Mail implements StaticRefs , java.io.Serializable {
	
	private static final long serialVersionUID = 4L ;
	/** HashMaps correspondantes aux différents types. */
	private HashMap<String , MailType> recus , envoyes , brouillons , lus ;
	/**
	 * Pour les mails supprimés on ne garde que les id pour les supprimer sur le serveur.
	 */
	private ArrayList<String> supprimes ;

	/**
	 * Initialise les HashMaps et l'ArrayList.
	 */
	public Mail() {
		this.recus = new HashMap<String , MailType>(0) ;
		this.envoyes = new HashMap<String , MailType>(0) ;
		this.brouillons = new HashMap<String , MailType>(0) ;
		this.lus = new HashMap<String , MailType>(0) ;
		this.supprimes = new ArrayList<String>(0) ;
	}

	/**
	 * Ajoute un mail à la liste appropiée (différenciation par type).
	 * @throws IllegalArgumentException Si le type est invalide.
	 */
	public void add( String id , MailContent email , String type ) throws IllegalArgumentException {
		HashMap<String , MailType> dest = this.witchMap( type ) ;
		if ( dest == null ) {
			throw new IllegalArgumentException( "La map correspondante n'a pas été trouvée." ) ;
		}
		dest.put( id , new MailType( email , type ) ) ;
	}

	/**
	 * Change un mail de type, donc de HashMap.
	 * @throws IllegalArgumentException Si le type, l'id sont invalides.
	 */
	public void changeTo( String id , String type ) throws IllegalArgumentException {
		HashMap<String , MailType> origin = this.inWitchMap( id ) ;
		if ( origin == null ) {
			throw new IllegalArgumentException( "L'id n'a pas été trouvé..." ) ;
		}
		HashMap<String , MailType> dest = this.witchMap( type ) ;
		if ( dest == null ) {
			throw new IllegalArgumentException( "La map correspondante n'a pas été trouvé..." ) ;
		}
		MailType tmpMail = origin.remove( id ) ;
		tmpMail.setType( type ) ;
		dest.put( id , tmpMail ) ;
	}

	/**
	 * Supprime un mail, donc passe l'id dans l'ArrayList supprimes.
	 * @throws IllegalArgumentException Si id est invalide.
	 */
	public void supprMail( String id ) throws IllegalArgumentException {
		HashMap<String , MailType> origin = this.inWitchMap( id ) ;
		if ( origin == null ) {	
			throw new IllegalArgumentException( "La map correspondante n'a pas été trouvée." ) ;
		}
		origin.remove( id ) ;
		this.supprimes.add( id ) ;
	}

	/**
	 * Etablit une correspondance entre un type et une HashMap.
	 * @return <code>null</code> si la map ne correspond pas.
	 * @throws IllegalArgumentException Si le type est invalide.
	 */
	private HashMap<String , MailType> witchMap( String type ) throws IllegalArgumentException {
		MailType.checkType( type ) ;
		HashMap<String , MailType> res ;
		if ( type.equals( MailType.RECU ) ) { res = this.recus ; }
		else if ( type.equals( MailType.LU ) ) { res = this.lus ; }
		else if ( type.equals( MailType.ENVOYE ) ) { res = this.envoyes ; }
		else if ( type.equals( MailType.BROUILLON ) ) { res = this.brouillons ; }
		else { res = null ; }
		return ( res ) ;
	}

	/**
	 * Recherche dans quelle HashMap est l'id.
	 * @return La HashMap correspondante ou <code>null</code> si elle n'a pas été établie.
	 */
	private HashMap<String , MailType> inWitchMap( String id ) throws IllegalArgumentException {
		HashMap<String , MailType> res ;
		if ( id == null || id.isEmpty() ) {
			throw new IllegalArgumentException( "L'id n'est pas valide." ) ;
		}
		else {
			if ( this.recus.containsKey( id ) ) { res = this.recus ; }
			else if ( this.lus.containsKey( id ) ) { res = this.lus ; }
			else if ( this.envoyes.containsKey( id ) ) { res = this.envoyes ; }
			else if ( this.brouillons.containsKey( id ) ) { res = this.brouillons ; }
			else { res = null ; }
		}
		return ( res ) ;
	}

	/**
	 * Retourne la HashMap contenant les mails avec le type recu.
	 */
	public HashMap<String , MailType> getRecusMap() {
		return ( this.recus ) ;
	}

	/**
	 * Retourne la HashMap contenant les mails avec le type lu.
	 */
	public HashMap<String , MailType> getLusMap() {
		return ( this.lus ) ;
	}

	/**
	 * Retourne la HashMap contenant les mails avec le type envoye.
	 */
	public HashMap<String , MailType> getEnvoyesMap() {
		return ( this.envoyes ) ;
	}

	/**
	 * Retourne la HashMap contenant les mails avec le type brouillon.
	 */
	public HashMap<String , MailType> getBrouillonsMap() {
		return ( this.brouillons ) ;
	}

	/**
	 * Retourne l'ArrayList contenant les id des mails supprimés.
	 */
	public ArrayList<String> getSupprList() {
		return ( this.supprimes ) ;
	}
}
