package pdaNetwork.client.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.codec.binary.Base64;

import pdaNetwork.misc.XMLServerMessage;
import pdaNetwork.misc.ConfigConst;
import pdaNetwork.misc.ProtocolException;

/**
 * Create a socket to the server.
 * 
 * @author PDA Server development team.
 *
 */
public class ClientPdaSocket {

    /**
     * Adress of the server.
     */
    protected String remoteHost;

    /**
     * Port of the server.
     */
    protected int remotePort;

    /**
     * The Socket used by the client. 
     */
    protected Socket servSock;

    /**
     * The PrintStream used by the client.
     */
    protected PrintStream out;

    /**
     * The BufferedReader used by the client.
     */
    protected BufferedReader in;

    /**
     * The name of the user.
     */
    private String username;

    /**
     * The cipherPass of the user.
     */
    private String cipherPass;

    /**
     *The service of the user. 
     */
    private String service;

    /**
     * A boolean which indicate if the server is connected.
     */
    private boolean isConnected;

    /**
     * The hash corresponding to a user and a service.
     */
    protected String hash;

    /**
     * An object timer t, it will be used by the ping manager.
     */
    private Timer timer;

    public final boolean debug;

    /**
     * Constructor of ClientPdaSocket class.
     * 
     * @param username Name of the user of the service.
     * @param password Password of the user.
     * @param service Service called.
     * 
     * @throws ProtocolException Throw if username, password or service are null.
     */
    public ClientPdaSocket (String username, String password, String service) throws ProtocolException {
	debug = ConfigConst.getDebug ();
	if (debug)
	    System.err.println ("ClientPdaSocket >>>");

	if (username==null || username.equals (""))
	    throw new ProtocolException ("Empty username");
	if (password==null || password.equals (""))
	    throw new ProtocolException ("Empty password");
	if (service==null || service.equals (""))
	    throw new ProtocolException ("Empty service");

	this.username = username;
	cipherPass = Md5.encode (password);
	this.service = service;

	remoteHost = ConfigConst.getRemoteHost ();
	remotePort = ConfigConst.getRemotePort ();

	isConnected = false;

	timer = new Timer ();
	timer.schedule (new PingManager (), 10000, 30000);

	if (debug)
	    System.err.println ("ClientPdaSocket <<<");
    }

