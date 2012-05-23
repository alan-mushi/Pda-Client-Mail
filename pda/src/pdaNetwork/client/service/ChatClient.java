package pdaNetwork.client.service;

import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;

import pdaNetwork.misc.ConfigConst;
import pdaNetwork.misc.ProtocolException;
import pdaNetwork.misc.XMLChatMessage;
import pdaNetwork.client.network.ClientPdaSocket;
import pdaNetwork.client.network.WebClientPdaSocket;

/**
 * This class is the chat API.
 * 
 * @author PDA Server development team.
 */
public class ChatClient {

    /**
     * Name of the user on the chat.
     */
    private String username;

    /**
     * Socket of the chat
     */
    private ClientPdaSocket socket;

    /**
     * List of all message left
     */
    private ArrayList<String> lastMessage;

    /**
     * Contain the list of the connected users. 
     */
    private ArrayList<String> listUsers;

    private boolean debug;

    /**
     * Constructor of the chat method
     *
     * @param username Name of the user on the chat.
     * @param cipherPass the encoded password of the user on the chat.
     * 
     * @throws ProtocolException If an error occured when chat services is running, it's send with a ProtocolException. 
     */
    public ChatClient (String username, String cipherPass) throws ProtocolException {
	debug = ConfigConst.getDebug ();
	if (debug)
	    System.err.println ("ChatClient >>> "+username+ " "+cipherPass);

	if (username==null || username.equals (""))
	    throw new ProtocolException ("Username must be filled.");
	this.username = username;
	lastMessage = new ArrayList<String> ();
	listUsers = new ArrayList<String> ();
	try {
	    if (ConfigConst.getSocketType ().equals ("socket"))
		socket = new ClientPdaSocket (username, cipherPass, "chat");
	    else
		socket = new WebClientPdaSocket (username, cipherPass, "chat");
	    socket.connect ();
	    if (debug)
		System.err.println ("ChatClient <<< ");
	} catch (ProtocolException e) {
	    throw new ProtocolException ("Error during the creation of the chat :  " + e.getMessage ());
	}
    }

    /**
     * This method send a message to the server.
     * 
     * @param message The message you want to send.
     * 
     * @throws ProtocolException If an error occured when a message is sent, it's send with a ProtocolException. 
     */
    public void sendMessage (String message) throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:sendMessage >>> "+message);
	if (message==null || message.equals (""))
	    throw new ProtocolException ("The message must not be empty.");
	sendRequest ("<action>newmessage</action><text>" + new String (Base64.encodeBase64 (message.getBytes ())) + "</text>");
	if (debug)
	    System.err.println ("ClientPdaSocket:sendMessage <<< ");
    }

    /**
     * This method get the new message from the server.
     * 
     * @return It returns the new message.
     * 
     * @throws ProtocolException If an error occured during receiption of a message, it's send with a ProtocolException. 
     */
    public ArrayList<String> getMessage () throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:getMessage >>>");
	lastMessage.clear ();
	if (!socket.isConnected ()) {
	    try {
		socket.connect ();
	    } catch (ProtocolException e) {
		throw new ProtocolException ("Error during the discution with the server : " + e.getMessage ());
	    }
	}
	sendRequest ("<action>getmessage</action>");
	if (debug)
	    System.err.println ("ClientPdaSocket:getMessage <<<");
	return lastMessage;
    }

    /**
     * Get the list of the users who is connected to the chat services.
     * 
     * @return The liste of the users.
     * 
     * @throws ProtocolException If an error occured during the receiption of the liste of users, it's send with a ProtocolException.
     */
    public ArrayList<String> getlistUser () throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:getlistUser >>>");
	listUsers.clear ();
	if (!socket.isConnected ()) {
	    try {
		socket.connect ();
	    } catch (ProtocolException e) {
		throw new ProtocolException ("Error during the discution with the server :" + e.getMessage ());
	    }
	}
	sendRequest ("<action>getlistusers</action>");
	if (debug)
	    System.err.println ("ClientPdaSocket:getlistusers <<<");
	return listUsers;
    }

    /**
     * This method parse the XML.
     * 
     * @param xmlMessage The message you want to parse.
     * 
     * @throws ProtocolException If an error occured during the parsing of a message, it's send with a ProtocolException. 
     */
    private void parseXml (String xmlMessage) throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:parseXml >>> "+xmlMessage);
	if (xmlMessage==null || xmlMessage.equals (""))
	    throw new ProtocolException ("The xmlMessage must not be empty.");
	XMLChatMessage xml = new XMLChatMessage (xmlMessage);
	String typeReponse = xml.getAttribute ("reponse");
	if ("newmessage".equals (typeReponse))
	    ;
	else if ("getmessage".equals (typeReponse)) {
	    try {
		String message;
		for (int i = 1; ! (message = xml.getAttribute ("message" + i)).equals (""); i++) {
		    lastMessage.add (new String (Base64.decodeBase64 (message.getBytes ())));
		}
	    } catch (ProtocolException e) {
		// ne doit rien renvoyer : la boucle doit se terminer quand il n'y a plus de messages (cherche un message inexistant).
		// L'exception sert de condition de sortie de boucle.
	    }
	} else if ("getlistusers".equals (typeReponse)) {
	    try {
		String message;
		for (int i = 1; ! (message = xml.getAttribute ("user" + i)).equals (""); i++) {
		    listUsers.add (message);
		}
	    } catch (ProtocolException e) {
		// ne doit rien renvoyer : la boucle doit se terminer quand il n'y a plus de messages (cherche un message inexistant).
		// L'exception sert de condition de sortie de boucle.
	    }
	}
	if (debug)
	    System.err.println ("ClientPdaSocket:parseXml <<<");
    }

    /**
     * This method send a request to the server.
     * 
     * @param request The request you want to send to the server.
     * 
     * @throws ProtocolException If an error occured when a message is sent, it's send with a ProtocolException. 
     */
    private void sendRequest (String request) throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:sendRequest >>> "+request);
	if (request==null || request.equals (""))
	    throw new ProtocolException ("The request must not be empty.");
	try {
	    if (!socket.isConnected ())
		socket.connect ();
	    parseXml (socket.sendMessage ("<chat>"+ request +"</chat>"));
	} catch (ProtocolException e) {
	    throw new ProtocolException ("Error during the discution with the server : " + e.getMessage ());
	}
	if (debug)
	    System.err.println ("ClientPdaSocket:sendRequest <<<");
    }

    /**
     * Method call when Mail objet is destroy by garbage collector.
     */
    protected void finalize () throws Throwable {
	if (debug)
	    System.err.println ("ClientPdaSocket:finalize >>>");
	if (socket != null)
	    socket.close ();
	socket.close ();
	if (debug)
	    System.err.println ("ClientPdaSocket:finalize <<<");
    }
	
    /**
     * Close the connection to the mail service.
     */
    public void close () {
	if (debug)
	    System.err.println ("ClientPdaSocket:close >>>");
	if (socket != null)
	    socket.close ();
	socket.close ();
	if (debug)
	    System.err.println ("ClientPdaSocket:close >>>");
    }
}
