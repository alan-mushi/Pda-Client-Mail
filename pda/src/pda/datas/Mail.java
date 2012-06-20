package pda.datas ;

import java.util.ArrayList ;
import java.util.HashMap ;
import pdaNetwork.misc.MailContent ;

/**
 * Cette classe rassemble les objets MailType dans 5 HashMaps
 * et une ArrayList.
 * @see pda.datas.MailType
 */
public class Mail implements StaticRefs , java.io.Serializable {

	private static final long serialVersionUID = 4L ;
	/** HashMaps correspondantes aux différents types. */
	private HashMap<String , MailType> recus , envoyes , brouillons , lus , toSend ;
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
		this.toSend = new HashMap<String , MailType>(0) ;
		this.supprimes = new ArrayList<String>(0) ;
	}

	/**
	 * Ajoute un mail à la liste appropiée (différenciation par type).
	 * @throws IllegalArgumentException Si le type est invalide.
	 * @return <code>true</code> si l'ajout c'est bien passé, <code>false</code> sinon.
	 */
	public boolean add( String id , MailContent email , String type ) throws IllegalArgumentException {
		boolean res = false ;
		HashMap<String , MailType> dest = this.whichMap( type ) ;
		if ( dest == null ) {
			throw new IllegalArgumentException( "La map correspondante n'a pas été trouvée." ) ;
		}
		dest.put( id , new MailType( email , type ) ) ;
		res = true ;
		return ( res ) ;
	}

	/**
	 * Ajoute un mail à la liste des messages a envoyer à la prochaine connection.
	 */
	public void addToSend( MailType email ) {
		toSend.put( this.getNextMaxKey() , email ) ;
	}

	/**
	 * Change un mail de type, donc de HashMap.
	 * @throws IllegalArgumentException Si le type, l'id sont invalides.
	 */
	public void changeTo( String id , String type ) throws IllegalArgumentException {
		HashMap<String , MailType> origin = this.inWhichMap( id ) ;
		if ( origin == null ) {
			throw new IllegalArgumentException( "L'id n'a pas été trouvé..." ) ;
		}
		HashMap<String , MailType> dest = this.whichMap( type ) ;
		if ( dest == null ) {
			throw new IllegalArgumentException( "La map correspondante n'a pas été trouvé..." ) ;
		}
		MailType tmpMail = origin.remove( id ) ;
		tmpMail.setType( type ) ;
		if ( dest.containsKey( id ) ) {
			id = this.getNextMaxKey() ;
		}
		dest.put( id , tmpMail ) ;
	}

	/**
	 * Supprime un mail, donc passe l'id dans l'ArrayList supprimes.
	 * @throws IllegalArgumentException Si id est invalide.
	 */
	public void supprMail( String id ) throws IllegalArgumentException {
		HashMap<String , MailType> origin = this.inWhichMap( id ) ;
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
	private HashMap<String , MailType> whichMap( String type ) throws IllegalArgumentException {
		MailType.checkType( type ) ;
		HashMap<String , MailType> res ;
		if ( type.equals( MailType.RECU ) ) { res = this.recus ; }
		else if ( type.equals( MailType.LU ) ) { res = this.lus ; }
		else if ( type.equals( MailType.ENVOYE ) ) { res = this.envoyes ; }
		else if ( type.equals( MailType.BROUILLON ) ) { res = this.brouillons ; }
		else if ( type.equals( MailType.TOSEND ) ) { res = this.toSend ; }
		else { res = null ; }
		return ( res ) ;
	}

	/**
	 * Retourne la clé qui <b>sera</b> la plus grande de toutes les HashMaps.
	 */
	public String getNextMaxKey() throws IllegalArgumentException {
		long res = 0 ;

		if ( this.recus.size() > 0 ) {
			Object[] ids = this.recus.keySet().toArray() ;
			java.util.Arrays.sort( ids ) ;
			res = Long.valueOf( (String) ids[ids.length-1] ) ;
		}
		if ( this.lus.size() > 0 ) {
			Object[] ids = this.lus.keySet().toArray() ;
			java.util.Arrays.sort( ids ) ;
			if ( res < Long.valueOf( (String) ids[ids.length-1] ) ) {
				res = Long.valueOf( (String) ids[ids.length-1] ) ;
			}
		}
		if ( this.envoyes.size() > 0 ) {
			Object[] ids = this.envoyes.keySet().toArray() ;
			java.util.Arrays.sort( ids ) ;
			if ( res < Long.valueOf( (String) ids[ids.length-1] ) ) {
				res = Long.valueOf( (String) ids[ids.length-1] ) ;
			}
		}
		if ( this.brouillons.size() > 0 ) {
			Object[] ids = this.brouillons.keySet().toArray() ;
			java.util.Arrays.sort( ids ) ;
			if ( res < Long.valueOf( (String) ids[ids.length-1] ) ) {
				res = Long.valueOf( (String) ids[ids.length-1] ) ;
			}
		}
		if ( this.toSend.size() > 0 ) {
			Object[] ids = this.toSend.keySet().toArray() ;
			java.util.Arrays.sort( ids ) ;
			if ( res < Long.valueOf( (String) ids[ids.length-1] ) ) {
				res = Long.valueOf( (String) ids[ids.length-1] ) ;
			}
		}
			
		res++ ;
		return ( String.valueOf( res ) ) ;
	}

	/**
	 * Recherche dans quelle HashMap est l'id.
	 * @return La HashMap correspondante ou <code>null</code> si elle n'a pas été établie.
	 */
	private HashMap<String , MailType> inWhichMap( String id ) throws IllegalArgumentException {
		HashMap<String , MailType> res ;
		if ( id == null || id.isEmpty() ) {
			throw new IllegalArgumentException( "L'id n'est pas valide." ) ;
		}
		else {
			if ( this.lus.containsKey( id ) ) { res = this.lus ; }
			else if ( this.recus.containsKey( id ) ) { res = this.recus ; }
			else if ( this.envoyes.containsKey( id ) ) { res = this.envoyes ; }
			else if ( this.brouillons.containsKey( id ) ) { res = this.brouillons ; }
			else if ( this.toSend.containsKey( id ) ) { res = this.toSend ; }
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
	 * Retourne la HashMap contenant les mails avec le type toSend.
	 */
	public HashMap<String , MailType> getToSendMap() {
		return ( this.toSend ) ;
	}

	/**
	 * Retourne l'ArrayList contenant les id des mails supprimés.
	 */
	public ArrayList<String> getSupprList() {
		return ( this.supprimes ) ;
	}

	/**
	 * Utilisé pour avoir un peu plus qu'une simple adresse...
	 */
	public String toString() {
		String res ;
		res = "\n################ OBJET MAIL ####################\n" ;
		res += "\n################ MAP RECUS #####################\n" + this.getRecusMap() ;
		res += "\n################ MAP LUS #######################\n" + this.getLusMap() ;
		res += "\n################ MAP ENVOYES ###################\n" + this.getEnvoyesMap() ;
		res += "\n################ MAP BROUILLONS ################\n" + this.getBrouillonsMap() ;
		res += "\n################ MAP TOSEND ####################\n" + this.getToSendMap() ;
		res += "\n################ LIST SUPPRIMES ################\n" + this.getSupprList() ;
		res += "\n################ END ###########################\n" ;
		return ( res ) ;
	}

}
