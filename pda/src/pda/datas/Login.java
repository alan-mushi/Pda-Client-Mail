package pda.datas ;

import pdaNetwork.client.network.Md5 ;

/**
 * Cette classe gère la connection à l'application.
 * Le login est indépendant des identifiants pour le serveur.
 */
public class Login implements StaticRefs, java.io.Serializable {

	/** Les identifiants locaux.*/
	private String passwd , user , oldUser ;
	/** Utilisé pour toujours savoir si on est loggué. */
	private boolean loggedIn ;
	private static final long serialVersionUID = 5L ;

	/**
	 * Initialise loggedIn à false.
	 */
	public Login() {
		this.loggedIn = false ;
	}

	/**
	 * Regarde si l'utilisateur est identique à celui sauvegardé.
	 * @throws IllegalArgumentException Si <code>unUser</code> est invalide
	 * ou si l'utilisateur est inconnu.
	 * @return <code>true</code> si l'utilisateur est valide.
	 */
	private boolean userExists( String unUser ) throws IllegalArgumentException {
		if ( unUser == null || unUser.isEmpty() ) {
			throw new IllegalArgumentException( "Le nom d'utilisateur n'est pas valide" ) ;
		}
		else if ( unUser.equals( this.user ) ) {
			return ( true ) ;
		}
		else {
			throw new IllegalArgumentException( "Cet utilisateur est inconnu." ) ;
		}
	}

	/**
	 * regarde si le mot de passe est identique à celui sauvegardé.
	 * @throws IllegalArgumentException Si <code>clearPass</code> est invalide ou le mot de
	 * passe est faux.
	 */
	private boolean passwdCorrect( String clearPass ) {
		if ( clearPass == null || clearPass.isEmpty() ) {
			throw new IllegalArgumentException( "Le mot de passe n'est pas valide" ) ;
		}
		else if ( this.passwd.equals( Md5.encode( clearPass ) ) ) {
			return ( true ) ;
		}
		else {
			throw new IllegalArgumentException( "Ce mot de passe est faux." ) ;
		}
	}

	/**
	 * Regarde si un utilisateur est crée.
	 * @return <code>true</code> si un utilisateur est enregistré, <code>false</code> sinon.
	 */
	private boolean userCreated() {
		boolean res = false ;
		if ( this.user != null && ! this.user.isEmpty() ) {
			res = true ;
		}
		return ( res ) ;
	}

	/**
	 * Méthode qui permet de se logguer à l'application. Si l'utilisateur n'est pas enregistré
	 * il sera crée à partir des informations transmises.
	 * @return <code>true</code> si le login est réussi, <code>false</code> sinon.
	 * @throws IllegalArgumentException Si un des paramètres n'est pas valide.
	 */
	public boolean LogMe( String unUser , String clearPass ) throws IllegalArgumentException {
		this.loggedIn = false ;
		if ( ! this.userCreated() ) {
			this.loggedIn = true ;
			try {
				this.modifyUser( unUser ) ;
				this.modifyPasswd( clearPass ) ;
			} catch ( IllegalArgumentException e ) {
				this.user = this.oldUser ;
				this.loggedIn = false ;
				throw new IllegalArgumentException( e.getMessage() ) ;
			}
		}
		else if ( this.userExists( unUser ) && this.passwdCorrect( clearPass ) ) {
			this.loggedIn = true ;
		}
		return ( this.loggedIn ) ;
	}

	/**
	 * Modifie le nom de l'utilisateur.
	 * @throws IllegalArgumentException Si on essaye d'effectuer la modification
	 * sans être loggué et si le paramètre n'est pas valide.
	 * @param unUser Le noueau nom de l'utilisateur.
	 */
	public void modifyUser( String unUser ) throws IllegalArgumentException {
		this.oldUser = this.user ;
		if ( ! this.loggedIn ) {
			throw new IllegalArgumentException( "Vous devez être loggué pour modifier l'utilisateur." ) ;
		}
		else if ( unUser == null || unUser.isEmpty() ) {
			throw new IllegalArgumentException( "Le nom d'utilisateur n'est pas valide" ) ;
		}
		else {
			this.user = unUser ;
		}
	}

	/**
	 * Modifie le mot de passe de l'utilisateur.
	 * @param clearPass Le mot de passe a affecter.
	 * @throws IllegalArgumentException Si le mot de passe n'est pas 
	 * valide ou si on est pas loggué à l'application.
	 */
	public void modifyPasswd( String clearPass ) throws IllegalArgumentException {
		if ( ! this.loggedIn ) {
			throw new IllegalArgumentException( "Vous devez être loggué pour modifier le mot de passe." ) ;
		}
		else if ( clearPass == null || clearPass.isEmpty() ) {
			throw new IllegalArgumentException( "Le mot de passe n'est pas valide." ) ;
		}
		else {
			this.passwd = Md5.encode( clearPass ) ;
		}
	}

	/**
	 * Retourne le nom de l'utilisateur. Si aucun utilisateur n'est crée,
	 * il sera retourné "L'utilisateur n'a pas encore été crée.".
	 */
	public String getUser() {
		String res ;
		if ( this.user == null ) {
			res = "L'utilisateur n'a pas encore été crée." ;
		}
		else { res = this.user ; }
		return ( res ) ;
	}

}
