package pda.datas ;

/**
 * Cette interfae permet de mettre en commun des objets.
 */
public interface StaticRefs {
	
	/** Référence sur l'objet DB. */
	public static final DB myDB = new DB() ;
	/** Nom de fichier pour l'objet Contacts. */
	public static final String contactsFile = "contacts.bin" ;
}
