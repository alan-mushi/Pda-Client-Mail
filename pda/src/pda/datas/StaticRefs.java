package pda.datas ;

/**
 * Cette interfae permet de mettre en commun des objets.
 */
public interface StaticRefs {
	
	/** Référence sur l'objet DB. */
	public static final DB myDB = new DB() ;

	/** Dossier principal de stockage. */
	public static final String root = "data/" ;
	/** Nom du fichier pour l'objet Login. */
	public static final String loginFile = root + "login.bin" ;
	/** Nom du fichier pour l'objet Contact. */
	public static final String contactsFile = root + "contacts.bin" ;
	/** Nom du fichier pour l'objet Mail. */
	public static final String mailsFile = root + "mails.bin" ;

	/** Dossier de stockage des HashMaps. */
	public static final String content = root + ".mapContent/" ;
	/** Nom du fichier de sauvegarde de la HashMap recus. */
	public static final String mapRecus = content + "recus.bin" ;
	/** Nom du fichier de sauvegarde de la HashMap lus. */
	public static final String mapLus = content + "lus.bin" ;
	/** Nom du fichier de sauvegarde de la HashMap envoyes. */
	public static final String mapEnvoyes = content + "envoyes.bin" ;
	/** Nom du fichier de sauvegarde de la HashMap brouillons. */
	public static final String mapBrouillons = content + "brouillons.bin" ;
	/** Nom du fichier de sauvegarde de la HashMap toSend. */
	public static final String mapToSend = content + "toSend.bin" ;
	/** Nom du fichier de sauvegarde de l'ArrayList Supprimes. */
	public static final String listSuppr = content + "suppr.bin" ;
}
