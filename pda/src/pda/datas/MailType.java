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
	public static final String RECU = "recu" , ENVOYE = "envoye" , BROUILLON = "brouillon" , LU = "lu" ;
	
	/**
	 *
	 */
	public MailType( String recipient , String object , String text , String tmpType ) throws IllegalArgumentException {
		super( recipient , object , text , user , null ) ;
		if ( tmpType == null || tmpType.isEmpty() ) {
			throw new IllegalArgumentException( "Le type du message n'est pas défini." ) ;
		}
		else if ( tmpType.equals( RECU ) || tmpType.equals( ENVOYE ) || tmpType.equals( BROUILLON ) || tmpType.equals( LU ) ) {
			this.type = tmpType ; 
		}
		else { 
			throw new IllegalArgumentException( "Le type du message n'est pas correct." ) ;
		}
	}
	
	/**
	 *
	 */
	public String getType() {
		return type;
	}
	
	/**
	 *
	 */
	public void setType(String unType) {
		this.type = unType;
	}

	/**
	 *
	 */
	public String toString() {
		String res = "type : " + this.getType() ;
		res += "\nexpéditeur : " + this.getExpeditor() ;
		res += "\nobjet : " + this.getObject() ;
		res += "\ndestinataire : " + this.getRecipient() ;
		res += "\ndate création : " + this.getDate() ;
		res += "\n------------------------------" ;
		res += "\nContenu du message : \n" + this.getText() ;
		return ( res ) ;
	}
}
