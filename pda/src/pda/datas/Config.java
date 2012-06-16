package pda.datas;

/**
* Classe permettant de gérer les paramètres de l'application et de les mettres sous format XML.
*/
public class Config {

	/** Les champs modifiable de la configuration */
	private String adresseServeur, portServeur, adresseProxy, portProxy;
	private boolean statutProxy;
	
	/**
	* Constructeur
	*/
	public Config() {
		adresseServeur = "";
		portServeur = "";
		adresseProxy = "";
		portProxy = "noProxy";
		statutProxy = false;
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
	* Définit si un proxy est utilisé ou non. Par défaut, aucun proxy n'est utilisé.
	* @param statut <code>true</code> si il y a un proxy, sinon <code>false</code>
	*/
	public void setProxy(boolean statut) {
		this.statutProxy = statut;
	}
	
	/**
	* Permet de transformer en XML la configuration.
	* @return Le contenu du fichier de configuration.
	*/
	public String toXml() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		xml += "<config>\n";
		xml += "<debug>0</debug>\n";
		if(statutProxy) {
			xml += "<socketType>webSocket</socketType>\n";
		}
		else {
			xml += "<socketType>socket</socketType>\n";
		}
		xml += "<remoteHost>" + this.adresseServeur + "</remoteHost>\n";
		xml += "<remotePort>" + this.portServeur + "</remotePort>\n";
		if(this.statutProxy) {
			xml += "<proxyHost>" + this.adresseProxy + "</proxyHost>\n";
  			xml += "<proxyPort>" + this.portProxy + "</proxyPort>\n";
  		}
  		else {
  			xml += "<proxyHost>noProxy</proxyHost>\n";
  			xml += "<proxyPort>3128</proxyPort>\n";
  		}
		xml += "<uri>/pda-server/pdaNetwork.php</uri>";
		xml += "<!-- others informations -->\n";
		xml += "<startOfReq>paquet</startOfReq>\n";
		xml += "<endOfReq>paquet</endOfReq>\n";
		xml += "</config>\n";
		
		return xml;
	}
	
	/**
	* Permet de sauvegarder le fichier de configuration.
	* @return Retourne <code>true</code> si la sauvegarde à réussie, sinon false.
	*/
	/*public boolean sauvegarderConfig() {
		return myDB.sauvegarderXML(this.toXml());
	}*/
}
