package pdaNetwork.client.service;

import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

import pdaNetwork.misc.XMLMailMessage;
import pdaNetwork.misc.MailContent;
import pdaNetwork.misc.ConfigConst;
import pdaNetwork.misc.ProtocolException;
import pdaNetwork.client.network.ClientPdaSocket;
import pdaNetwork.client.network.WebClientPdaSocket;

/**
 * This class manages the Mail service on the client PDA.
 * <p>
 * Usage Mail Send:
 * <pre>
 * try {
 *   MailClient sender = new MailClient ("user1", "mdp");
 *   sender.send( new MailContent ("user2", "Objet 1", "Le texte 1"));
 *   sender.send (new MailContent ("user2", "Objet 2", "Le texte 2"));
 *   sender.close ();
 *	} catch (ProtocolException e) {
 *	    e.printStackTrace ();
 *	}
 * </pre>
 * <p>
 * Usage Mail Receive :
 * <pre>
 * try {
 *	 MailClient receiver = new MailClient ("user2", "mdp");
 *   for (String id : receiver.getHeaders ()) {
 *	  	MailContent email = receiver.receive (id);
 * 		System.out.println ("expediteur : " + email.getExpeditor ());
 *		System.out.println ("recepteur : " + email.getRecipient ());
 *		System.out.println ("date : " + email.getDate ());
 *		System.out.println ("objet : " + email.getObject ());
 *		System.out.println ("texte : " + email.getText ());
 *	    }
 *   receiver.close ();
 *	} catch (ProtocolException e) {
 *	    e.printStackTrace ();
 *	}
 * </pre>
 * <p>
 * Usage Mail Clean :
 * <pre>
 * try {
 *   System.out.println ("destruction de tous les messages de user2");
 *	 MailClient receiver = new MailClient ("user2", "mdp");
 *   for (String id : receiver.getHeaders ()) {
 * 		receiver.delete (id);
 *	 }
 *   receiver.close ();
 *	} catch (ProtocolException e) {
 *	    e.printStackTrace ();
 *	}
 * </pre>
 * 
 * @author PDA Server development team.
 *
 */
public class MailClient {

    /**
     * Name of the user on the mail client.
     */
    private String username;

    /**
     * Password of the user.
     */
    private String password;

    /**
     * Socket which is connected to the PdaServer.
     */
    private ClientPdaSocket socket;

    /**
     * Mail who is in preperation to be sent.
     */
    private MailContent currentMail;

    private ArrayList<String> listHeaders;

    /**
     * 
     * Constructor of the Mail service. Initialize the variable.
     * 
     * @param username Username of the mail writer.
     * @param password Password of the mail writer.
     * 
     * @throws ProtocolException If an error occured during the reception of the mail, it's send with a ProtocolException.
     */
    public MailClient (String username, String password) throws ProtocolException {

	this.username = username;
	this.password = password;
	currentMail = null;
	socket = null;

	listHeaders = new ArrayList<String> ();

	try {
	    if (ConfigConst.getSocketType ().equals ("socket"))
		socket = new ClientPdaSocket (username, password, "mail");
	    else
		socket = new WebClientPdaSocket (username, password, "mail");
	    socket.connect ();
	    // if success, print of connection informations
	    System.out.println ( "====>\n\tConnected to server " + socket.getHostName() + " on port number " + socket.getHostPortNumber() + "\n<====" );
	} catch (ProtocolException e) {
	    e.printStackTrace ();
	    throw new ProtocolException ("Erreur lors de la creation du client de mail: " + e.getMessage ());
	}
    }

    /**
     * Retrieve header from the mail box.
     * 
     * @throws ProtocolException If an error occured during the reception of the mail, it's send with a ProtocolException. 
     */
    public ArrayList<String> getHeaders () throws ProtocolException {
	listHeaders.clear ();
	sendRequest ("<action>getheaders</action>");
	return listHeaders;
    }

