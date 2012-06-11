package pda.datas ;

/**
 * Cette interfae permet de mettre en commun des objets.
 */
public interface StaticRefs {
	
	/** Référence sur l'objet DB. */
	public static final DB myDB = new DB() ;
	/** Nom de fichier pour l'objet Login. */
	public static final String loginFile = "login.bin" ;
	/** Nom de fichier pour l'objet Mail. */
	public static final String contactsFile = "contacts.bin" ;
}
