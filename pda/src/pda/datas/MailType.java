package pda.datas ;

import pdaNetwork.misc.MailContent ;

/**
 * Cette classe est une surchouche de la classe MailContent. Elle a l'atout 
 * d'implémenter Serializable et StaticRefs.
 * @see pdaNetwork.misc.MailContent
 */
public class MailType extends MailContent implements java.io.Serializable , StaticRefs {
	/** Le type du message. */
	private String type;
	private static final long serialVersionUID = 3L ;
	public static final String RECU = "recu" , ENVOYE = "envoye" , BROUILLON = "brouillon" , LU = "lu" , SUPPR = "supprime" , TOSEND = "toSend" ;
	
	/**
	 * Surcouche pour le constructeur de MailContent. Le type est vérifié, il doit
	 * correspondre aux constantes <code>RECU, ENVOYE, BROUILLON, LU, SUPPR ou TOSEND</code>.
	 * @see pdaNetwork.misc.MailContent#MailContent(java.lang.String recipient, java.lang.String object, java.lang.String text, java.lang.String expeditor, java.lang.String date)
	 * @throws IllegalArgumentException Si le paramètre tmpType n'est pas valide.
	 */
	public MailType( String recipient , String object , String text , String exp , String tmpType ) throws IllegalArgumentException {
		super( recipient , object , text , exp , null ) ;
		if ( this.checkType( tmpType ) ) {
			this.type = tmpType ;
		}
	}
	
	/**
	 * Permet de créer un MailType à partir d'un objet MailContent.
	 */
	public MailType( MailContent mailC , String tmpType ) throws IllegalArgumentException {
		super( mailC.getRecipient() , mailC.getObject() , mailC.getText() , mailC.getExpeditor() , null ) ;
		if ( this.checkType( tmpType ) ) {
			this.type = tmpType ;
		}
	}

	/**
	 * Retourne le type du mail.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Positionne le type du mail.
	 */
	public void setType(String unType) {
		if ( this.checkType( unType ) ) {
			this.type = unType;
		}
	}

	/**
	 * Etablit si le type passé en paramètre est conforme.
	 * @return <code>true</code> si le tmpType est valide, <code>false</code> sinon.
	 */
	public static boolean checkType( String tmpType ) throws IllegalArgumentException {
		boolean res = false ;
		if ( tmpType == null || tmpType.isEmpty() ) {
			throw new IllegalArgumentException( "Le type du message n'est pas défini." ) ;
		}
		else if ( tmpType.equals( RECU ) || tmpType.equals( ENVOYE ) || tmpType.equals( BROUILLON ) || tmpType.equals( LU ) || tmpType.equals( SUPPR ) || tmpType.equals( TOSEND ) ) {
			res = true ; 
		}
		else { 
			throw new IllegalArgumentException( "Le type du message n'est pas correct." ) ;
		}
		return ( res ) ;
	}

	/**
	 * Cette méthode affiche les informations sur le mail en clair avec un formatage.
	 */
	public String toString() {
		String res = "\n-----header du message------\n" ;
		res += "type : " + this.getType() ;
		res += "\nexpéditeur : " + this.getExpeditor() ;
		res += "\nobjet : " + this.getObject() ;
		res += "\ndestinataire : " + this.getRecipient() ;
		res += "\ndate création/réception : " + this.getDate() ;
		res += "\n-----contenu du message-----\n" ;
		res += this.getText() ;
		res += "\n-------fin du message-------\n" ;
		return ( res ) ;
	}
}
