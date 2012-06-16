package pda.view;

import pda.control.*;
import pda.datas.*;
import pdaNetwork.misc.ConfigConst ;
import javax.swing.*;
import java.awt.*;
import java.awt.event.* ;

/**
 * Cette classe affiche et laisse à l'utilisateur la possibilité de modifier les 
 * paramètres de l'application.
 */
public class MailParamView implements ActionListener , StaticRefs {
	/** Panel principal de l'application */
	private JPanel mainPanel;

	/** Boutons de navigation */;
	private JButton retour, modifier;

	/** Les labels pour la champs du formulaire */
	private JLabel labUserName , labMdp, labHote, labPort, labAdresseProxy, labPortProxy;

	/** Les champs de textes permettant de modifier les paramètres */
	private JTextField username , hote, port, adresseProxy, portProxy, mdp;

	/** Utiliser un proxy ? */
	private JCheckBox proxyUsed ;

	/** Référence locale sur Login pour obtenir le nom d'utilisateur et le mot de passe. */
	private Login login ;

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

		JPanel panelUser = new JPanel(new GridLayout(4, 1));
		JPanel panelConnection = new JPanel(new GridLayout(4, 1));
		JPanel panelProxy = new JPanel(new GridLayout(5, 1));
		JPanel panelCentre = new JPanel(new GridLayout(3, 1));
		JScrollPane defilement = new JScrollPane(panelCentre);


		String infoLogin = "Utilisateur" ;
		String infoConnection = "Connection" ;
		String infoProxy = "Proxy" ;

		retour = new JButton("Retour");
		modifier = new JButton("Appliquer");

		labUserName = new JLabel( "Utilisateur :" ) ;
		labMdp = new JLabel("Mot de passe :");
		labHote = new JLabel("Adresse :");
		labPort = new JLabel("Port :");
		labAdresseProxy = new JLabel("Adresse :");
		labPortProxy = new JLabel("Port :");

		mdp = new JTextField(this.login.getPasswd());
		mdp.setColumns(15);
		username = new JTextField( this.login.getUser() ) ;
		username.setColumns(15);
		hote = new JTextField( ConfigConst.getRemoteHost() );
		hote.setColumns(15);
		port = new JTextField( ConfigConst.getRemotePort() );
		port.setColumns(15);
		adresseProxy = new JTextField( ConfigConst.getProxyHost() );
		adresseProxy.setColumns(15);
		portProxy = new JTextField( ConfigConst.getProxyPort() );
		portProxy.setColumns(15);

		proxyUsed = new JCheckBox( "Utiliser un proxy ?" ) ;
		proxyUsed.setSelected( false ) ;

		panelUser.add( labUserName ) ;
		panelUser.add( username ) ;
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

		JPanel panelBas = new JPanel(new GridLayout(1, 2));

		panelUser.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder("Utilisateur"), BorderFactory.createEmptyBorder(5,5,5,5) ) ) ;
		panelConnection.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder("Serveur"), BorderFactory.createEmptyBorder(5,5,5,5) ) ) ;
		panelProxy.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createTitledBorder("Proxy"), BorderFactory.createEmptyBorder(5,5,5,5) ) ) ;

		panelBas.add(retour);
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
	}

	/**
	 * Utilisé pour les JRadioButtons.
	 */
	public void actionPerformed( ActionEvent e ) {

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
	 * Permet de retourner le panel principal de l'application.
	 * @return Le panel principal.
	 */
	public JPanel getMainPanel() {
		return this.mainPanel;
	}
}
