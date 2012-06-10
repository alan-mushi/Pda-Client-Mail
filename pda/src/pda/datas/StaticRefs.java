package pda.datas ;

/**
 * Cette interfae permet de mettre en commun des objets.
 */
public interface StaticRefs {
	
	/** Référence sur l'objet DB. */
	public static final DB myDB = new DB() ;
	/** Nom de fichier pour l'objet Login. */
	public static final String loginFile = "login.bin" ;
	/** Nom de fichier pour l'objet Contacts. */
	public static final String contactsFile = "contacts.bin" ;
	/** Nom de l'utilisateur. */
	public static final String user = "Not set yet!" ;
}
