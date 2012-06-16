package pda.datas;

/**
* Classe permettant de gérer les paramètres de l'application et de les mettres sous format XML.
*/
public class Config {

	/** Les champs modifiable de la configuration */
	private String adresseServeur, portServeur, adresseProxy, portProxy;
	
	public Config() {
		adresseServeur = "";
		portServeur = "";
		adresseProxy = "";
		portProxy = "noProxy";
	}
	
	/**
	* Définit l'adresse du serveur.
	* @param adresse L'adresse du serveyr.
	*/
	public void setAdresseServeur(String adresse) {
		this.adresseServeur = adresse;
	}
	
	/**
	* Définit le port du serveur.
	* @param port Le port pour se connecter au serveur.
	*/
	public void setPortServeur(String port) {
		this.portServeur = port;
	}
	
	/**
	* Définit l'adresse du proxy.
	* @param adresse L'adresse pour se connecter au proxy.
	*/
	public void setAdresseProxy(String adresse) {
		this.adresseProxy = adresse;
	}
	
	/**
	* Définit le port pour accèder au proxy.
	* @param port Le port pour se connecter au proxy.
	*/
	public void setPortProxy(String port) {
		this.portProxy = port;
	}
	
	/**
	* Permet de transformer en XML la configuration.
	*/
	public String toXml() {
		String xml = "<?xml version="1.0" encoding="UTF-8"?>\n";
		xml += "<config>\n";
		xml += "<debug>0</debug>\n";
		xml += "<socketType>socket</socketType>\n";
		xml += "<remoteHost>" + this.adresseServeur + "</remoteHost>\n";
		xml += "<remotePort>" + this.portServeur + "</remotePort>\n";
		xml += "";
		
		return xml;
	}
	
	/**
	* Permet de sauvegarder le fichier de configuration.
	*/
	public boolean sauvegarderConfig() {
		myDB.sauvegarderXML();
	}
}