    /**
     * 
     * Retrieve a mail.
     * 
     * @param id ID of the mail you want.
     * 
     * @throws ProtocolException If an error occured during the reception of the mail, it's send with a ProtocolException.
     */ 
    public MailContent receive (String id) throws ProtocolException {
	sendRequest ("<action>receive</action><id>" + id + "</id>");
	return currentMail;
    }

    /**
     * Send a mail.
     * 
     * @param mail The mail you want to send.
     * 
     * @throws ProtocolException If an error occured during the reception of the mail, it's send with a ProtocolException.
     */
    public void send (MailContent mail) throws ProtocolException {
	mail.setExpeditor (username);
	sendRequest ("<action>sendmail</action>" +
		     "<recipient>" + new String (Base64.encodeBase64 (mail.getRecipient ().getBytes ())) + "</recipient>" +
		     "<expeditor>"+new String (Base64.encodeBase64 (mail.getExpeditor ().getBytes ())) + "</expeditor>" +
		     "<object>"+new String (Base64.encodeBase64 (mail.getObject ().getBytes ()))+"</object>" + 
		     "<text>"+new String (Base64.encodeBase64 (mail.getText ().getBytes ())) + "</text>");
    }

    /**
     * Send a message on the server.
     * @throws ProtocolException If an error occured during the reception of the mail, it's send with a ProtocolException.
     *
     */
    private void sendRequest (String request) throws ProtocolException {
	if (request==null || request.equals (""))
	    throw new ProtocolException ("La requette ne doit pas etre vide");
	try {
	    if (!socket.isConnected ())
		socket.connect ();
	    parseXml (socket.sendMessage ("<mail>"+ request +"</mail>"));
	} catch (ProtocolException e) {
	    throw new ProtocolException ("Erreur lors de l'envois du message: " + e.getMessage ());
	}
    }

    /**
     * 
     * Initialize variables with the content of xmlMessage.
     * 
     * @param xmlMessage XML message who is analysed.
     * 
     * @throws ProtocolException If an error occured during the reception of the mail, it's send with a ProtocolException.
     */
    private void parseXml (String xmlMessage) throws ProtocolException {
	XMLMailMessage xml = new XMLMailMessage (xmlMessage);
	String typeReponse = xml.getAttribute ("reponse");
	if ("receive".equals (typeReponse)) {
	    String recipient = new String (Base64.decodeBase64 (xml.getAttribute ("recipient").getBytes ()));
	    String object = new String (Base64.decodeBase64 (xml.getAttribute ("object").getBytes ()));
	    String text = new String (Base64.decodeBase64 (xml.getAttribute ("text").getBytes ()));
	    String expeditor = new String (Base64.decodeBase64 (xml.getAttribute ("expeditor").getBytes ()));
	    String date = new String (Base64.decodeBase64 (xml.getAttribute ("date").getBytes ()));

	    MailContent mail = new MailContent (recipient, object, text, expeditor, date);
	    currentMail = mail;
	} else if ("getheaders".equals (typeReponse)) {
	    try {
		String message;
		for (int i = 1; ! (message = xml.getAttribute ("id" + i)).equals (""); i++)
		    listHeaders.add (message);
	    } catch (ProtocolException e) {
		// ne doit rien renvoyer : la boucle doit se terminer quand il n'y a plus de messages (cherche un message inexistant).
		// L'exception sert de condition de sortie de boucle.
	    }
	} else if ("error".equals (typeReponse))
	    throw new ProtocolException (xml.getAttribute ("text"));
    }

    /** 
     * Delete a mail.
     * 
     * @param id Id of the mail which is delete.
     * 
     * @throws ProtocolException If an error occured during the reception of the mail, it's send with a ProtocolException.
     */
    public void delete (String id) throws ProtocolException {
	sendRequest ("<action>delete</action><id>"+ id +"</id>");
    }

    /**
     * Method call when Mail objet is destroy by garbage collector.
     */
    protected void finalize () throws Throwable {
	if (socket != null)
	    socket.close ();
	socket = null;
    }

    /**
     * Close the connection to the mail service.
     */
    public void close () {
	if (socket != null)
	    socket.close ();
	socket = null;
    }
}