    /**
     * Create a connection to the server.
     * 
     * @throws ProtocolException If an error occured during the connection, it's send with a ProtocolException.
     */
    public void connect () throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:connect >>>");
	if (isConnected ())
	    //déja connecté
	    return;
	sendMessage ("<newconnect><user>"+username+"</user><cipherPass>"+cipherPass+"</cipherPass><service>"+service+"</service></newconnect>");	
	if (debug)
	    System.err.println ("ClientPdaSocket:connect <<<");
    }

    /**
     * This method initialize the socket.
     * 
     * @throws ProtocolException If an error occured during the initisalisation of the socket, it's send with a ProtocolException.
     */
    protected void initSocket () throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:initSocket >>>");
	try {
	    servSock = new Socket (remoteHost, remotePort);
	    out = new PrintStream (servSock.getOutputStream ());
	    in = new BufferedReader (new InputStreamReader (servSock.getInputStream ()));
	} catch (UnknownHostException e) {
	    throw new ProtocolException ("Can't find server (or proxy) "+remoteHost+":"+remotePort);
	} catch (IOException e) {
	    throw new ProtocolException ("IOException with server (or proxy) "+remoteHost+":"+remotePort);
	}
	if (debug)
	    System.err.println ("ClientPdaSocket:initSocket <<<");
    }

    /**
     * This method close the socket
     * 
     * @throws ProtocolException If an error occured when the socket is closed, it's send with a ProtocolException. 
     */
    protected void closeSocket () throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:closeSocket >>>");
	try {
	    out.flush ();
	    out.close ();
	    in.close ();
	    servSock.close ();
	} catch (IOException e) {
	    isConnected = false;
	    throw new ProtocolException ("Can't close socket :", e);
	}
	if (debug)
	    System.err.println ("ClientPdaSocket:closeSocket <<<");
    }

    /**
     * Send a message to the server.
     * 
     * @param xmlMessage The XML message send to the server
     * 
     * @throws ProtocolException If an error occured when a message is sent, it's send with a ProtocolException. 
     */
    public synchronized String sendMessage (String xmlMessage) throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:sendMessage >>>");
	if (xmlMessage==null || xmlMessage.equals (""))
	    throw new ProtocolException ("Empty xmlMessage");
	if (!isConnected () && !xmlMessage.startsWith ("<newconnect>"))
	    throw new ProtocolException ("Not connected");

	initSocket ();
	if (!xmlMessage.startsWith ("<newconnect>") && !xmlMessage.startsWith ("<ping>") && !xmlMessage.startsWith ("<close>"))
	    xmlMessage = "<request><hash>" + hash + "</hash>" + xmlMessage + "</request>";
	byte[] tmp=Base64.encodeBase64 (xmlMessage.getBytes ());
	xmlMessage = new String (tmp);
	xmlMessage = ConfigConst.getStartOfReq ()+xmlMessage+ConfigConst.getEndOfReq ();

	// Différence avec WebClientPdaSocket
	xmlMessage = addHTML (xmlMessage);

	if (debug)
	    System.err.println ("ClientPdaSocket:receiveMessage xmlMessage : "+xmlMessage);

	out.println (xmlMessage);
	out.flush ();
	if (debug)
	    System.err.println ("ClientPdaSocket:sendMessage <<<");
	return receiveMessage ();
    }

    /**
     * Receive a message of the server.
     * 
     * @return Content of the message from the server.
     * 
     * @throws ProtocolException If an error occured during the reception of a message, it's send with a ProtocolException. 
     */
    private synchronized String receiveMessage () throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:receiveMessage >>>");
	try {
	    String line = in.readLine ();
	    if (line == null || line.equals (""))
		closeSocket ();

	    // XXX 27 / 04 /2012
	    while (in.ready ()) {
		String tmp = in.readLine ();
		line += tmp + "\n";
	    }
	    // XXX fin 27 / 04 /2012

	    if (debug)
		System.err.println ("ClientPdaSocket:receiveMessage line : "+line);
	    // Différence avec WebClientPdaSocket
	    line = removeHTML (line);

	    // pas de suppression de HTML
	    if (!line.startsWith (ConfigConst.getStartOfReq ()) || !line.endsWith (ConfigConst.getEndOfReq ())) {
		closeSocket ();
		throw new ProtocolException ("Protocol Error: " + line);
	    }
	    line = line.substring (ConfigConst.getStartOfReq ().length (), line.length ()-ConfigConst.getEndOfReq ().length ());
	    line = new String (Base64.decodeBase64 (line.getBytes ()));
	    parseXml (line);
	    closeSocket ();
	    if (debug)
		System.err.println ("ClientPdaSocket:receiveMessage <<<");
	    return line;
	} catch (IOException e) {
	    closeSocket ();
	    throw new ProtocolException ("Error with the listening of the socket", e);
	}
    }

    /**
     * This method add the HTML tag to the xmlMessage.
     * 
     * @param xmlMessage The XML message you want to send.
     * 
     * @return An xml message tagged with HTML.
     */
    protected String addHTML (String xmlMessage) {
	if (debug)
	    System.err.println ("ClientPdaSocket:addHTML : "+xmlMessage);
	return xmlMessage;
    }

    /**
     * This method remove the HTML tag.
     * 
     * @param htmlMessage The HTML message you want to untag.
     * 
     * @return The message untagged.
     */
    protected String removeHTML (String htmlMessage) {
	if (debug)
	    System.err.println ("ClientPdaSocket:addHTML : "+htmlMessage);
	return htmlMessage;
    }

    /**
     * This method parse an XML string.
     * 
     * @param xmlMessage An XML Message.
     * 
     * @throws ProtocolException If an error occured during the parsing of a message, it's send with a ProtocolException. 
     */
    protected void parseXml (String xmlMessage) throws ProtocolException {
	if (debug)
	    System.err.println ("ClientPdaSocket:parseXml >>>");
	XMLServerMessage xml = new XMLServerMessage (xmlMessage);
	String typeReq = xml.getTypeReq ();
	if (typeReq == null) {
	    closeSocket ();
	    throw new ProtocolException ("Unknown request type : " + typeReq);
	}
	//new connect
	if (typeReq.equals ("newconnect")) {
	    hash = xml.getAttribute ("hash");
	    isConnected = true;
	} else if (typeReq.equals ("error")) {
	    if (xml.getAttribute ("type").equals ("session"))
		isConnected = false;
	    throw new ProtocolException (xml.getAttribute ("type") + " " + xml.getAttribute ("text"));
	}
    }

    /**
     * This method send a ping to the server.
     */
    public synchronized void sendPing () {
	if (isConnected ()) {
	    try {
		sendMessage ("<ping><hash>" + hash + "</hash></ping>");
	    } catch (ProtocolException e) {
		System.err.println (e.getMessage ());
	    }
	}
    }

    /**
     * This method return the state of the connection between the server and the client.
     * 
     * @return A boolean which indicate if the client is connected.
     */
    public boolean isConnected () {
	return isConnected;
    }

    /**
     * Getter of HostName
     * @return HostName
     */
    public String getHostName () {
	return this.remoteHost;
    }

    /**
     * Getter of Host Port number
     * @return the host port number
     */
    public int getHostPortNumber () {
	return this.remotePort;
    }

    /**
     * A private class of ClientPdaSocket, it manage the ping.
     * 
     * @author  PDA Server development team
     *
     */
    class PingManager extends TimerTask {

	/**
	 * Launch the ping task.
	 */
	public void run () {
	    sendPing ();
	}
    }
	
    /**
     * Close the connection to server.
     */
    public void close (){
	timer.cancel ();
	try {
	    sendMessage ("<close><hash>" + hash + "</hash></close>");
	} catch (ProtocolException e) {
	    System.err.println (e.getMessage ());
	}
	isConnected = false;
    }
}
