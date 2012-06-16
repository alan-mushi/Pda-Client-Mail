package pda.view;

import pda.control.*;
import pda.datas.*;
import pdaNetwork.misc.ConfigConst ;
import pdaNetwork.client.network.Md5 ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.* ;

/**
 * Cette classe affiche et laisse à l'utilisateur la possibilité de modifier les 
 * paramètres de l'application.
 */
public class MailParamView implements StaticRefs , KeyListener , ActionListener {
	/** Panel principal de l'application */
	private JPanel mainPanel;

	/** Boutons de navigation */;
	private JButton retour, modifier, paramDefaut;

	/** Les labels pour la champs du formulaire */
	private JLabel labUserName , labMdp, labHote, labPort, labAdresseProxy, labPortProxy , motDePasseLabel ;

	/** Les champs de textes permettant de modifier les paramètres */
	private JTextField username , hote, port, adresseProxy, portProxy, mdp;

	/** Utiliser un proxy ? */
	private JCheckBox proxyUsed ;

	/** Référence locale sur Login pour obtenir le nom d'utilisateur et le mot de passe. */
	private Login login ;

	/** Champ de mot de passe. */
	private JPasswordField clearPasswd ;

	/**
	 * Constructeur
	 * @param thePanel Le panel principal de l'application.
	 */
	public MailParamView(JPanel thePanel) {
		this.mainPanel = thePanel;
		try {
			this.login = (Login) myDB.charger( loginFile ) ;
		} catch ( java.io.FileNotFoundException e ) { System.out.println( e.getMessage() ) ; }
		ConfigConst.readConfigFile( "data/xml/pdaServer/configClient.xml" , false ) ;
		mainPanel.removeAll();
		mainPanel.updateUI();
		initialiserGui();
		attacherReactions();
	}

	/**
	 * Permet d'initialiser l'interface graphique.
	 */
	public void initialiserGui() {
		mainPanel.setLayout(new BorderLayout());

		//##################################
		JPanel panelUser = new JPanel(new GridLayout(6, 1));
		//##################################
		//JPanel panelUser = new JPanel(new GridLayout(4, 1));
		JPanel panelConnection = new JPanel(new GridLayout(4, 1));
		JPanel panelProxy = new JPanel(new GridLayout(5, 1));
		JPanel panelCentre = new JPanel(new GridLayout(3, 1));
		JScrollPane defilement = new JScrollPane(panelCentre);

		retour = new JButton("Retour");
		paramDefaut = new JButton("Défaut");
		modifier = new JButton("Appliquer");

		labUserName = new JLabel( "Utilisateur :" ) ;
		//##################################
		motDePasseLabel = new JLabel( "Entrez votre nouveau mot de passe :" ) ;
		//##################################
		labMdp = new JLabel("Mot de passe (md5) :");
		labHote = new JLabel("Adresse :");
		labPort = new JLabel("Port :");
		labAdresseProxy = new JLabel("Adresse :");
		labPortProxy = new JLabel("Port :");

		//##################################
		clearPasswd = new JPasswordField( 15 ) ;
		//##################################
		mdp = new JTextField(this.login.getPasswd());
		mdp.setColumns(15);
		mdp.setEditable( false ) ;
		username = new JTextField( this.login.getUser() ) ;
		username.setColumns(15);
		hote = new JTextField( ConfigConst.getRemoteHost() );
		hote.setColumns(15);
		Integer portInt = new Integer(ConfigConst.getRemotePort());
		port = new JTextField(portInt.toString());
		port.setColumns(15);
		adresseProxy = new JTextField( ConfigConst.getProxyHost() );
		adresseProxy.setColumns(15);
		Integer portProxyInt = new Integer(ConfigConst.getProxyPort());
		portProxy = new JTextField(portProxyInt.toString());
		portProxy.setColumns(15);

		proxyUsed = new JCheckBox( "Utiliser un proxy ?" ) ;
		if(!ConfigConst.getProxyHost().equals("noProxy")) {
			proxyUsed.setSelected(true);
		}
		else {
			proxyUsed.setSelected(false);
			this.blurProxySettings() ;
		}

		panelUser.add( labUserName ) ;
		panelUser.add( username ) ;
		//##################################
		panelUser.add( motDePasseLabel ) ;
		panelUser.add( clearPasswd ) ;
		//##################################
		panelUser.add(labMdp);
		panelUser.add(mdp);

		panelConnection.add(labHote);
		panelConnection.add(hote);
		panelConnection.add(labPort);
		panelConnection.add(port);

		panelProxy.add( proxyUsed ) ;
		panelProxy.add(labAdresseProxy);
		panelProxy.add(adresseProxy);
		panelProxy.add(labPortProxy);
		panelProxy.add(portProxy);

		panelCentre.add( panelUser ) ;
		panelCentre.add( panelConnection ) ;
		panelCentre.add( panelProxy ) ;

		JPanel panelBas = new JPanel(new GridLayout(1, 3));

		panelUser.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder("Utilisateur"), BorderFactory.createEmptyBorder(5,5,5,5) ) ) ;
		panelConnection.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder("Serveur"), BorderFactory.createEmptyBorder(5,5,5,5) ) ) ;
		panelProxy.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder("Proxy"), BorderFactory.createEmptyBorder(5,5,5,5) ) ) ;

		panelBas.add(retour);
		panelBas.add(paramDefaut);
		panelBas.add(modifier);
		
		defilement.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		mainPanel.add(defilement, BorderLayout.CENTER);
		mainPanel.add(panelBas, BorderLayout.SOUTH);
	}

	/**
	 * Permet de faire le lien entre la vue (cette classe) et son controleur.
	 */
	public void attacherReactions() {
		MailParamCtrl controleur = new MailParamCtrl(this);
		retour.addActionListener(controleur);
		modifier.addActionListener(controleur);
		paramDefaut.addActionListener(controleur);
		clearPasswd.addKeyListener( this ) ;
		proxyUsed.addActionListener( this ) ;
	}

	/** Redéfinition obligatoire pour KeyListener. */
	public void keyPressed( KeyEvent evt ) { }

	/** Redéfinition obligatoire pour KeyListener. */
	public void keyTyped( KeyEvent evt ) { }

	/**
	 * Si des caractères sont tappés dans le champ de mot de passe,
	 * le champ présentant le hash md5 est mis à jour.
	 */
	public void keyReleased( KeyEvent evt ) {
		String tmp = new String( this.clearPasswd.getPassword() ) ;
		if ( ! tmp.isEmpty() ) {
			this.mdp.setText( Md5.encode( new String(this.clearPasswd.getPassword()) ) ) ;
		}
		else {	
			this.mdp.setText( login.getPasswd() ) ;
		}
	}

	/**
	 * Active désactive les champs de configuration avancée pour le proxy.
	 */
	public void actionPerformed( ActionEvent e ) {
		if ( proxyUsed.isSelected() ) {
			labAdresseProxy.setEnabled( true ) ;
			adresseProxy.setEnabled( true ) ;
			labPortProxy.setEnabled( true ) ;
			portProxy.setEnabled( true ) ;
		}
		else { this.blurProxySettings() ; }
	}
			

	/**
	 * Désactive les champs liés aux configurations avancées du proxy
	 * si la case <code>proxyUsed</code> est utilisée.
	 */
	private void blurProxySettings() {
		labAdresseProxy.setEnabled( false ) ;
		adresseProxy.setEnabled( false ) ;
		labPortProxy.setEnabled( false ) ;
		portProxy.setEnabled( false ) ;
	}
	
	/**
	* Permet de récupérer le bouton des paramètres par défaut.
	* @return Le bouton des paramètres par défaut.
	*/
	public JButton getBoutonParamDefaut() {
		return this.paramDefaut;
	}

	/**
	 * Permet de récupérer le bouton retour.
	 * @return Le bouton retour.
	 */
	public JButton getBoutonRetour() {
		return this.retour;
	}

	/**
	 * Permet de récupérer le bouton modifier.
	 * @return Le bouton modifier.
	 */
	public JButton getBoutonModifier() {
		return this.modifier;
	}
	
	/**
	* Permet de récupérer le champs du login.
	* @return Le champs du login.
	*/
	public JTextField getUserName() {
		return this.username;
	}
	
	/**
	* Permet de récupérer le champs du mot de passe.
	* @return Le champs du mot de passe.
	*/
	public JTextField getMdp() {
		return this.mdp;
	}
	
	/**
	* Permet de récupérer le champs de l'hôte (adresse du serveur).
	* @return Le champs de l'hote.
	*/
	public JTextField getHote() {
		return this.hote;
	}
	
	/**
	* Permet de récupérer le champs du port.
	* @return Le champs du port.
	*/
	public JTextField getPort() {
		return this.port;
	}
	
	/**
	* Permet de récupérer le champs de l'adresse du proxy.
	* @return Le champs du proxy
	*/
	public JTextField getAdresseProxy() {
		return this.adresseProxy;
	}
	
	/**
	* Permet de récupérer le champs du port du proxy.
	* @return Le champs du port du proxy.
	*/
	public JTextField getPortProxy() {
		return this.portProxy;
	}
	
	/**
	* Retourne la case à cocher qui définit si on utilise un proxy ou non.
	* @return La case à cocher qui définit si on utilise un proxy ou non.
	*/
	public JCheckBox getProxyUsed() {
		return this.proxyUsed;
	}

	/**
	 * Permet de retourner le panel principal de l'application.
	 * @return Le panel principal.
	 */
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
